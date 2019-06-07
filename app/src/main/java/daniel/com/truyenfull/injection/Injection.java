package daniel.com.truyenfull.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import daniel.com.truyenfull.data.BooksRepository;
import daniel.com.truyenfull.data.local.BooksDatabase;
import daniel.com.truyenfull.data.local.BooksLocalDataSource;
import daniel.com.truyenfull.data.remote.BooksRemoteDataSource;
import daniel.com.truyenfull.util.AppExecutors;

import static com.google.common.base.Preconditions.checkNotNull;

public class Injection {
    public static Context provideContext(@NonNull Context context) {
        return checkNotNull(context, "context cannot be null!");
    }

    public static BooksRepository provideBooksRepository(Context context) {
        BooksDatabase database = BooksDatabase.getInstance(context);
        return BooksRepository.getInstance(
            BooksRemoteDataSource.getInstance(),
            BooksLocalDataSource.getInstance(new AppExecutors(), database.bookDao())
        );
    }
}
