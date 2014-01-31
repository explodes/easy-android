package io.explod.android.app.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Abstract implementation of {@link IEasyActivity} <br/>
 * <p/>
 * Includes the things that one must usually always do, like picking a layout resId, and setting fonts. <br/>
 * <p/>
 * {@link #onCreate(Bundle)} is specialized to automatically inflate layout resIds returned
 * by {@link #getLayoutId()} and set view fonts by calling {@link #setFonts(android.content.Context)}
 *
 * @author evan
 */
public abstract class EasyActivity extends Activity implements IEasyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final boolean inflated = this.inflate(this);
        if (inflated) {
            this.setFonts(this);
        }
    }

    @Override
    public boolean inflate(Context context) {
        int id = this.getLayoutId();
        if (id != 0) {
            this.setContentView(id);
            return true;
        } else {
            return false;
        }
    }

}
