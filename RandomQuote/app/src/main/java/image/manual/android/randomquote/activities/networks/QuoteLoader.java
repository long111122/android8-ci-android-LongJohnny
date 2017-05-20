package image.manual.android.randomquote.activities.networks;

import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import image.manual.android.randomquote.R;

/**
 * Created by EDGY on 5/16/2017.
 */

public class QuoteLoader extends AsyncTask<Void, Void, String>{

    private static final String TAG = "QuoteLoader";
    private final String QUOTE_URL = "http://quotesondesign.com/wp-json/posts?filter[orderby]=rand";
    private TextView tvQuote;

    public QuoteLoader(TextView tvQuote) {
        this.tvQuote = tvQuote;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        //1 : Open connection
        try {
            URL url = new URL(QUOTE_URL);
            //2 : Open Stream
            InputStream inputStream = url.openStream();

            //3 : Get data
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            String content = stringBuilder.toString();
            JSONArray jsonArray = new JSONArray(content);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String quoteContent = jsonObject.getString("content");
            Log.d(TAG,"fking this : "+ quoteContent);
            return quoteContent;
            //4 : Decode data

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        tvQuote.setText(Html.fromHtml(s));
        tvQuote.setBackgroundResource(R.color.colorGrey);
    }
}
