package image.manual.android.demotasks.networks.service;

import image.manual.android.demotasks.networks.RegisterRequest;
import image.manual.android.demotasks.networks.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by EDGY on 5/26/2017.
 */

public interface RegisterService {
    @POST("register")
    Call<RegisterResponse> register
            (@Body RegisterRequest registerRequest);
}
