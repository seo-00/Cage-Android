package com.example.smart_cage_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.example.smart_cage_android.databinding.ActivityCageManagementBinding;
import com.example.smart_cage_android.databinding.ActivityRoRoBinding;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CageManagementActivity extends AppCompatActivity {

    ActivityCageManagementBinding viewBinding;

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityCageManagementBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        viewBinding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data = getJsonTempData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() { viewBinding.TextTest.setText(data); }
                        });
                    }
                }).start();
            }
        });


    }

    String getJsonTempData() {

        String response="";
        String mid = "";
        StringBuffer result = new StringBuffer();

        String queryUrl= "http://182.221.64.162:7579/Mobius/test-grp1";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            URL url= new URL(queryUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("X-M2M-RI", "dashboard_testing");
            connection.setRequestProperty("X-M2M-Origin", "dashboard_testing");
            connection.setRequestProperty("Content-Type", "application/vnd.onem2m-res+json; ty=9");

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

            JSONObject jsonObject = new JSONObject(response).getJSONObject("m2m:grp");
            mid = jsonObject.optString("mid");

            String[] mid_list = mid.toString().split(","); //쉼표를 기준으로 문자열 나누기

            for (int i = 0; i < mid_list.length; i++){
                if (i == 0)
                    mid_list[i] = mid_list[i].substring(10, mid_list[i].length() - 1);
                else if (i == mid_list.length - 1)
                    mid_list[i] = mid_list[i].substring(9, mid_list[i].length() - 2);
                else
                    mid_list[i] = mid_list[i].substring(9, mid_list[i].length() - 1);

                result.append(mid_list[i]);
                result.append("\n");

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}