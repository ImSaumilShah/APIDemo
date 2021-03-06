package com.saumil.apidemo.Volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.saumil.apidemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Volley extends AppCompatActivity {

    private static final String JSON_URL = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
    ListView listView;
    List<Hero> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        listView = (ListView) findViewById(R.id.listview);
        heroList = new ArrayList<>();

        loadHeroList();
    }

    private void loadHeroList() {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.INVISIBLE);

                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("heroes");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject heroobject = jsonArray.getJSONObject(i);

                        Hero hero = new Hero(heroobject.getString("name"), heroobject.getString("imageurl"));

                        heroList.add(hero);
                    }

                    ListViewAdapter adapter = new ListViewAdapter(heroList, getApplicationContext());
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Volley.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

       // RequestQueue requestQueue = new RequestQueue();
       // requestQueue.add(stringRequest);
    }
}
