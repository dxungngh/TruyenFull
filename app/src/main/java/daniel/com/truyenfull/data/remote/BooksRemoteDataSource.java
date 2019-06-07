package daniel.com.truyenfull.data.remote;

import android.support.annotation.NonNull;

import daniel.com.truyenfull.data.BooksDataSource;
import daniel.com.truyenfull.data.entity.Book;

public class BooksRemoteDataSource implements BooksDataSource {
    private static final String TAG = BooksRemoteDataSource.class.getSimpleName();

    private static volatile BooksRemoteDataSource INSTANCE;

    public static BooksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (BooksRemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BooksRemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    private BooksRemoteDataSource() {
    }

    @Override
    public void getBooks(@NonNull LoadBooksCallback callback) {
    }

    @Override
    public void saveBook(Book book) {
    }

    @Override
    public void deleteAllBooks() {
    }
}
