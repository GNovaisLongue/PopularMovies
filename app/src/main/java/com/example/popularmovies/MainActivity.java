package com.example.popularmovies;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //JSON
    private String JSON_URL;
    private List<Movies> listmovies ;
    private RecyclerView recyclerView ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TooBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //FAB
        FloatingActionButton fab = findViewById(R.id.fab);
        //
        listmovies = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        setuprecyclerview(listmovies);

        //Adapter
        adapter = new RecyclerViewAdapter(this,listmovies) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        //JSON
        String endereco =
                getString(R.string.url)+ //api.themoviedb.org\/3\/movie\/popular\?api_key=
                        getString(R.string.api_key)+
                "&language="+ Locale.getDefault().toString();
        JSON_URL = "https://"+endereco;


        //FAB ONCLICK
        fab.setOnClickListener(view -> {
            httpconnection();
            Toast.makeText(MainActivity.this, getString(R.string.loading), Toast.LENGTH_SHORT).show();
        });

    }//OnCreate

    //Connection OK
    private void httpconnection(){
        new Thread(
                () -> {
                    try{
                        URL url = new URL (JSON_URL);
                        HttpURLConnection connection =
                                (HttpURLConnection) url.openConnection();

                        BufferedReader reader =
                                new BufferedReader(
                                        new InputStreamReader(
                                                connection.getInputStream()
                                        )
                                );

                        StringBuilder stringBuilder = new StringBuilder(); //async Builder (StringBuilder ins't)
                        String linha = "";
                        while ((linha = reader.readLine()) != null){
                            stringBuilder.append(linha);
                        }

                        reader.close();

                        //GSON
//                        gsonrequest(stringBuilder.toString());
                        jsonrequest(stringBuilder.toString());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                        runOnUiThread(
                                () -> Toast.makeText(
                                        MainActivity.this,
                                        getString(R.string.connect_error),
                                        Toast.LENGTH_SHORT
                                ).show()
                        );
                    }
                }
        ).start();
    }

    private void jsonrequest(String stringbuilder) {

        try{
            //acesso ao Json
            JSONObject json = new JSONObject(stringbuilder);
            JSONArray list = json.getJSONArray("results");
            for (int i = 0; i < list.length(); i++){

                JSONObject iesimo = list.getJSONObject(i); //acesso ao JSON. Acesso à cada elemento de previsão do tempo

                String title = iesimo.getString("title");
                String overview = iesimo.getString("overview");
                String poster_path = iesimo.getString("poster_path");


                Movies movies =
                        new Movies(
                                title,
                                overview,
                                poster_path
                        );
                listmovies.add(movies);
            }
            runOnUiThread(
                    () -> adapter.notifyDataSetChanged()
            );

        }
        catch (JSONException e){
            e.printStackTrace();
            runOnUiThread(
                    ()-> Toast.makeText(this, getString(R.string.read_error), Toast.LENGTH_SHORT).show()
            );
        }

    }


    private void setuprecyclerview(List<Movies> listmovies) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,listmovies) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
    }


    //System default
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}



