package com.example.coronatracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    RecyclerView recyclerView;
    ProgressDialog pd;
    Button button, button5;
    recycleradapter adapter;
    InputStream is = null;

    TextView checknet;

    SearchView searchView;
    ArrayList<countrydata> list = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.viewby, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.alpha) {
            appdata.flag = 0;
        } else {
            appdata.flag = 1;
        }
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button3);
        button5 = findViewById(R.id.button5);
        checknet = findViewById(R.id.checknet);
        checknet.setVisibility(View.GONE);
        if (appdata.layout_type == 1) button.setText("List");
        else
            button.setText("Grid");
        searchView = findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        new JsonTask().execute("https://corona.lmao.ninja/v2/countries");

    }

    public void onclick(View v) {

        appdata.layout_type = 1 - appdata.layout_type;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void aboutcorona(View v) {
        startActivity(new Intent(this, about_corona.class));
        finish();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        adapter.getFilter().filter(s);
        return true;
    }


    private class JsonTask extends AsyncTask<String, String, ArrayList<countrydata>> {

        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait \n" + "Fetching data ");
            pd.setCancelable(false);
            pd.show();
        }

        protected ArrayList<countrydata> doInBackground(String... params) {

            if (appdata.locallist_listview.size() == 0 || appdata.locallist.size() == 0 || appdata.prevflag != appdata.flag) {
                HttpURLConnection connection = null;
                HttpURLConnection connection2 = null;

                BufferedReader reader = null;

                try {
                    URL url = new URL(params[0]);
                    URL url2 = new URL("https://corona.lmao.ninja/v2/all");
                    connection = (HttpURLConnection) url.openConnection();
                    connection2 = (HttpURLConnection) url2.openConnection();
                    try {
                        connection.connect();
                        connection2.connect();
                    } catch (IOException e) {
                        checknet.setVisibility(View.VISIBLE);

                    }


                    InputStream stream = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuilder buffer = new StringBuilder();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line).append("\n");

                    }

                    JSONArray countries = new JSONArray(buffer.toString());
                    for (int i = 0; i < countries.length(); i++) {
                        String country = countries.getJSONObject(i).getString("country");
                        String flagurl = countries.getJSONObject(i).getJSONObject("countryInfo").getString("flag");
                        long cases = countries.getJSONObject(i).getLong("cases");
                        long todaycases = countries.getJSONObject(i).getLong("todayCases");

                        long deaths = countries.getJSONObject(i).getLong("deaths");

                        long todaydeaths = countries.getJSONObject(i).getLong("todayDeaths");
                        long recovered = countries.getJSONObject(i).getLong("recovered");
                        long active = countries.getJSONObject(i).getLong("active");

                        list.add(new countrydata(country, flagurl, cases, todaycases, deaths, todaydeaths, recovered, active));
                    }

                    if (appdata.flag == 0) {
                        Collections.sort(list, new Comparator<countrydata>() {
                            @Override
                            public int compare(countrydata countrydata1, countrydata countrydata2) {
                                if (countrydata1.name.compareToIgnoreCase(countrydata2.name) > 0)
                                    return 1;
                                else return -1;
                            }
                        });
                    } else {
                        Collections.sort(list, new Comparator<countrydata>() {
                            @Override
                            public int compare(countrydata countrydata1, countrydata countrydata2) {
                                if (countrydata1.cases > countrydata2.cases)
                                    return -1;
                                else return 1;
                            }
                        });
                    }
                    appdata.locallist.clear();

                    appdata.locallist.addAll(list);
                    stream = connection2.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    buffer = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line).append("\n");

                    }
                    JSONObject total = new JSONObject(buffer.toString());
                    list.add(0, new countrydata("TOTAL", " ", total.getLong("cases"), total.getLong("affectedCountries"), total.getLong("deaths"), 0, total.getLong("recovered"), total.getLong("active")));

                    appdata.locallist_listview.clear();

                    appdata.locallist_listview.addAll(list);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                } finally {

                    if (connection != null) {
                        connection.disconnect();
                    }
                }

                URL url = null;

                if (appdata.b.size() == 0) {
                    //appdata.prevflag=appdata.flag;
                    for (int i = 0; i < appdata.locallist.size(); i++) {
                        if (appdata.locallist.get(i).flagurl.equals(" ")) {
                            appdata.b.put(appdata.locallist.get(i).name, null);
                        } else {
                            try {
                                url = new URL(appdata.locallist.get(i).flagurl);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            final URL finalUrl = url;
                            HttpURLConnection connection1 = null;
                            try {
                                connection1 = (HttpURLConnection) finalUrl.openConnection();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            try {
                                is = connection1.getInputStream();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            appdata.b.put(appdata.locallist.get(i).name, Bitmap.createScaledBitmap(BitmapFactory.decodeStream(is), 110, 80, false));
                        }
                    }
                }
                appdata.prevflag = appdata.flag;


                return list;

            } else {
                return appdata.locallist_listview;
            }


        }

        @Override
        protected void onPostExecute(ArrayList<countrydata> result) {
            super.onPostExecute(result);
            if (pd.isShowing()) {
                pd.dismiss();
            }


            if (appdata.layout_type == 1) {
                adapter = new recycleradapter(getApplicationContext(), appdata.locallist);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemViewCacheSize(appdata.locallist.size());
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));

            } else {
                adapter = new recycleradapter(getApplicationContext(), appdata.locallist_listview);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setItemViewCacheSize(list.size());

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


            }


        }
    }
}