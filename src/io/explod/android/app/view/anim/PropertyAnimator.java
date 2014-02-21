package io.explod.android.app.view.anim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.view.animation.Interpolator;

/**
 * Created by evan on 1/29/14.
 * <p/>
 * ObjectAnimator with chained calls.
 */
public class PropertyAnimator {

    // static

    public static PropertyAnimator ofFloat(Object target, String prop, float toValue) {
        return new PropertyAnimator(ObjectAnimator.ofFloat(target, prop, toValue));
    }

    public static PropertyAnimator ofFloat(Object target, String prop, float... values) {
        return new PropertyAnimator(ObjectAnimator.ofFloat(target, prop, values));
    }

    public static PropertyAnimator ofInt(Object target, String prop, int toValue) {
        return new PropertyAnimator(ObjectAnimator.ofInt(target, prop, toValue));
    }

    public static PropertyAnimator ofInt(Object target, String prop, int... values) {
        return new PropertyAnimator(ObjectAnimator.ofInt(target, prop, values));
    }

    // instance

    /* default */ ObjectAnimator anim;

    private PropertyAnimator(ObjectAnimator anim) {
        this.anim = anim;
    }

    public PropertyAnimator setDuration(long duration) {
        this.anim.setDuration(duration);
        return this;
    }

    public PropertyAnimator setEvaluator(TypeEvaluator<?> evaluator) {
        this.anim.setEvaluator(evaluator);
        return this;
    }

    public PropertyAnimator setInterpolator(Interpolator interpolator) {
        this.anim.setInterpolator(interpolator);
        return this;
    }

    public PropertyAnimator setStartDelay(long delay) {
        this.anim.setStartDelay(delay);
        return this;
    }

    public PropertyAnimator addListener(Animator.AnimatorListener listener) {
        this.anim.addListener(listener);
        return this;
    }

    public PropertyAnimator cancel() {
        this.anim.cancel();
        return this;
    }

    public void start() {
        this.anim.start();
    }
}
