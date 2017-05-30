package image.manual.android.demotasks.fragments;


import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import image.manual.android.demotasks.BuildConfig;
import image.manual.android.demotasks.R;
import image.manual.android.demotasks.adapters.TaskPagerAdapter;
import image.manual.android.demotasks.networks.GetTasksResponse;
import image.manual.android.demotasks.networks.RetrofitFactory;
import image.manual.android.demotasks.networks.service.GetTasksService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetTasksFragment extends Fragment {

    @BindView(R.id.rc_task)
    RecyclerView recyclerTask;

    private static final String TAG = "GetTasksFragment";
    private static final String HEADER_TOKEN = "JWT ";
    private List<GetTasksResponse> getTasksResponses = new ArrayList<>();
    private TaskPagerAdapter taskPagerAdapter;
    private String token;

    public GetTasksFragment() {
        // Required empty public constructor
    }

    public GetTasksFragment setToken(String token) {
        this.token = HEADER_TOKEN + token;
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_tasks, container, false);
        setupUI(view);
        getTasks();
        return view;
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        taskPagerAdapter = new TaskPagerAdapter(getTasksResponses, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerTask.setAdapter(taskPagerAdapter);
        recyclerTask.setLayoutManager(manager);
    }

    private void getTasks() {
        RetrofitFactory
                .getInstance()
                .createService(GetTasksService.class)
                .getTasks(this.token)
                .enqueue(new Callback<List<GetTasksResponse>>() {
                    @Override
                    public void onResponse(Call<List<GetTasksResponse>> call, retrofit2.Response<List<GetTasksResponse>> response) {
                        for(GetTasksResponse task : response.body()){
                            getTasksResponses.add(new GetTasksResponse(task.getName(),
                                    task.getLocalID(),
                                    task.getColor(),
                                    task.getName(),
                                    task.isDone(),
                                    task.getPaymentPerHour(),
                                    task.getDueDate()));
                        }
//                        Toast.makeText(GetTasksFragment.this.getContext(),"OK : " + getTasksResponses.size(),Toast.LENGTH_SHORT).show();
                        taskPagerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<GetTasksResponse>> call, Throwable t) {

                    }
                });
    }
}

//        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
//        okBuilder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                Request.Builder reBuilder = request.newBuilder().header("Authorization", token);
//                return chain.proceed(reBuilder.build());
//            }
//        });
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl("http://a-task.herokuapp.com/api/")
//                .client(okBuilder.build())
//                .addConverterFactory(GsonConverterFactory.create());
//        Retrofit retrofit = builder.build();
//        GetTasksService getTasksService = retrofit.create(GetTasksService.class);
//        Call<List<GetTasksResponse>> getTasksResponseCall = getTasksService.getTasks(token);
//        getTasksResponseCall.enqueue(new Callback<List<GetTasksResponse>>() {
//            @Override
//            public void onResponse(Call<List<GetTasksResponse>> call, retrofit2.Response<List<GetTasksResponse>> response) {
//                Log.d(TAG,response.body().get(0).getName());
//            }
//
//            @Override
//            public void onFailure(Call<List<GetTasksResponse>> call, Throwable t) {
//
//            }
//        });