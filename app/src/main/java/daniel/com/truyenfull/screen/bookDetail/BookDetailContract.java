package daniel.com.truyenfull.screen.bookDetail;

import daniel.com.truyenfull.baseMVP.BasePresenter;
import daniel.com.truyenfull.baseMVP.BaseView;

public interface BookDetailContract {
    interface View extends BaseView<Presenter> {
        void setTitleOfToolBar(String title);
    }

    interface Presenter extends BasePresenter {
    }
}
