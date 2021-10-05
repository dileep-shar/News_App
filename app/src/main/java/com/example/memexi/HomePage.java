package com.example.memexi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomePage extends AppCompatActivity {
    View create_meme;
    View profile;
    View rate_us;
    View settings;
    RecyclerViewAdapter rad ;
    ArrayList<News> newsList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_home_page);
        create_meme = findViewById(R.id.goto_create_meme);
        //sett rate us about us profile
        profile    = findViewById(R.id.goto_my_profile);
        rate_us    = findViewById(R.id.goto_rate_us);
        settings   = findViewById(R.id.goto_settings);
        recyclerView = (RecyclerView) findViewById(R.id.news_recycler);


        newsList = new ArrayList<>();

         fetchData();
        //sab line me lag jao bhai sabko milega ek ek karke barabar milega

       buildRecyclerView();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic,menu);
        return  true;

    }
    private void fetchData(){
        String url = "https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=57f43207c8ea4965b7242aec2ff37bbf";
        RequestQueue requestQueue;
        requestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    int results = response.getInt("totalResults");
                    Log.d("poor", "Results found: " + results);
                    JSONArray newsArticles = response.getJSONArray("articles");

 //                 try getString if not worked
                    for(int i = 0;i < newsArticles.length();i++) {
                        JSONObject jsonObject = newsArticles.getJSONObject(i);
                        if(jsonObject.get("urlToImage").toString().equals("null")){
                        continue;
                        }
                        if(jsonObject.get("url").toString().equals("null")){
                            continue;
                        }
                        News n = new News();
                        n.setAuthor(jsonObject.get("author").toString());
                        n.setHeadline(jsonObject.getString("description"));
                        n.setUrl(jsonObject.getString("url"));
                        n.setImage_url(jsonObject.getString("urlToImage"));
                        newsList.add(n);
                    }
                   buildRecyclerView();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(HomePage.this, "Maha inside Fail", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("errror","errrr");
                Toast.makeText(HomePage.this, "Faill", Toast.LENGTH_SHORT).show();
            }

        }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("User-Agent", "Mozilla/5.0");

                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
    private void buildRecyclerView() {

        // initializing our adapter class.
        rad = new RecyclerViewAdapter( newsList,HomePage.this);

        // adding layout manager
        // to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        recyclerView.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        recyclerView.setAdapter(rad);
    }
    private void onItemClicked(News n){

    }
}