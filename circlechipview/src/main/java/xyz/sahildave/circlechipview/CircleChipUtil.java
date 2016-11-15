package xyz.sahildave.circlechipview;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by sahil on 15/11/16.
 */

public class CircleChipUtil {
    public static int getResourceId(Resources res, String var, String resType, String packageName) {
        try {
            return res.getIdentifier(var, resType, packageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @ColorInt
    public static int getThemeColor(@NonNull final Context context, @AttrRes final int attributeColor) {
        final TypedValue value = new TypedValue();
        context.getTheme ().resolveAttribute (attributeColor, value, true);
        return value.data;
    }

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * displayMetrics.density);
    }

    public static int pxToDp(Context context, int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / displayMetrics.density);
    }
}
