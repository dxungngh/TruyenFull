package daniel.com.truyenfull.data;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import daniel.com.truyenfull.data.entity.Book;

public class BooksRepository implements BooksDataSource {
    private static final String TAG = BooksRepository.class.getSimpleName();
    private static BooksRepository INSTANCE = null;

    private final BooksDataSource booksRemoteDataSource;
    private final BooksDataSource booksLocalDataSource;

    Map<Integer, Book> cachedBooks;
    boolean cacheIsDirty = false;

    public static BooksRepository getInstance(BooksDataSource booksRemoteDataSource,
                                              BooksDataSource booksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new BooksRepository(booksRemoteDataSource, booksLocalDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private BooksRepository(@NonNull BooksDataSource booksRemoteDataSource,
                            @NonNull BooksDataSource booksLocalDataSource) {
        this.booksRemoteDataSource = Preconditions.checkNotNull(booksRemoteDataSource);
        this.booksLocalDataSource = Preconditions.checkNotNull(booksLocalDataSource);
    }

    @Override
    public void getBooks(@NonNull final LoadBooksCallback callback) {
        Preconditions.checkNotNull(callback);
        if (this.cachedBooks != null && !this.cacheIsDirty) {
            callback.onBooksLoaded(new ArrayList<>(this.cachedBooks.values()));
            return;
        }
        if (this.cacheIsDirty) {
            getBooksFromRemoteDataSource(callback);
        } else {
            this.booksLocalDataSource.getBooks(
                new LoadBooksCallback() {
                    @Override
                    public void onBooksLoaded(List<Book> bookList) {
                        refreshCache(bookList);
                        callback.onBooksLoaded(new ArrayList<>(cachedBooks.values()));
                    }

                    @Override
                    public void onDataNotAvailable() {
                        getBooksFromRemoteDataSource(callback);
                    }
                }
            );
        }
    }

    @Override
    public void saveBook(@NonNull Book book) {
        Preconditions.checkNotNull(book);
        this.booksLocalDataSource.saveBook(book);

        // Do in memory cache update to keep the app UI up to date
        if (this.cachedBooks == null) {
            this.cachedBooks = new LinkedHashMap<>();
        }
        this.cachedBooks.put(book.getId(), book);
    }

    @Override
    public void deleteAllBooks() {
        this.booksLocalDataSource.deleteAllBooks();
        if (this.cachedBooks == null) {
            this.cachedBooks = new LinkedHashMap<>();
        }
        this.cachedBooks.clear();
    }

    private void getBooksFromRemoteDataSource(@NonNull final LoadBooksCallback callback) {
        this.booksRemoteDataSource.getBooks(
            new LoadBooksCallback() {
                @Override
                public void onBooksLoaded(List<Book> bookList) {
                    refreshCache(bookList);
                    refreshLocalDataSource(bookList);
                    callback.onBooksLoaded(new ArrayList<>(cachedBooks.values()));
                }

                @Override
                public void onDataNotAvailable() {
                    callback.onDataNotAvailable();
                }
            }
        );
    }

    private void refreshCache(List<Book> bookList) {
        if (this.cachedBooks == null) {
            this.cachedBooks = new LinkedHashMap<>();
        }
        this.cachedBooks.clear();
        for (Book book : bookList) {
            this.cachedBooks.put(book.getId(), book);
        }
        this.cacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<Book> bookList) {
        this.booksLocalDataSource.deleteAllBooks();
        for (Book book : bookList) {
            this.booksLocalDataSource.saveBook(book);
        }
    }
}
