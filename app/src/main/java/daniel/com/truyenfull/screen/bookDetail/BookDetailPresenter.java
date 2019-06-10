package daniel.com.truyenfull.screen.bookDetail;

import android.content.Context;

import daniel.com.truyenfull.data.BooksRepository;
import daniel.com.truyenfull.data.entity.Book;

public class BookDetailPresenter implements BookDetailContract.Presenter {
    private static final String TAG = BookDetailPresenter.class.getSimpleName();

    private Context context;
    private BookDetailContract.View view;
    private BooksRepository booksRepository;
    private Book book;

    public BookDetailPresenter(Context context,
                               BookDetailContract.View view,
                               BooksRepository booksRepository,
                               Book book) {
        this.context = context;
        this.view = view;
        this.booksRepository = booksRepository;
        this.view.setPresenter(this);
        this.book = book;
    }

    @Override
    public void start() {
        this.view.setTitleOfToolBar(this.book.getName());
    }
}
