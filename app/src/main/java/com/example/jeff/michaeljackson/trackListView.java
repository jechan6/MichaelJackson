package com.example.jeff.michaeljackson;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class trackListView extends AppCompatActivity {
    CustomAdapter customAdapter;
    private String id;
    ArrayList<Collection> trackList = new ArrayList<Collection>();
    ArrayList<Collection> filterTrackList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_list_view);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        filterTrackList = new ArrayList<Collection>();
        trackList = intent.getParcelableArrayListExtra("trackList");



        filterList();
        setTitle(filterTrackList.get(0).getCollection());

        customAdapter = new CustomAdapter(this, R.layout.track_items, filterTrackList) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View customView = inflater.inflate(R.layout.track_items, parent,false);
                TextView titleText = (TextView) customView.findViewById(R.id.titleText);
                TextView priceText = (TextView) customView.findViewById(R.id.priceText);
                ImageView imageItem = (ImageView) findViewById(R.id.collectionImage);

                Collection item = getItem(position);
                titleText.setText(item.getTrack());
                priceText.setText("$"+item.getTrackPrice());
                new ImageLoadTask(item.getImage(), imageItem).execute();

                return customView;
            }
        };

        ListView listView = (ListView) findViewById(R.id.trackListView);
        listView.setAdapter(customAdapter);
    }
    //filter list to only show the collection selected
    public void filterList() {

        for(int i = 0; i < trackList.size();i++) {
            if(trackList.get(i).getId().equals(id)) {
                filterTrackList.add(trackList.get(i));
            }
        }
    }
}
