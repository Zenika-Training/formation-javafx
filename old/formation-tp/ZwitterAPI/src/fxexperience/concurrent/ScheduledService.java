package fxexperience.concurrent;

import javafx.animation.PauseTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * <p>
 * The ScheduledService is a service which will automatically restart
 * itself after a successful execution, and under some conditions will
 * restart even in case of failure. A new ScheduledService begins in
 * the READY state, just as a normal Service. After calling
 * <code>start</code> or <code>restart</code>, the ScheduledService will
 * enter the SCHEDULED state for the duration specified by <code>delay</code>.
 * </p>
 * <p>
 * Once RUNNING, the ScheduledService will execute its Task. On successful
 * completion, the ScheduledService will transition to the SUCCEEDED state,
 * and then to the READY state and back to the SCHEDULED state. The amount
 * of time the ScheduledService will remain in this state depends on the
 * amount of time between the last state transition to RUNNING, and the
 * current time, and the <code>period</code>. In short, the <code>period</code>
 * defines the minimum amount of time between executions. If the previous
 * execution completed before <code>period</code> expires, then the
 * ScheduledService will remain in the SCHEDULED state until the period
 * expires. If on the other hand the execution took longer than the
 * specified period, then the ScheduledService will immediately transition
 * back to RUNNING.
 * </p>
 * <p>
 * If, while RUNNING, the ScheduledService's Task throws an error or in
 * some other way ends up transitioning to FAILED, then the ScheduledService
 * will either restart or quit, depending on the values for
 * <code>computeEaseOff</code>, <code>restartOnFailure</code>, and
 * <code>maximumFailureCount</code>.
 * </p>
 * <p>
 * If a failure occurs and <code>restartOnFailure</code> is false, then
 * the ScheduledService will transition to FAILED and will stop. To restart
 * a failed ScheduledService, you must call restart manually.
 * </p>
 * <p>
 * If a failure occurs and <code>restartOnFailure</code> is true, then
 * the the ScheduledService <em>may</em> restart automatically. First,
 * the result of calling <code>computeEaseOff</code> will become the
 * new <code>cumulativePeriod</code>. In this way, after each failure, you can cause
 * the service to wait a longer and longer period of time before restarting.
 * ScheduledService defines static EXPONENTIAL_EASE_OFF and LOGARITHMIC_EASE_OFF
 * implementations, of which LOGARITHMIC_EASE_OFF is the default value of
 * computeEaseOff. After <code>maximumFailureCount</code> is reached, the
 * ScheduledService will transition to FAILED in exactly the same way as if
 * <code>restartOnFailure</code> were false.
 * </p>
 */
public abstract class ScheduledService<V> extends Service<V> {

    /**
     * A Callback implementation for the <code>computeEaseOff</code> property which
     * will exponentially ease off the period between re-executions in the case of
     * a failure. This computation takes the original period and the number of
     * consecutive failures and computes the ease off amount from that information.
     */
    public static final Callback<ScheduledService<?>, Duration> EXPONENTIAL_EASE_OFF = new Callback<ScheduledService<?>, Duration>() {
        @Override
        public Duration call(ScheduledService<?> service) {
            double period = (service == null || service.getPeriod() == null) ? 0 : service.getPeriod().toMillis();
            double x = (service == null) ? 0 : service.getCurrentFailureCount();
            return Duration.millis(period + (period * Math.exp(x)));
        }
    };

    /**
     * A Callback implementation for the <code>computeEaseOff</code> property which
     * will logarithmically ease off the period between re-executions in the case of
     * a failure. This computation takes the original period and the number of
     * consecutive failures and computes the ease off amount from that information.
     */
    public static final Callback<ScheduledService<?>, Duration> LOGARITHMIC_EASE_OFF = new Callback<ScheduledService<?>, Duration>() {
        @Override
        public Duration call(ScheduledService<?> service) {
            double period = (service == null || service.getPeriod() == null) ? 0 : service.getPeriod().toMillis();
            double x = (service == null) ? 0 : service.getCurrentFailureCount();
            return Duration.millis(period + (period * Math.log1p(x)));
        }
    };

    /**
     * The initial delay between when the ScheduledService is first started, and when it will begin
     * operation. This is the amount of time the ScheduledService will remain in the SCHEDULED state,
     * before entering the RUNNING state.
     */
    private ObjectProperty<Duration> delay = new SimpleObjectProperty<Duration>(this, "delay", Duration.ZERO);

    public final Duration getDelay() {
        return delay.get();
    }

    public final void setDelay(Duration value) {
        delay.set(value);
    }

    public final ObjectProperty<Duration> delayProperty() {
        return delay;
    }

    /**
     * The minimum amount of time to allow between the last time the service was in the RUNNING state
     * until it should run again. The actual period (also known as <code>cumulativePeriod</code>)
     * will depend on this property as well as the <code>computeEaseOff</code> and number of failures.
     */
    private ObjectProperty<Duration> period = new SimpleObjectProperty<Duration>(this, "period", Duration.ZERO);

    public final Duration getPeriod() {
        return period.get();
    }

    public final void setPeriod(Duration value) {
        period.set(value);
    }

    public final ObjectProperty<Duration> periodProperty() {
        return period;
    }

    /**
     * Computes the amount of time to add to the period on each failure. This cumulative amount is reset whenever
     * the the ScheduledService is manually restarted. The Callback takes a Duration, which is the last
     * <code>cumulativePeriod</code>, and returns a Duration which will be the new <code>cumulativePeriod</code>.
     */
    private ObjectProperty<Callback<ScheduledService<?>, Duration>> computeEaseOff = new SimpleObjectProperty<Callback<ScheduledService<?>, Duration>>(this, "computeEaseOff", LOGARITHMIC_EASE_OFF);

    public final Callback<ScheduledService<?>, Duration> getComputeEaseOff() {
        return computeEaseOff.get();
    }

    public final void setComputeEaseOff(Callback<ScheduledService<?>, Duration> value) {
        computeEaseOff.set(value);
    }

    public final ObjectProperty<Callback<ScheduledService<?>, Duration>> computeEaseOffProperty() {
        return computeEaseOff;
    }

    /**
     * Indicates whether the ScheduledService should automatically restart in the case of a failure.
     */
    private BooleanProperty restartOnFailure = new SimpleBooleanProperty(this, "restartOnFailure", false);

    public final boolean getRestartOnFailure() {
        return restartOnFailure.get();
    }

    public final void setRestartOnFailure(boolean value) {
        restartOnFailure.set(value);
    }

    public final BooleanProperty restartOnFailureProperty() {
        return restartOnFailure;
    }

    /**
     * The maximum number of times the ScheduledService can fail before it simply ends in the FAILED
     * state. You can of course restart the ScheduledService manually, which will cause the current
     * count to be reset.
     */
    private IntegerProperty maximumFailureCount = new SimpleIntegerProperty(this, "maximumFailureCount", Integer.MAX_VALUE);

    public final int getMaximumFailureCount() {
        return maximumFailureCount.get();
    }

    public final void setMaximumFailureCount(int value) {
        maximumFailureCount.set(value);
    }

    public final IntegerProperty maximumFailureCountProperty() {
        return maximumFailureCount;
    }

    /**
     * The current number of times the ScheduledService has failed. This is reset whenever the
     * ScheduledService is manually restarted.
     */
    private ReadOnlyIntegerWrapper currentFailureCount = new ReadOnlyIntegerWrapper(this, "currentFailureCount", 0);

    public final int getCurrentFailureCount() {
        return currentFailureCount.get();
    }

    public final ReadOnlyIntegerProperty currentFailureCountProperty() {
        return currentFailureCount.getReadOnlyProperty();
    }

    /**
     * The current cumulative period in use between iterations. This will be the same as <code>period</code>,
     * except after a failure, in which case the <code>easeOffDuration</code> will be added to the period
     * for each failure when. This is reset whenever the ScheduledService is manually restarted.
     */
    private ReadOnlyObjectWrapper<Duration> cumulativePeriod = new ReadOnlyObjectWrapper<Duration>(this, "cumulativePeriod", Duration.ZERO);

    public final Duration getCumulativePeriod() {
        return cumulativePeriod.get();
    }

    public final ReadOnlyObjectProperty<Duration> cumulativePeriodProperty() {
        return cumulativePeriod.getReadOnlyProperty();
    }

    /**
     * The last successfully computed value. During each iteration, the "value" of the ScheduledService will be
     * reset to null, as with any other Service. The "lastValue" however will be set to the most currently
     * successfully computed value, even across iterations. It is reset however whenever you manually call
     * reset or restart.
     */
    private ReadOnlyObjectWrapper<V> lastValue = new ReadOnlyObjectWrapper<V>(this, "lastValue", null);

    public final V getLastValue() {
        return lastValue.get();
    }

    public final ReadOnlyObjectProperty<V> lastValueProperty() {
        return lastValue.getReadOnlyProperty();
    }

    /**
     * The timestamp of the last time the thing was run
     */
    private long lastRunTime = 0L;
    private boolean freshStart = true;
    //    private boolean performIteration = false;
    private PauseTransition pauseTransition = null;

    @Override
    protected void succeeded() {
        super.succeeded();
        lastValue.set(getValue());
        // Reset the cumulative time
        Duration d = getPeriod();
        setCumulativePeriod(d == null ? Duration.ZERO : d);
        // Call the super implementation of reset, which will not cause us
        // to think this is a new fresh start.
        ScheduledService.this.superReset();
        // Fire it up!
        ScheduledService.this.start();
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        // Stop the pauseTransition if it exists
        if (pauseTransition != null) {
            pauseTransition.stop();
            pauseTransition = null;
        }
    }

    @Override
    protected void failed() {
        super.failed();
        // Stop the pauseTransition if it exists
        if (pauseTransition != null) {
            pauseTransition.stop();
            pauseTransition = null;
        }
        // Restart as necessary
        setCurrentFailureCount(getCurrentFailureCount() + 1);
        if (getMaximumFailureCount() > getCurrentFailureCount()) {
            // We've not yet maxed out the number of failures we can
            // encounter, so we're going to iterate

            Callback<ScheduledService<?>, Duration> func = getComputeEaseOff();
            if (func != null) {
                Duration d = func.call(this);
                setCumulativePeriod(d == null ? Duration.ZERO : d);
            }

            ScheduledService.this.superReset();
            // Fire it up!
            ScheduledService.this.start();
        } else {
            // We've maxed out, so do nothing and things will just stop.
        }
    }

    private void superReset() {
        super.reset();
    }

    @Override
    public void reset() {
        super.reset();
        Duration p = getPeriod();
        setCumulativePeriod(p == null ? Duration.ZERO : p);
        lastValue.set(null);
        currentFailureCount.set(0);
        lastRunTime = 0L;
        freshStart = true;
    }

    @Override
    protected void executeTask(final Task<V> task) {
        // TODO checkThread()
        if (freshStart) {
            // The pauseTransition should have concluded and been made null by this point.
            // If not, then somehow we were paused waiting for another iteration and
            // somebody caused the system to run again. However resetting things should
            // have cleared the transition.
            assert pauseTransition == null;

            // Pause for the "delay" amount of time then execute
            final Duration d = getDelay();
            if (d == null || d.toMillis() == 0) {
                // If the delay is zero or null, then just start immediately
                executeTask(task);
            } else {
                pauseTransition = new PauseTransition(d == null ? Duration.ZERO : d);
                pauseTransition.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        executeTaskNow(task);
                        pauseTransition = null;
                    }
                });
                pauseTransition.play();
            }
        } else {
            // We are executing as a result of an iteration, not a fresh start.
            // If the runPeriod (time between the last run and now) exceeds the cumulativePeriod, then
            // we need to execute immediately. Otherwise, we will pause until the cumulativePeriod has
            // been reached, and then run.
            double cumulative = getCumulativePeriod().toMillis(); // Can never be null.
            double runPeriod = System.currentTimeMillis() - lastRunTime;
            if (runPeriod < cumulative) {
                // Pause and then execute
                assert pauseTransition == null;
                pauseTransition = new PauseTransition(Duration.millis(cumulative - runPeriod));
                pauseTransition.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        executeTaskNow(task);
                        pauseTransition = null;
                    }
                });
                pauseTransition.play();
            } else {
                // Execute immediately
                executeTaskNow(task);
            }
        }
    }

    private void executeTaskNow(Task<V> task) {
        lastRunTime = System.currentTimeMillis();
        freshStart = false;
        super.executeTask(task);
    }

    void setCumulativePeriod(Duration value) {
        if (value == null) {
            throw new NullPointerException("cumulative period cannot be null");
        }
        cumulativePeriod.set(value);
    }

    void setCurrentFailureCount(int value) {
        currentFailureCount.set(value);
    }
}
