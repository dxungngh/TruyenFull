package daniel.com.truyenfull.main;

import daniel.com.truyenfull.baseMVP.BasePresenter;
import daniel.com.truyenfull.baseMVP.BaseView;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        boolean isDrawerOpen();

        void closeDrawer();

        void setTitleOfToolBar(String title);

        void setupNavigationView(String[] bookTypeList);

        void setSelectedTabLayout(boolean isNewBooksTab);
    }

    interface Presenter extends BasePresenter {
        void changeBookType(String bookType);

        String getLinkOfBookType(String bookType);
    }
}
