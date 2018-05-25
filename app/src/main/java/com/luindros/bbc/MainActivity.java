package com.luindros.bbc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private String JsonURL = "https://raw.githubusercontent.com/fmtvp/recruit-test-data/master/data.json";
    private RequestQueue requestQueue;

    private List<Fruit> fruits=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        mLayoutManager= new LinearLayoutManager(this);
        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new MyAdapter(fruits, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit fruit, int position) {
                Toast.makeText(MainActivity.this, "Lo que sea", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        requestFruits();
    }
    public void requestFruits() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, JsonURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray fruitArray= null;

                        try {
                            fruitArray=response.getJSONArray("fruit");
                            for(int i=0; i<fruitArray.length(); i++){

                                JSONObject fruitObject= fruitArray.getJSONObject(i);

                                Fruit fruit = new Fruit(
                                        fruitObject.getString("type"),
                                        fruitObject.getInt("price"),
                                        fruitObject.getInt("weight"));

                                fruits.add(fruit);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Toast.makeText(MainActivity.this, "Lo que sea:"+response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

// Access the RequestQueue through your singleton class.
        requestQueue.add(jsonObjectRequest);

    }




}
