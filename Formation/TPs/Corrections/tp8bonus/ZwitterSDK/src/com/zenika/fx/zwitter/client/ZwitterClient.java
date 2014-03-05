package com.zenika.fx.zwitter.client;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenika.fx.zwitter.model.Zweet;
import com.zenika.fx.zwitter.model.ZwitterUser;

public class ZwitterClient {
  private ZweetDb db;
  private ScheduledExecutorService executor;
  private WeakReference<List<Zweet>> feed;
  private ReadWriteLock feedLock;

  public ZwitterClient() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.addMixInAnnotations(Zweet.class, ZweetMixin.class);
    mapper.addMixInAnnotations(ZwitterUser.class, ZwitterUserMixin.class);
    try {
      URL dbUrl = ZweetDb.class.getResource("zweets.json");
      if (dbUrl == null) {
        throw new IllegalStateException("Unable to find zweets.json");
      }
      this.db = mapper.readValue(dbUrl, ZweetDb.class);

      this.executor = Executors.newSingleThreadScheduledExecutor();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to parse zweets.json", e);
    }
  }

  public void startTimeline(List<Zweet> timelineFeed) {
    feedLock = new ReentrantReadWriteLock();
    this.feed = new WeakReference<List<Zweet>>(timelineFeed);
    ZweetRandomProducer producer = new ZweetRandomProducer(db, timelineFeed, feedLock);
    executor.scheduleAtFixedRate(producer, 0L, 15L, TimeUnit.SECONDS);
  }

  /**
   * Publish your own zweets using this method
   */
  public void publish(Zweet zweet) {
    try {
      feedLock.writeLock().lock();
      List<Zweet> target = feed.get();
      if (target != null) {
        target.add(0, zweet);
      }
    } finally {
      feedLock.writeLock().unlock();
    }
  }

  public void terminate() {
    executor.shutdownNow();
  }

  /**
   * Convenience method to retrieve a Zwitter User image as an InputStream
   * 
   * @param imgReference
   * @return
   */
  public static InputStream getImageAsStream(String imgReference) {
    return ZwitterClient.class.getResourceAsStream(imgReference);
  }

  private static class ZweetRandomProducer implements Runnable {
    private WeakReference<ZweetDb> sourceRef;
    private WeakReference<List<Zweet>> sinkRef;
    private final ReadWriteLock lock;

    public ZweetRandomProducer(ZweetDb source, List<Zweet> sink, ReadWriteLock feedLock) {
      this.sourceRef = new WeakReference<ZweetDb>(source);
      this.sinkRef = new WeakReference<List<Zweet>>(sink);
      this.lock = feedLock;
    }

    @Override
    public void run() {
      try {
        ZweetDb source = sourceRef.get();
        List<Zweet> sink = sinkRef.get();
        if (source == null || sink == null)
          return;

        Random r = new Random();
        int index = r.nextInt(source.getDb().size());
        Zweet zweet = source.getDb().get(index);

        try {
          lock.writeLock().lock();
          sink.add(0, zweet);
        } finally {
          lock.writeLock().unlock();
        }
      } catch (Throwable t) {
        t.printStackTrace();
      }
    }
  }
}
