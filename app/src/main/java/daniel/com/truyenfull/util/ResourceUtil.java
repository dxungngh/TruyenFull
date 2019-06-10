package daniel.com.truyenfull.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class ResourceUtil {
    private static final String TAG = ResourceUtil.class.getSimpleName();

    public static final int getColor(Context context, int id) {
        try {
            final int version = Build.VERSION.SDK_INT;
            if (version >= 23) {
                return ContextCompat.getColor(context, id);
            } else {
                return context.getResources().getColor(id);
            }
        } catch (Exception e) {
            Log.e(TAG, "getColor", e);
            return 0;
        }
    }

    public static final Drawable getDrawable(Context context, int id) {
        try {
            final int version = Build.VERSION.SDK_INT;
            if (version >= 23) {
                return ContextCompat.getDrawable(context, id);
            } else {
                return context.getResources().getDrawable(id);
            }
        } catch (Exception e) {
            Log.e(TAG, "getDrawable", e);
            return null;
        }
    }
}
