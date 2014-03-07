package com.zenika.fx.zwitter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zenika.fx.zwitter.jackson.ZweetDb;
import com.zenika.fx.zwitter.jackson.ZweetMixin;
import com.zenika.fx.zwitter.jackson.ZwitterUserMixin;
import com.zenika.fx.zwitter.model.Zweet;
import com.zenika.fx.zwitter.model.ZwitterUser;
import fxexperience.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

public class ZwitterImpl implements Zwitter {

    protected final BlockingQueue<Zweet> zweetsQueue = new ArrayBlockingQueue<>(100);
    protected final ZwitterService zwitterService;

    static class ZwitterService extends ScheduledService<List<Zweet>> {

        private final BlockingQueue<Zweet> toPublish;
        private final ZweetDb zweetDb;
        private final int zweetsPerExecution;

        ZwitterService(final BlockingQueue<Zweet> toPublish, final ZweetDb zweetDb, final int zweetsPerExecution) {
            this.toPublish = toPublish;
            this.zweetDb = zweetDb;
            this.zweetsPerExecution = zweetsPerExecution;
        }

        @Override
        protected Task<List<Zweet>> createTask() {
            return new Task<List<Zweet>>() {
                @Override
                protected List<Zweet> call() throws Exception {
                    final List<Zweet> results = new ArrayList<>();
                    toPublish.drainTo(results);
                    if (results.isEmpty()) {
                        for (int i = 0; i < zweetsPerExecution; i++) {
                            final Zweet zweet = generateZweet(zweetDb);
                            results.add(zweet);
                        }
                    }
                    toPublish.clear();
                    return results;
                }
            };
        }
    }

    protected ZwitterImpl(final double delay, final double period, final Collection<EventHandler<WorkerStateEvent>> onSuccessHandlers) {
        zwitterService = buildZwitterService(delay, period, onSuccessHandlers);
    }

    protected ZwitterService buildZwitterService(final double delay, final double period, final Collection<EventHandler<WorkerStateEvent>> onSuccessHandlers) {
        final ZwitterService service = new ZwitterService(zweetsQueue, parseZweetDb(), 1);
        service.setDelay(new Duration(delay));
        service.setPeriod(new Duration(period));

        final EventHandler<WorkerStateEvent> onSuccessHandler = new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(final WorkerStateEvent workerStateEvent) {
                for (final EventHandler<WorkerStateEvent> handler : onSuccessHandlers) {
                    handler.handle(workerStateEvent);
                }
            }
        };
        service.setOnSucceeded(onSuccessHandler);

        return service;
    }

    protected ZweetDb parseZweetDb() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.addMixInAnnotations(Zweet.class, ZweetMixin.class);
        mapper.addMixInAnnotations(ZwitterUser.class, ZwitterUserMixin.class);
        try {
            final URL dbUrl = ZweetDb.class.getResource("zweets.json");
            if (null == dbUrl) {
                throw new IllegalStateException("Unable to find zweets.json");
            }
            return mapper.readValue(dbUrl, ZweetDb.class);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to parse zweets.json", e);
        }
    }

    @Override
    public void start() {
        zwitterService.restart();
    }

    @Override
    public void stop() {
        zwitterService.cancel();
    }

    @Override
    public void publish(final Zweet zweet) {
        zweetsQueue.add(zweet);
    }

    public static Zweet generateZweet(final ZweetDb source) {
        final Random r = new Random();
        final int index = r.nextInt(source.getDb().size());
        return source.getDb().get(index);
    }

}
