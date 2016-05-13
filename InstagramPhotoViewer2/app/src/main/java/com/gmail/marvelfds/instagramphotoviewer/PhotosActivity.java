package com.gmail.marvelfds.instagramphotoviewer;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {
    public static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
    private ArrayList<InstagramPhoto> photos ;
    private InstagramArrayAdapter aphotos;
    private SwipeRefreshLayout swipeContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                // Your code to refresh the list here.

                // Make sure you call swipeContainer.setRefreshing(false)

                // once the network request has completed successfully.

                fetchPopularPhotos();


            }

        });
        photos =  new ArrayList<>();
        aphotos = new InstagramArrayAdapter(this,0,photos);
        ListView lvPhotos = (ListView)findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(aphotos);
        // Lookup the swipe container view
        fetchPopularPhotos();

    }
    private void fetchPopularPhotos() {
        String url = "https://api.instagram.com/v1/media/popular?client_id="+CLIENT_ID;
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url,new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("DEBUG",response.toString());

                JSONArray photosJson =null;
                try {
                    photosJson = response.getJSONArray("data");

                    for (int i =0;i<photosJson.length();i++){
                        //Get JsonObject




                        JSONObject jsonObject = photosJson.getJSONObject(i);

                        InstagramPhoto photo = new InstagramPhoto();
                        photo.userName = jsonObject.getJSONObject("user").getString("username");
                        photo.caption = jsonObject.getJSONObject("caption").getString("text");
                        photo.imageUrl = jsonObject.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        photo.imageHeight = jsonObject.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
                        photo.likesCount = jsonObject.getJSONObject("likes").getInt("count");
                        photo.userProfile = jsonObject.getJSONObject("user").getString("profile_picture");
                        photo.creationDate = jsonObject.getLong("created_time");




                        photos.add(photo) ;


                    }

                }
                catch (JSONException ex){
                    ex.printStackTrace();

                }
                aphotos.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
