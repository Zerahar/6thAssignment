package com.example.a5thassignment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ListView;
import android.content.Context;
import android.net.ConnectivityManager;
import com.android.volley.toolbox.JsonArrayRequest;
import android.net.NetworkInfo;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue;
    JsonArrayRequest jsonArrayRequest;
    CustomAdapter customAdapter;
    ArrayList<ListObject> arrayList;

   private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void fetchData() {
        queue.add(jsonArrayRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        final Toast toast = Toast.makeText(getApplicationContext(), "Internet connection not available", Toast.LENGTH_SHORT);
        final ListView lv = findViewById(R.id.list_view);

        //Instantiate ListView
        arrayList = new ArrayList<>();
        customAdapter = new CustomAdapter(this, arrayList);
        lv.setAdapter(customAdapter);

// Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
        String url ="https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1";


        // Request a string response from the provided URL.
        jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        // Clear the list before new values are fetched.
                        arrayList.clear();

                        // Add all objects in the response to listView data array.
                        for (Integer i = 0; i < response.length(); i++) {
                            try {
                                arrayList.add(new ListObject(response.getJSONObject(i)));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        customAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Fetch successful!", Toast.LENGTH_SHORT).show();


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Fetch failed", Toast.LENGTH_LONG).show();

                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //test that internet connection works
                if (isNetworkAvailable() == false){
                    toast.show();
                }
                else {
// Fetch data and put it into ListView.
                   fetchData();
                }
            }
        });
    }
}


