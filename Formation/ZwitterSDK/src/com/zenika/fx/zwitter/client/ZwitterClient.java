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

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final ZweetDb db;
    private WeakReference<List<Zweet>> feed;
    private ReadWriteLock feedLock;

    public ZwitterClient() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.addMixInAnnotations(Zweet.class, ZweetMixin.class);
        mapper.addMixInAnnotations(ZwitterUser.class, ZwitterUserMixin.class);
        try {
            final URL dbUrl = ZweetDb.class.getResource("zweets.json");
            if (null == dbUrl) {
                throw new IllegalStateException("Unable to find zweets.json");
            }
            db = mapper.readValue(dbUrl, ZweetDb.class);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to parse zweets.json", e);
        }
    }

    public void startTimeline(final List<Zweet> timelineFeed) {
        feedLock = new ReentrantReadWriteLock();
        feed = new WeakReference<>(timelineFeed);
        final ZweetRandomProducer producer = new ZweetRandomProducer(db, timelineFeed, feedLock);
        executor.scheduleAtFixedRate(producer, 0L, 15L, TimeUnit.SECONDS);
    }

    /**
     * Publish your own zweets using this method
     */
    public void publish(final Zweet zweet) {
        try {
            feedLock.writeLock().lock();
            final List<Zweet> target = feed.get();
            if (null != target) {
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
     */
    public static InputStream getImageAsStream(final String imgReference) {
        return ZwitterClient.class.getResourceAsStream(imgReference);
    }

    private static class ZweetRandomProducer implements Runnable {

        private final WeakReference<ZweetDb> sourceRef;
        private final WeakReference<List<Zweet>> sinkRef;
        private final ReadWriteLock lock;

        public ZweetRandomProducer(final ZweetDb source, final List<Zweet> sink, final ReadWriteLock feedLock) {
            sourceRef = new WeakReference<>(source);
            sinkRef = new WeakReference<>(sink);
            lock = feedLock;
        }

        @Override
        public void run() {
            try {
                final ZweetDb source = sourceRef.get();
                final List<Zweet> sink = sinkRef.get();
                if (null == source || null == sink) {
                    return;
                }

                final Random r = new Random();
                final int index = r.nextInt(source.getDb().size());
                final Zweet zweet = source.getDb().get(index);
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
