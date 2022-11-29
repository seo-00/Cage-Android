package com.example.smart_cage_android;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RoRoActivity extends AppCompatActivity {

    String data;
    TextView temperature;
    XmlPullParser xpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ro_ro);

        temperature = (TextView)findViewById(R.id.result_temp);
    }

    public void OnClick(View v) {
        switch (v.getId()){
            case R.id.btn_update_val:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data = getJsonTempData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() { temperature.setText(data); }
                        });
                    }
                }).start();
                break;
        }
    }

    String getJsonTempData() {

        String response="";
        String con = "";

        String queryUrl= "http://182.221.64.162:7579/Mobius/dashboard_test_1/group1-test/ae1-test/temperature/4-20221125114215929";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            URL url= new URL(queryUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("X-M2M-RI", "dashboard_testing");
            connection.setRequestProperty("X-M2M-Origin", "dashboard_testing");

            int responseCode = connection.getResponseCode();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null)  {
                stringBuffer.append(inputLine);
            }
            bufferedReader.close();

            response = stringBuffer.toString();

            System.out.println(response);

            JSONObject jsonObject = new JSONObject(response).getJSONObject("m2m:cin");
            con = jsonObject.optString("con");

        }catch(Exception e){
            e.printStackTrace();
        }
        return con + " ℃";
    }

}