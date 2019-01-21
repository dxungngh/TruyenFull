package daniel.com.truyenfull.main;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void changeBookType(String bookType) {
        this.view.setTitleOfToolBar(bookType);
        this.view.closeDrawer();
    }
}
