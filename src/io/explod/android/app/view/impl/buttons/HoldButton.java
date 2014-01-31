package io.explod.android.app.view.impl.buttons;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Button that fires onHold events periodically.
 * Register your listener with 'setOnHoldListener(OnHoldListener listener)'
 * Update the event rate with 'setFrequency(int millis)'
 *
 * @author evan
 */
public class HoldButton extends Button {

    /**
     * Interface for reacting to hold events.
     *
     * @author evan
     */
    public interface OnHoldListener {
        /**
         * Event fired at a specified frequency
         *
         * @param view
         */
        public void onHold(View view);
    }

    /**
     * Default hold-event fire frequency in milliseconds
     */
    public static final int DEFAULT_REPEAT_FRQ_MILLIS = 222;

    /**
     * on touch listener
     */
    private OnTouchListener touchListener;
    /**
     * current listener
     */
    private OnHoldListener listener;
    /**
     * current frequency at which to fire onHold-events
     */
    private int frqMillis = DEFAULT_REPEAT_FRQ_MILLIS;
    /**
     * Handler for queing onHold-event firing runnables.
     */
    private Handler handler;
    /**
     * onHold-event firing runnable.
     */
    private Runnable sendEvent;
    /**
     * current state of pressed-ness
     */
    private boolean pressed = false;

    /**
     * Standard view constructor for {@link HoldButton}
     *
     * @param context
     */
    public HoldButton(Context context) {
        super(context);
        this.start(context);
    }

    /**
     * Standard view constructor for {@link HoldButton}
     *
     * @param context
     * @param attrs
     */
    public HoldButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.start(context);
    }

    /**
     * Standard view constructor for {@link HoldButton}
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public HoldButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.start(context);
    }

    /**
     * Perform the construction of the object, no matter how this object was instantiated.
     *
     * @param context
     */
    protected void start(Context context) {
        this.handler = new Handler();
        this.sendEvent = new Runnable() {
            @Override
            public void run() {
                final HoldButton self = HoldButton.this;
                if (self.pressed) {
                    final OnHoldListener listener = self.getOnHoldListener();
                    if (listener != null) {
                        listener.onHold(self);
                    }
                    final int delay = self.getFrequency();
                    handler.postDelayed(this, delay);
                }
            }
        };
        this.wireEvents();
    }

    /**
     * Fired when touch events are down
     */
    protected synchronized void onPress() {
        this.pressed = true;
        this.handler.post(this.sendEvent);
    }

    /**
     * Fired when touch events are released
     */
    protected synchronized void onRelease() {
        this.pressed = false;
        this.handler.removeCallbacks(this.sendEvent);
    }

    /**
     * Wire the touch events needed to react to TOUCH-DOWN and TOUCH-UP events
     */
    protected void wireEvents() {
        super.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final HoldButton self = HoldButton.this;
                if (self.touchListener != null) {
                    final boolean alreadyHandled = self.touchListener.onTouch(view, event);
                    if (alreadyHandled) {
                        return true;
                    }
                }
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        self.onPress();
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        self.onRelease();
                        return true;
                }
                return false;
            }
        });
    }

    /**
     * Register a listener to react to hold events
     *
     * @param listener
     */
    public void setOnHoldListener(OnHoldListener listener) {
        this.listener = listener;
    }

    /**
     * Convenience method for setting frequency and a listener.
     *
     * @param frqMillis
     * @param listener
     */
    public void setOnHoldListener(int frqMillis, OnHoldListener listener) {
        this.setFrequency(frqMillis);
        this.setOnHoldListener(listener);
    }

    /**
     * Get the current OnHoldListener
     *
     * @return
     */
    protected OnHoldListener getOnHoldListener() {
        return this.listener;
    }

    @Override
    public void setOnTouchListener(OnTouchListener listener) {
        this.touchListener = listener;
    }

    /**
     * Set how often this button fires a hold event
     *
     * @param millis
     */
    public void setFrequency(int millis) {
        this.frqMillis = millis;
    }

    /**
     * Get how often this button fires a hold event
     *
     * @return millis
     */
    public int getFrequency() {
        return this.frqMillis;
    }

}
