package daniel.com.truyenfull.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

import daniel.com.truyenfull.data.BooksDataSource;
import daniel.com.truyenfull.data.entity.Book;
import daniel.com.truyenfull.util.AppExecutors;

public class BooksRemoteDataSource implements BooksDataSource {
    private static final String TAG = BooksRemoteDataSource.class.getSimpleName();

    private static volatile BooksRemoteDataSource INSTANCE;

    private AppExecutors appExecutors;
    private BooksParser booksParser;

    public static BooksRemoteDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                    @NonNull BooksParser booksParser) {
        if (INSTANCE == null) {
            synchronized (BooksRemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BooksRemoteDataSource(appExecutors, booksParser);
                }
            }
        }
        return INSTANCE;
    }

    private BooksRemoteDataSource(@NonNull AppExecutors appExecutors,
                                  @NonNull BooksParser booksParser) {
        this.appExecutors = appExecutors;
        this.booksParser = booksParser;
    }

    @Override
    public void getBooksInPage(@NonNull final LoadBooksCallback callback,
                               final String link,
                               final int pageIndex) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(link).get();
                    List<Book> bookList = booksParser.parseBookList(document);
                    callback.onBooksLoaded(bookList);
                } catch (Exception e) {
                    Log.e(TAG, "getBooksInPage", e);
                    callback.onDataNotAvailable();
                }
            }
        };
        this.appExecutors.networkIO().execute(runnable);
    }

    @Override
    public void getAllBooks(@NonNull LoadBooksCallback callback) {
    }

    @Override
    public void saveBook(Book book) {
    }

    @Override
    public void deleteAllBooks() {
    }
}
