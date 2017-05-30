package image.manual.android.demoretrofit.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import image.manual.android.demoretrofit.networks.GetTasks;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetTasksFragment extends Fragment {

    private static final String TAG = "GetTasksFragment";
    private String token;

    public GetTasksFragment() {
        // Required empty public constructor
    }

    public GetTasksFragment setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_tasks, container, false);
        findReferences(view);
        setupUI();
        getTasks();
        return view;
    }

    private void findReferences(View view) {

    }

    private void setupUI() {
    }

    private void getTasks() {
        new GetTasks().setToken(token).execute();
    }
}
