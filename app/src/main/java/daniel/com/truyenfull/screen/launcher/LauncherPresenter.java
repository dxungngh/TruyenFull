package daniel.com.truyenfull.screen.launcher;

import android.os.Handler;

public class LauncherPresenter implements LauncherContract.Presenter {
    private LauncherContract.View view;
    private final int SLEEP_TIME = 2000;

    public LauncherPresenter(LauncherContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        this.sleep();
    }

    @Override
    public void sleep() {
        final Handler handler = new Handler();
        handler.postDelayed(
            new Runnable() {
                @Override
                public void run() {
                    view.openMainScreen();
                }
            },
            SLEEP_TIME
        );
    }
}
