package com.starwars.cst2335lab7;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.FrameLayout;
import java.net.URLEncoder;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.net.HttpURLConnection;
import java.net.URL;
import android.widget.ProgressBar;
import android.util.DisplayMetrics;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ProgressBar pBar;
    FrameLayout fLayout;
    RecyclerView rView;
    ArrayList<Result> resultss;
    int pixelsHeight;
    int pixelsWidth;
    double inchesScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pBar = (ProgressBar)findViewById(R.id.bar_progress);
        fLayout = (FrameLayout)findViewById(R.id.frameLayout);
        rView = (RecyclerView) findViewById(R.id.list);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        pixelsHeight = metrics.heightPixels;
        pixelsWidth = metrics.widthPixels;
        Log.d(TAG,"width "+pixelsWidth);
        Log.d(TAG,"height "+pixelsHeight);
        double width=(double)pixelsWidth/(double)metrics.xdpi;
        double x = Math.pow(width,2);
        double height=(double)pixelsHeight/(double)metrics.ydpi;
        double y = Math.pow(height,2);
        inchesScreen = Math.sqrt(x+y);
        Log.d(TAG,"screenInches "+inchesScreen);
        GetStarWarsAsyncTask getTask = new GetStarWarsAsyncTask();
        getTask.execute();
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, rView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View clickView, int pos) {
                        if (inchesScreen <=9){
                            fLayout.setVisibility(View.VISIBLE);
                            rView.setVisibility(View.GONE);
                        }
                        Result res = resultss.get(pos);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frameLayout, DetailsFragment.newInstance(res));
                        ft.commit();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );
    }

    private class GetStarWarsAsyncTask extends AsyncTask<Void, String, String>{

        private String callGetRequest() throws Exception {
            String responseFormat="json";
            StringBuilder stringBuilder = new StringBuilder("https://swapi.dev/api/people");
            stringBuilder.append("?format=");
            stringBuilder.append(URLEncoder.encode(responseFormat, "UTF-8"));
            URL createdObj = new URL(stringBuilder.toString());
            HttpURLConnection connection = (HttpURLConnection) createdObj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            System.out.println("\nURL Request Sent: " + connection.getURL().toString());
            System.out.println("Code Response: " + connection.getResponseCode());
            System.out.println("Message Received : " + connection.getResponseMessage());
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String currentLine;
            StringBuffer response = new StringBuffer();
            while ((currentLine = bufferedReader.readLine()) != null) {
                response.append(currentLine);
            }
            bufferedReader.close();
            System.out.println(response.toString());
            return response.toString();
        }

        @Override
        protected String doInBackground(Void... strings) {
            try {
                return callGetRequest();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                Log.d(TAG,"result "+result);
                pBar.setVisibility(View.GONE);
                StarWarApiResp data =new StarWarApiResp();
                Gson gson = new Gson();
                data= gson.fromJson(result,StarWarApiResp.class);
                resultss = data.getResults();
                rView.setAdapter(new MyStarWarRecyclerViewAdapter(data.getResults()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onBackPressed() {
        if (inchesScreen <= 9){
            if (fLayout.getVisibility() == View.VISIBLE){
                fLayout.setVisibility(View.GONE);
                rView.setVisibility(View.VISIBLE);
            }else{
                super.onBackPressed();
            }
        }else {
            super.onBackPressed();
        }
    }


}