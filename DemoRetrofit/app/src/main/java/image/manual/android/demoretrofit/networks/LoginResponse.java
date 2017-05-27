package image.manual.android.demoretrofit.networks;

/**
 * Created by EDGY on 5/23/2017.
 */

public class LoginResponse {
    private String message;
    private int code;

    public LoginResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
