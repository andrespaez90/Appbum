package appbum.mobile.com.appbum.ui.models.factories;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;

import java.lang.annotation.Retention;

import appbum.mobile.com.appbum.ui.models.events.ColoredSnackbar;

import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

public class SnackBarFactory {

    public static final String TYPE_INFO = "type_info";
    public static final String TYPE_ERROR = "type_error";

    public static Snackbar getSnackBar(@SnackBarType String snackBarType, @NonNull View view, @StringRes int stringId, int duration) {
        Snackbar snackbar = Snackbar.make(view, stringId, duration);
        return createSnackBar(snackBarType, snackbar);
    }

    public static Snackbar getSnackBar(@SnackBarType String snackBarType, @NonNull View view, @NonNull CharSequence text, int duration) {
        Snackbar snackbar = Snackbar.make(view, text, duration);
        return createSnackBar(snackBarType, snackbar);
    }

    public static Snackbar getSnackBar(@ColorInt int color, @NonNull View view, @NonNull CharSequence text, int duration) {
        Snackbar snackbar = Snackbar.make(view, text, duration);
        return createSnackBar(color, snackbar);
    }

    public static Snackbar getSnackBarWithAction(@SnackBarType String snackBarType, @NonNull View view,
                                                 @NonNull CharSequence text, String actionText,
                                                 View.OnClickListener onClickListener) {
        return createSnackBar(snackBarType, Snackbar.make(view, text, LENGTH_INDEFINITE)
                .setAction(!TextUtils.isEmpty(actionText) ? actionText : "Reintentar", onClickListener)
                .setActionTextColor(Color.WHITE));
    }

    private static Snackbar createSnackBar(@SnackBarType String snackBarType, Snackbar snackbar) {
        switch (snackBarType) {
            case TYPE_INFO:
                return ColoredSnackbar.draw(snackbar, ContextCompat.getColor(snackbar.getContext(), android.R.color.holo_green_dark));
            case TYPE_ERROR:
                return ColoredSnackbar.draw(snackbar, ContextCompat.getColor(snackbar.getContext(), android.R.color.holo_red_dark));
            default:
                return snackbar;
        }
    }

    private static Snackbar createSnackBar(int color, Snackbar snackbar) {
        return ColoredSnackbar.draw(snackbar, color);
    }

    @Retention(SOURCE)
    @StringDef({TYPE_INFO, TYPE_ERROR})
    public @interface SnackBarType {
    }

}
