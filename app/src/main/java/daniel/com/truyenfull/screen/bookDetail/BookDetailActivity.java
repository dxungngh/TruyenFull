package daniel.com.truyenfull.screen.bookDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import daniel.com.truyenfull.R;
import daniel.com.truyenfull.data.entity.Book;
import daniel.com.truyenfull.injection.Injection;
import daniel.com.truyenfull.util.ActivityUtils;

public class BookDetailActivity extends AppCompatActivity {
    private BookDetailFragment fragment;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        this.book = (Book) super.getIntent().getSerializableExtra("BOOK");
        this.drawTitle();
        this.drawMainFragment();
        this.createPresenter();
    }

    private void drawTitle() {

    }

    private void drawMainFragment() {
        this.fragment = BookDetailFragment.getInstance();
        ActivityUtils.addFragmentToActivity(
            super.getSupportFragmentManager(),
            this.fragment,
            R.id.book_detail_content
        );
    }

    private void createPresenter() {
        new BookDetailPresenter(
            Injection.provideContext(super.getApplicationContext()),
            this.fragment,
            Injection.provideBooksRepository(super.getApplicationContext()),
            this.book
        );
    }
}
