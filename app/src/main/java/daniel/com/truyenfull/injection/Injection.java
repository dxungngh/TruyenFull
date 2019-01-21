package daniel.com.truyenfull.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {
    public static Context provideContext(@NonNull Context context) {
        return checkNotNull(context, "context cannot be null!");
    }
}
