package daniel.com.truyenfull.screen.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import daniel.com.truyenfull.R;
import daniel.com.truyenfull.util.ActivityUtils;

public class LauncherActivity extends AppCompatActivity {
    private LauncherFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_launcher);
        this.drawLauncherFragment();
        this.createPresenter();
    }

    private void drawLauncherFragment() {
        this.fragment = LauncherFragment.getInstance();
        ActivityUtils.addFragmentToActivity(
            super.getSupportFragmentManager(),
            this.fragment,
            R.id.launcher_content
        );
    }

    private void createPresenter() {
        new LauncherPresenter(this.fragment);
    }
}
