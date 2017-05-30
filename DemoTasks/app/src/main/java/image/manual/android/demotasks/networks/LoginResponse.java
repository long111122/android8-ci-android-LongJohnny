package image.manual.android.demotasks.networks;

import com.google.gson.annotations.SerializedName;

/**
 * Created by EDGY on 5/23/2017.
 */

public class LoginResponse {
    @SerializedName("access_token")
    private String token;
    private String description;
    private String error;
    @SerializedName("status_code")
    private int status;

    public LoginResponse(String token, String description, String error, int status) {
        this.token = token;
        this.description = description;
        this.error = error;
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public String getDescription() {
        return description;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }
}
