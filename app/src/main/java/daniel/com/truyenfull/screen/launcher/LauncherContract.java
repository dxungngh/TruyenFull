package daniel.com.truyenfull.screen.launcher;

import daniel.com.truyenfull.baseMVP.BasePresenter;
import daniel.com.truyenfull.baseMVP.BaseView;

public interface LauncherContract {
    interface View extends BaseView<Presenter> {
        void openMainScreen();
    }

    interface Presenter extends BasePresenter {
        void sleep();
    }
}
