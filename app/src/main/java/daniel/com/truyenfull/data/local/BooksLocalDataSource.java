package daniel.com.truyenfull.data.local;

import android.support.annotation.NonNull;

import java.util.List;

import daniel.com.truyenfull.data.BooksDataSource;
import daniel.com.truyenfull.data.entity.Book;
import daniel.com.truyenfull.util.AppExecutors;

public class BooksLocalDataSource implements BooksDataSource {
    private static final String TAG = BooksLocalDataSource.class.getSimpleName();

    private static volatile BooksLocalDataSource INSTANCE;

    private BookDao bookDao;

    private AppExecutors appExecutors;

    public static BooksLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull BookDao bookDao) {
        if (INSTANCE == null) {
            synchronized (BooksLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BooksLocalDataSource(appExecutors, bookDao);
                }
            }
        }
        return INSTANCE;
    }

    private BooksLocalDataSource(@NonNull AppExecutors appExecutors,
                                 @NonNull BookDao bookDao) {
        this.appExecutors = appExecutors;
        this.bookDao = bookDao;
    }

    @Override
    public void getBooks(@NonNull final LoadBooksCallback callback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Book> bookList = bookDao.getBooks();
                appExecutors.mainThread().execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            if (bookList.isEmpty()) {
                                callback.onDataNotAvailable();
                            } else {
                                callback.onBooksLoaded(bookList);
                            }
                        }
                    }
                );
            }
        };
        this.appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveBook(Book book) {
    }

    @Override
    public void deleteAllBooks() {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                bookDao.deleteAllBooks();
            }
        };
        this.appExecutors.diskIO().execute(deleteRunnable);
    }
}
