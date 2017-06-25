package com.example.jeff.michaeljackson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    private final String my_url = "https://itunes.apple.com/search?term=Michael+jackson";
    private ArrayList<Collection> results;
    private ArrayList<Collection> trackList;
    private ArrayList<String> idList;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        results = new ArrayList<Collection>();
        trackList = new ArrayList<Collection>();
        idList = new ArrayList<>();
        getJson();
    }
    //getjson data from url
    private void getJson() {

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, my_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray mjList = response.getJSONArray("results");
                    for(int i = 0; i< mjList.length(); i++) {
                        JSONObject jObj = mjList.getJSONObject(i);
                        String collection = jObj.getString("collectionName");
                        String tPrice = jObj.getString("trackPrice");
                        String cPrice = jObj.getString("collectionPrice");
                        String date = jObj.getString("releaseDate");
                        String track = jObj.getString("trackName");
                        String image = jObj.getString("artworkUrl100");
                        String genre = jObj.getString("primaryGenreName");
                        String id = jObj.getString("collectionId");
                        //checking for duplicate id, don't add duplicate collections
                        if(!idList.contains(id)) {
                            idList.add(id);
                            results.add(new Collection(id,collection,tPrice,cPrice,
                                    date,track,image,genre));
                        }
                        trackList.add(new Collection(id,collection,tPrice,cPrice,
                                date,track,image,genre));
                    }
                } catch(JSONException e) {
                }
                CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,R.layout.list_item,results,trackList);
                ListView listView =(ListView) findViewById(R.id.itemList);
                listView.setAdapter(customAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage());
            }
        });
        queue.add(jsObjRequest);
    }
}
