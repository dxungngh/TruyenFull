package daniel.com.truyenfull.main;

import daniel.com.truyenfull.BasePresenter;
import daniel.com.truyenfull.BaseView;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        boolean isDrawerOpen();

        void closeDrawer();

        void setTitleOfToolBar(String title);
    }

    interface Presenter extends BasePresenter {
        void changeBookType(String bookType);
    }
}
