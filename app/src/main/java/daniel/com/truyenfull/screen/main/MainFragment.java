package daniel.com.truyenfull.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import daniel.com.truyenfull.R;
import daniel.com.truyenfull.data.entity.Book;
import daniel.com.truyenfull.screen.bookDetail.BookDetailActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainFragment extends Fragment
    implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {
    private static final String TAG = MainFragment.class.getSimpleName();

    private static MainFragment INSTANCE;
    private MainContract.Presenter presenter;

    @BindView(R.id.fragment_main_drawer_layout)
    protected DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.fragment_main_nav_view)
    protected NavigationView navigationView;
    @BindView(R.id.fragment_main_tab_layout)
    protected TabLayout tabLayout;
    @BindView(R.id.fragment_main_book_list_recycler_view)
    protected RecyclerView bookListRecyclerView;
    @BindView(R.id.fragment_main_progress_bar)
    protected ProgressBar progressBar;

    private BooksAdapter booksAdapter;

    public static MainFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MainFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        this.drawComponentViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.start();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String bookType = item.getTitle().toString();
        this.presenter.changeBookType(bookType);
        return true;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public boolean isDrawerOpen() {
        return this.drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public void closeDrawer() {
        this.drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void setTitleOfToolBar(String title) {
        this.toolbar.setTitle(title);
    }

    @Override
    public void setupNavigationView(String[] bookTypeList) {
        this.navigationView.setNavigationItemSelectedListener(this);
        Menu menu = this.navigationView.getMenu();
        for (String bookTypeTitle : bookTypeList) {
            menu.add(bookTypeTitle);
        }
    }

    @Override
    public void setSelectedTabLayout(boolean isNewBooksTab) {
        TabLayout.Tab tab = this.tabLayout.getTabAt(0);
        if (!isNewBooksTab) {
            tab = this.tabLayout.getTabAt(1);
        }
        tab.select();
    }

    @Override
    public void drawBookList(final List<Book> bookList) {
        super.getActivity().runOnUiThread(
            new Runnable() {
                @Override
                public void run() {
                    if (booksAdapter == null) {
                        booksAdapter = new BooksAdapter(presenter, bookList);
                        bookListRecyclerView.setAdapter(booksAdapter);
                    } else {
                        booksAdapter.notifyDataSetChanged(bookList);
                    }
                }
            }
        );
    }

    @Override
    public void showLoadingDialog() {
        super.getActivity().runOnUiThread(
            new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        );
    }

    @Override
    public void hideLoadingDialog() {
        super.getActivity().runOnUiThread(
            new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            }
        );
    }

    @Override
    public void showError() {

    }

    @Override
    public void openBookDetailScreen(Book book) {
        Intent intent = new Intent(super.getActivity(), BookDetailActivity.class);
        intent.putExtra("BOOK", book);
        super.startActivity(intent);
    }

    private void drawComponentViews() {
        AppCompatActivity activity = (AppCompatActivity) super.getActivity();
        activity.setSupportActionBar(this.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            activity,
            this.drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        );
        this.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        this.initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(super.getActivity());
        this.bookListRecyclerView.setLayoutManager(layoutManager);
    }
}
