package com.saumil.apidemo.AsyncTask;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.saumil.apidemo.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTask extends AppCompatActivity {

    ProgressBar progressBar;
    TextView details;
    EditText emailaddress;
    String API_KEY,API_URL;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        details = (TextView) findViewById(R.id.details);
        emailaddress = (EditText) findViewById(R.id.emailaddress);
        API_KEY = "mpBZ4P8z7zhvpiVeGhPxrt41fdff1ZSg";
        //API_URL = "https://api.fullcontact.com/v3/person.enrich";
        API_URL = "https://api.fullcontact.com/v2/person.json?";
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RetriveFeedTask().execute();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    class RetriveFeedTask extends android.os.AsyncTask<Void, Void, String>{
        private Exception exception;

        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
            details.setText("");
        }

        @Override
        protected String doInBackground(Void... urls) {
            String Email = emailaddress.getText().toString();

            try{
                URL url = new URL(API_URL + "email=" + emailaddress + "&apiKey=" + API_KEY);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch (Exception e){
                Log.e("ERROR",e.getMessage(),e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            details.setText(response);
        }
    }
}


//import org.apache.http.client.fluent.Request;
//
//        Request.Post("https://api.fullcontact.com/v3/person.enrich")
//        .addHeader("Authorization","Bearer {Your API Key}")
//        .execute();