package daniel.com.truyenfull.data;

import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import daniel.com.truyenfull.data.entity.Book;

public class BooksRepository implements BooksDataSource {
    private static final String TAG = BooksRepository.class.getSimpleName();
    private static BooksRepository INSTANCE = null;

    private final BooksDataSource booksRemoteDataSource;
    private final BooksDataSource booksLocalDataSource;

    private boolean isCacheDirty = true;
    private Map<String, Book> cachedBooks;

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
    public void getBooksInPage(@NonNull final LoadBooksCallback callback,
                               String link,
                               int pageIndex) {
        Preconditions.checkNotNull(callback);
        this.getBooksFromRemoteDataSource(callback, link, pageIndex);
    }

    @Override
    public void getAllBooks(@NonNull LoadBooksCallback callback) {
    }

    @Override
    public void saveBook(@NonNull Book book) {
        Preconditions.checkNotNull(book);
        this.booksLocalDataSource.saveBook(book);
    }

    @Override
    public void deleteAllBooks() {
        this.booksLocalDataSource.deleteAllBooks();
    }

    private void getBooksFromRemoteDataSource(@NonNull final LoadBooksCallback callback,
                                              String link,
                                              int pageIndex) {
        this.booksRemoteDataSource.getBooksInPage(
            new LoadBooksCallback() {
                @Override
                public void onBooksLoaded(List<Book> bookList) {
                    refreshLocalDataSource(bookList);
                    callback.onBooksLoaded(bookList);
                }

                @Override
                public void onDataNotAvailable() {
                    callback.onDataNotAvailable();
                }
            },
            link,
            pageIndex
        );
    }

    private void refreshLocalDataSource(final List<Book> bookList) {
        if (this.isCacheDirty || this.cachedBooks == null) {
            this.booksLocalDataSource.getAllBooks(
                new LoadBooksCallback() {
                    @Override
                    public void onBooksLoaded(List<Book> bookListOnDatabase) {
                        putAllBooksToCache(bookListOnDatabase);
                        checkBooksOnDatabaseToSave(bookList);
                    }

                    @Override
                    public void onDataNotAvailable() {
                    }
                }
            );
        } else {
            this.checkBooksOnDatabaseToSave(bookList);
        }
    }

    private void putAllBooksToCache(List<Book> bookListOnDatabase) {
        this.cachedBooks = new HashMap<>();
        if (bookListOnDatabase != null) {
            for (Book book : bookListOnDatabase) {
                this.cachedBooks.put(book.getLink(), book);
            }
        }
    }

    private void checkBooksOnDatabaseToSave(List<Book> bookList) {
        for (Book book : bookList) {
            boolean isExisted = false;
            String link = book.getLink();
            if (this.cachedBooks.containsKey(link)) {
                isExisted = true;
            }
            if (!isExisted) {
                this.booksLocalDataSource.saveBook(book);
                this.cachedBooks.put(link, book);
            }
        }
        this.isCacheDirty = false;
    }
}
