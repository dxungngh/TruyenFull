package daniel.com.truyenfull.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import daniel.com.truyenfull.R;
import daniel.com.truyenfull.main.MainActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class LauncherFragment extends Fragment implements LauncherContract.View {
    private static LauncherFragment INSTANCE;
    private LauncherContract.Presenter presenter;

    public static LauncherFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LauncherFragment();
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_launcher, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.presenter.start();
    }

    @Override
    public void setPresenter(LauncherContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void openMainScreen() {
        Intent intent = new Intent(super.getActivity(), MainActivity.class);
        super.startActivity(intent);
        super.getActivity().finish();
    }
}
