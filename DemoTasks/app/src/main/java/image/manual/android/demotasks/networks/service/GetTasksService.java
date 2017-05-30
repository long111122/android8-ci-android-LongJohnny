package image.manual.android.demotasks.networks.service;


import java.util.List;
import java.util.Map;

import image.manual.android.demotasks.networks.GetTasksResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;

/**
 * Created by EDGY on 5/29/2017.
 */

public interface GetTasksService {
    @GET("task")
    Call<List<GetTasksResponse>> getTasks(@Header("Authorization") String token);
}
