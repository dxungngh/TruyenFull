package daniel.com.truyenfull.launcher;

import daniel.com.truyenfull.BasePresenter;
import daniel.com.truyenfull.BaseView;

public interface LauncherContract {
    interface View extends BaseView<Presenter> {
        void openMainScreen();
    }

    interface Presenter extends BasePresenter {
        void sleep();
    }
}
