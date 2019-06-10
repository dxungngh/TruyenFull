package daniel.com.truyenfull.data;

import android.support.annotation.NonNull;

import java.util.List;

import daniel.com.truyenfull.data.entity.Book;

public interface BooksDataSource {
    interface LoadBooksCallback {
        void onBooksLoaded(List<Book> bookList);

        void onDataNotAvailable();
    }

    interface GetBookCallback {
        void onBookLoaded(Book book);

        void onDataNotAvailable();
    }

    void getBooksInPage(@NonNull LoadBooksCallback callback, String link, int pageIndex);

    void getAllBooks(@NonNull LoadBooksCallback callback);

    void deleteAllBooks();

    void saveBook(Book book);
}
