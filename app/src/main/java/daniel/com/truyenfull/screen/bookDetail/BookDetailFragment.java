package daniel.com.truyenfull.screen.bookDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import daniel.com.truyenfull.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class BookDetailFragment extends Fragment implements BookDetailContract.View {
    private static final String TAG = BookDetailFragment.class.getSimpleName();

    private static BookDetailFragment INSTANCE;
    private BookDetailContract.Presenter presenter;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    public static BookDetailFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookDetailFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        ButterKnife.bind(this, view);
        this.drawComponentViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.start();
    }

    @Override
    public void setPresenter(BookDetailContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void setTitleOfToolBar(String title) {
        this.toolbar.setTitle(title);
    }

    private void drawComponentViews() {
    }
}
