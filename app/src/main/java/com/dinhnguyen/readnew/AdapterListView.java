package com.dinhnguyen.readnew;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Administrator on 3/15/2017.
 */

public class AdapterListView extends BaseAdapter implements Setting {
    ArrayList<New> list;
    Context context;

    public AdapterListView(ArrayList<New> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public AdapterListView() {
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Custom_Layout custom_layout;
        if(convertView == null)
        {
            custom_layout = new Custom_Layout();
            LayoutInflater infl = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infl.inflate(R.layout.custom_layout, parent, false);
            custom_layout.tvcustom = (TextView)convertView.findViewById(R.id.tvCustom);
            custom_layout.imageView = (ImageView)convertView.findViewById(R.id.imageCustom);
        }
        else
        {
            custom_layout = (Custom_Layout)convertView.getTag();
        }
        custom_layout.tvcustom.setText(list.get(position).getTitle());
        String url =address+list.get(position).getImage();
        new DowloadImage(custom_layout.imageView).execute(url);
        return convertView;
    }
    class Custom_Layout
    {
        ImageView imageView;
        TextView tvcustom;
    }
    class DowloadImage extends AsyncTask<String,Integer,Bitmap>
    {
        ImageView imageView;

        public DowloadImage(ImageView imageView) {
            this.imageView = imageView;
        }
        Bitmap bitmap;
        @Override
        protected Bitmap doInBackground(String... params) {

            try {
                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                Log.d("Malformed",e.toString());
            } catch (IOException e) {
                Log.d("IO",e.toString());
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }
}
