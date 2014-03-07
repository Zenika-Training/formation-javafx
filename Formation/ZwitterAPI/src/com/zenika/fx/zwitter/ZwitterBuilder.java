package com.zenika.fx.zwitter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zenika.fx.zwitter.model.Zweet;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public class ZwitterBuilder {

    private Set<EventHandler<WorkerStateEvent>> onSuccessHandlers = new HashSet<>();
    private double delay = 1000;
    private double period = 2000;

    private ZwitterBuilder() {
        final EventHandler<WorkerStateEvent> onSuccessHandler = new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(final WorkerStateEvent workerStateEvent) {
                final Object value = workerStateEvent.getSource().getValue();
                System.out.println("Got new values for Zwitter:" + value);
            }
        };
        onSuccessHandlers.add(onSuccessHandler);
    }

    public static ZwitterBuilder create() {
        return new ZwitterBuilder();
    }

    public ZwitterBuilder withOnSuccessHandler(final EventHandler<WorkerStateEvent> onSuccessHandler) {
        onSuccessHandlers.add(onSuccessHandler);
        return this;
    }

    public ZwitterBuilder withObservableList(final ObservableList<Zweet> zweets) {
        return withObservableList(zweets, -1);
    }

    public ZwitterBuilder withObservableList(final ObservableList<Zweet> zweets, final int maxElements) {
        final EventHandler<WorkerStateEvent> onSuccessHandler = new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(final WorkerStateEvent workerStateEvent) {
                final List<Zweet> zweetsFromWorker = (List<Zweet>) workerStateEvent.getSource().getValue();
                for (int i = zweetsFromWorker.size() - 1; i >= 0; i--) {
                    final Zweet zweet = zweetsFromWorker.get(i);
                    zweets.add(0, zweet);
                }
                if (maxElements > 0 && zweets.size() > maxElements) {
                    zweets.remove(maxElements, zweets.size());
                }
            }
        };
        onSuccessHandlers.add(onSuccessHandler);
        return this;
    }

    public ZwitterBuilder withDelay(final double delay) {
        this.delay = delay;
        return this;
    }

    public ZwitterBuilder withPeriod(final double period) {
        this.period = period;
        return this;
    }

    public Zwitter build() {
        return new ZwitterImpl(delay, period, onSuccessHandlers);
    }

}
