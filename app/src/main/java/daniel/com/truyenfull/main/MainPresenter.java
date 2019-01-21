package daniel.com.truyenfull.main;

import android.content.Context;

import daniel.com.truyenfull.R;

public class MainPresenter implements MainContract.Presenter {
    private Context context;
    private MainContract.View view;

    public MainPresenter(Context context, MainContract.View view) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        String[] bookTypesList = this.context.getResources().getStringArray(
            R.array.book_types_array
        );
        this.changeBookType(bookTypesList[0]);
    }

    @Override
    public void changeBookType(String bookType) {
        this.view.setTitleOfToolBar(bookType);
        this.view.closeDrawer();
    }

    @Override
    public String getLinkOfBookType(final String bookType) {
        String[] linksOfBookTypesList = this.context.getResources().getStringArray(
            R.array.links_of_book_types_array
        );
        String[] bookTypesList = this.context.getResources().getStringArray(
            R.array.book_types_array
        );
        for (int i = 0; i < bookTypesList.length; i++) {
            if (bookTypesList[i] == bookType) {
                return linksOfBookTypesList[i];
            }
        }
        return linksOfBookTypesList[0];
    }
}
