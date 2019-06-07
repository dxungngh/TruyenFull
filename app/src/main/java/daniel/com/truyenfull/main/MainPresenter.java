package daniel.com.truyenfull.main;

import android.content.Context;

import daniel.com.truyenfull.R;
import daniel.com.truyenfull.data.BooksRepository;

public class MainPresenter implements MainContract.Presenter {
    private Context context;
    private MainContract.View view;
    private BooksRepository booksRepository;

    private boolean isNewBooksTab = true;
    private String currentBookType = "";

    public MainPresenter(Context context, MainContract.View view, BooksRepository booksRepository) {
        this.context = context;
        this.view = view;
        this.booksRepository = booksRepository;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        String[] bookTypesList = this.context.getResources().getStringArray(
            R.array.book_types_array
        );
        this.view.setupNavigationView(bookTypesList);
        this.changeBookType(bookTypesList[0]);
        this.getBookList();
    }

    @Override
    public void changeBookType(String bookType) {
        this.currentBookType = bookType;
        this.view.setSelectedTabLayout(this.isNewBooksTab);
        this.view.setTitleOfToolBar(this.currentBookType);
        this.view.closeDrawer();
    }

    @Override
    public String getLinkOfBookType(final String bookType) {
        String[] linksOfBookTypesList = this.context.getResources().getStringArray(
            R.array.links_of_book_types_array
        );
        String[] bookTypesList = this.context.getResources().getStringArray(
            R.array.book_types_array
        );
        for (int i = 0; i < bookTypesList.length; i++) {
            if (bookTypesList[i] == bookType) {
                return linksOfBookTypesList[i];
            }
        }
        return linksOfBookTypesList[0];
    }

    private void getBookList() {
    }
}
