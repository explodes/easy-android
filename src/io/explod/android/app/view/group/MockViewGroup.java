package io.explod.android.app.view.group;

import android.view.View;

/**
 * Class to pretend that views are in a group and perform actions on all of them at once.
 * <p/>
 * Created by evan on 3/6/14.
 */
public class MockViewGroup {

    private View[] views;

    /**
     * Construct this group without views.
     */
    public MockViewGroup() {
    }

    /**
     * Construct this group with the given views
     *
     * @param views Default views for this group
     */
    public MockViewGroup(View... views) {
        this.views = views;
    }

    /**
     * @return Return the views in this group
     */
    public View[] getViews() {
        return this.views;
    }

    /**
     * @param views Set the views in this group
     */
    public void setViews(View[] views) {
        this.views = views;
    }

    /**
     * @param visibility Apply this visibility to all views in this group
     */
    public void setVisibility(int visibility) {
        if (this.views != null) {
            for (final View view : this.views) {
                view.setVisibility(visibility);
            }
        }
    }

    /**
     * @param alpha Apply this alpha to all view views in this group
     */
    public void setAlpha(float alpha) {
        if (this.views != null) {
            for (final View view : this.views) {
                view.setAlpha(alpha);
            }
        }
    }
}
