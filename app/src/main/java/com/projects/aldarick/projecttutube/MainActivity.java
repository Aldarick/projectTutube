package com.projects.aldarick.projecttutube;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import customClasses.Item;
import customClasses.Snippet;
import customClasses.Videos;
import uiClasses.OnVideoSelectedListener;
import uiClasses.VideosAdapter;

import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

// clé api : AIzaSyB9HQfc0bnUMv5AqAUm6_RqxdVHGrZHmHM


public class MainActivity extends AppCompatActivity implements OnVideoSelectedListener{

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SearchView search = (SearchView) this.findViewById(R.id.search);

        search.setActivated(true);
        search.setQueryHint("Type your keywords here");
        search.onActionViewExpanded();
        search.setIconified(false);
        search.clearFocus();


            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    searchVideos(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    searchVideos(newText);
                    return false;
                }
        });

    }

    public void searchVideos(String query){

        if (query.equals("")){
            Videos empty = new Gson().fromJson("", Videos.class);
            showResults(empty);
        }
        else {
            System.out.println("=====================");
            System.out.println(query);

            String values = query.replace(' ', '+');
            String requestAPI = "https://www.googleapis.com/youtube/v3/search?part=id,snippet&q="+values+"&type=video&key=AIzaSyB9HQfc0bnUMv5AqAUm6_RqxdVHGrZHmHM";

            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, requestAPI,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Videos videos = new Gson().fromJson(response, Videos.class);

                            showResults(videos);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //mTextView.setText("That didn't work!");
                }
            });

            queue.add(stringRequest);


            System.out.println("=====================");
        }



    }

    private void showResults(Videos videos) {
        VideosAdapter adapter = new VideosAdapter(videos);
        adapter.setOnVideoSelectedListener(this);
        recyclerView.setAdapter(adapter);
    }



    public void sendMessage(View view) {
        Intent intent = new Intent(this, DetailsActivity.class);





        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, 1);
    }


    @Override
    public void onVideoSelected(Item video) {

        Snippet snippet = video.getSnippet();
        DetailsActivity.start(this, snippet.getTitle(), snippet.getThumbnails().getMedium().getUrl(), snippet.getDescription(), snippet.getPublishedAt(), snippet.getChannelTitle());
    }
}
