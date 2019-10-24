package com.example.a5thassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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
    ArrayList arrayList;
    ListView lv;

   public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void fetchData() {
        queue.add(jsonArrayRequest);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

       /* //Fragments
        ButtonFragment buttonFragment = (ButtonFragment) fm.findFragmentById(R.id.buttonFragment);
        ListFragment listFragment = (ListFragment) fm.findFragmentById(R.id.listFragment);

        //ListAdapter
        arrayList = listFragment.arrayList;
        ListView lv = listFragment.lv;*/
        customAdapter = new CustomAdapter(this, arrayList);
        lv.setAdapter(customAdapter);

        //RequestQueue.
        queue = Volley.newRequestQueue(this);
        String url ="https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1";

        //Create request
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


    }
}


