package com.example.jeff.michaeljackson;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeff on 6/23/2017.
 */

public class CustomAdapter extends ArrayAdapter<Collection> {
    Context context;
    int resource;
    ArrayList<Collection> trackList;
    public CustomAdapter(Context context, int resource, ArrayList<Collection> collections) {
        super(context,resource,collections);

        this.context = context;
        this.resource = resource;
    }
    public CustomAdapter(Context context, int resource, ArrayList<Collection> collections, ArrayList<Collection> trackList) {
        super(context,resource,collections);

        this.context = context;
        this.resource = resource;
        this.trackList = trackList;
    }
    //load images asyncronously
    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }
        //download image from the web
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View customView = inflater.inflate(R.layout.list_item, parent, false);
        final Collection item = getItem(position);


        ImageView imageItem = (ImageView) customView.findViewById(R.id.mjImage);
        TextView collectionText = (TextView) customView.findViewById(R.id.collectionText);
        TextView collectionPrice = (TextView) customView.findViewById(R.id.collectionPrice);


        collectionText.setText(item.getCollection());
        collectionPrice.setText(item.getCollectionPrice());
        //load image asyncronously
        new ImageLoadTask(item.getImage(), imageItem).execute();

        customView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,trackListView.class);
                intent.putExtra("id", item.getId());
                intent.putParcelableArrayListExtra("trackList", trackList);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                context.startActivity(intent);
            }
        });
        return customView;
    }
}
