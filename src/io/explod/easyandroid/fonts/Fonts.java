package io.explod.easyandroid.fonts;

import io.explod.easyandroid.iface.ISetTypeface;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Fonts {

	public static class InvalidViewException extends Exception {
		private static final long serialVersionUID = 2385647224255620950L;

		public InvalidViewException(String message) {
			super(message);
		}
	}

	public static final Typeface loadFont(String familyName, int style) {
		final Typeface font = Typeface.create(familyName, style);
		return font;
	}

	public static final Typeface loadFont(String familyName) {
		final Typeface font = loadFont(familyName, Typeface.NORMAL);
		return font;
	}

	public static final Typeface loadFontFromAssets(Context context, String path) {
		final AssetManager assets = context.getAssets();
		final Typeface font = Typeface.createFromAsset(assets, path);
		return font;
	}

	public static final void setViewFonts(Typeface font, View root, int... viewIds) throws InvalidViewException {
		for (final int viewId : viewIds) {
			final View view = root.findViewById(viewId);
			if (view instanceof TextView) {
				((TextView) view).setTypeface(font);
			} else if (view instanceof Button) {
				((Button) view).setTypeface(font);
			} else if (view instanceof EditText) {
				((EditText) view).setTypeface(font);
			} else if (view instanceof ISetTypeface) {
				((ISetTypeface) view).setTypeface(font);
			} else {
				final String message = String.format("Invalid view: #%x: %s", viewId, view);
				throw new InvalidViewException(message);
			}
		}
	}

}
