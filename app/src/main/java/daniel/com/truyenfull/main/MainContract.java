package daniel.com.truyenfull.main;

import java.util.List;

import daniel.com.truyenfull.baseMVP.BasePresenter;
import daniel.com.truyenfull.baseMVP.BaseView;
import daniel.com.truyenfull.data.entity.Book;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        boolean isDrawerOpen();

        void closeDrawer();

        void setTitleOfToolBar(String title);

        void setupNavigationView(String[] bookTypeList);

        void setSelectedTabLayout(boolean isNewBooksTab);

        void drawBookList(List<Book> bookList);

        void showError();
    }

    interface Presenter extends BasePresenter {
        void changeBookType(String bookType);

        String getLinkOfBookType(String bookType);
    }
}
