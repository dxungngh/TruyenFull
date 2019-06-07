package daniel.com.truyenfull.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import daniel.com.truyenfull.R;
import daniel.com.truyenfull.injection.Injection;
import daniel.com.truyenfull.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {
    private MainFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.drawMainFragment();
        this.createPresenter();
    }

    @Override
    public void onBackPressed() {
        if (this.fragment.isDrawerOpen()) {
            this.fragment.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    private void drawMainFragment() {
        this.fragment = MainFragment.getInstance();
        ActivityUtils.addFragmentToActivity(
            super.getSupportFragmentManager(),
            this.fragment,
            R.id.main_content
        );
    }

    private void createPresenter() {
        new MainPresenter(
            Injection.provideContext(super.getApplicationContext()),
            this.fragment,
            Injection.provideBooksRepository(super.getApplicationContext())
        );
    }
}
