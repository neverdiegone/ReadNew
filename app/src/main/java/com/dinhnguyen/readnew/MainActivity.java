package com.dinhnguyen.readnew;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Setting {
    private String url = address + "list.php";
    private AdapterListView adapterListView;
    private ArrayList<New> list;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listNew);
        adapterListView = new AdapterListView(list, MainActivity.this);
        listView.setAdapter(adapterListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Deltail_Activity.class);
                intent.putExtra("id",((New) adapterListView.getItem(position)).getId());
                intent.putExtra("title", ((New) adapterListView.getItem(position)).getTitle());
                intent.putExtra("titlesummary", ((New) adapterListView.getItem(position)).getTitlesummary());
                intent.putExtra("image", ((New) adapterListView.getItem(position)).getImage());
                intent.putExtra("content", ((New) adapterListView.getItem(position)).getContent());
                intent.putExtra("writingmaster", ((New) adapterListView.getItem(position)).getWritingmaster());
                startActivity(intent);
                finish();
            }
        });
        new DowloadData().execute(url);
    }

    class DowloadData extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String b;
            StringBuilder temp = null;
            try {
                URL url = new URL(params[0]);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                temp = new StringBuilder();
                String line = "";
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = reader.readLine()) != null) {
                    temp.append(line);
                    temp.append("\n");
                }
                reader.close();

            } catch (MalformedURLException e) {
                Log.d("MalMain", e.toString());
            } catch (IOException e) {
                Log.d("IOMain", e.toString());
                return "";
            }
            return temp.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("s",s.toString());
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("new");
                for(int i=0;i<=jsonArray.length();i++)
                {
                    New newpaer = new New();
                    newpaer.setId(Integer.parseInt(jsonArray.getJSONObject(i).getString("id")));
                    newpaer.setTitle(jsonArray.getJSONObject(i).getString("title"));
                    newpaer.setTitlesummary(jsonArray.getJSONObject(i).getString("titlesummary"));
                    newpaer.setContent(jsonArray.getJSONObject(i).getString("content"));
                    newpaer.setImage(jsonArray.getJSONObject(i).getString("image"));
                    newpaer.setWritingmaster(jsonArray.getJSONObject(i).getString("writingmaster"));

                    list.add(newpaer);
                }
            } catch (JSONException e) {
                Log.d("JsonE",e.toString());
            }

        }
    }
}
