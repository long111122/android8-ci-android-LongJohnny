package image.manual.android.demoretrofit.networks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by EDGY on 5/29/2017.
 */

public class GetTasks extends AsyncTask<Void, Void, String> {
    private static final String HEADER_TOKEN = "JWT ";
    private static final String TAG = "GetTasks";
    private String token;

    public GetTasks() {
    }

    public GetTasks setToken(String token) {
        this.token = HEADER_TOKEN + token;
        return this;
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            //1. Open connection
            URL url = new URL(this.token);

            //2. open stream
            InputStream inputStream = url.openStream();

            //3. get tasks
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            String content = stringBuilder.toString();
            JSONArray jsonArray = new JSONArray(content);
            for (int i = 0; i < jsonArray.length(); i++){
                Log.d(TAG, jsonArray.get(i).toString());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
