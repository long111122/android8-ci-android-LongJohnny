package image.manual.android.demotasks.networks.service;

import image.manual.android.demotasks.networks.LoginRequest;
import image.manual.android.demotasks.networks.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by EDGY on 5/23/2017.
 */

public interface LoginService {
    @POST("login")
    Call<LoginResponse> login
            (@Body LoginRequest registerRequest);
}
