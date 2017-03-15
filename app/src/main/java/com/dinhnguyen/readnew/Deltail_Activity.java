package com.dinhnguyen.readnew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Deltail_Activity extends AppCompatActivity implements Setting {
    private TextView tvDetail,tvsumarydetail,tvcontent;
    private ImageView imgDetail;
    private String title,summary,content,image;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deltail_);
        tvDetail = (TextView)findViewById(R.id.tvdetail);
        tvsumarydetail = (TextView)findViewById(R.id.tvsumamrydetail);
        tvcontent = (TextView)findViewById(R.id.tvcontentdetail);
        imgDetail = (ImageView)findViewById(R.id.imgdetail);
        intent = getIntent();
        title = intent.getStringExtra("title");
        summary = intent.getStringExtra("titlesummary");
        content = intent.getStringExtra("content");
        image = intent.getStringExtra("image");

        tvDetail.setText(title);
        tvcontent.setText(content);
        tvsumarydetail.setText(summary);
        Log.d("title",title);
        String url = address+image;
        new Download(imgDetail).execute(url);
    }
    class Download extends AsyncTask<String, Integer, Bitmap>
    {
        ImageView image;
        public Download(ImageView image) {
            this.image = image;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                Bitmap b;
                URL u = new URL(strings[0]);
                URLConnection conn = u.openConnection();
                InputStream i = conn.getInputStream();
                b = BitmapFactory.decodeStream(i);
                return b;
            }catch(Exception ex)
            {
                Log.d("Loi hinh:", ex.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            image.setImageBitmap(bitmap);
        }
    }
}
