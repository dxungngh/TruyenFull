package daniel.com.truyenfull.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import daniel.com.truyenfull.R;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.main_drawer_layout)
    protected DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.main_nav_view)
    protected NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.drawComponentViews();
    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String bookType = item.getTitle().toString();
        this.toolbar.setTitle(bookType);
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void drawComponentViews() {
        super.setSupportActionBar(this.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this,
            this.drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        );
        this.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        this.navigationView.setNavigationItemSelectedListener(this);
        Menu menu = this.navigationView.getMenu();
        String[] bookTypesList = super.getResources().getStringArray(R.array.book_types_array);
        for (String bookTypeTitle : bookTypesList) {
            menu.add(bookTypeTitle);
        }
    }
}
