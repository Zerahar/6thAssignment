package com.example.a5thassignment;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.app.ListActivity;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    String[] ListElements = new String[] {
            "Android",
            "PHP"
    };

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);
        final ListView lv = findViewById(R.id.list_view);
        final Toast toast = Toast.makeText(getApplicationContext(), "Internet connection not available", Toast.LENGTH_SHORT);

        final List < String > ListElementsArrayList = new ArrayList < String >
                (Arrays.asList(ListElements));

        final ArrayAdapter < String > adapter = new ArrayAdapter < String >
                (MainActivity.this, android.R.layout.simple_list_item_1,
                        ListElementsArrayList);

        listview.setAdapter(adapter);

        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1";

// Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       try {

                           JSONObject jsonResponse = new JSONObject(response);
                           JSONArray jsonMainNode = jsonResponse.optJSONArray("employee");

                           for(int i = 0; i<jsonMainNode.length();i++) {
                               JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                               String pvm = jsonChildNode.optString("pvm");
                               String nimi = jsonChildNode.optString("nimi");
                               String outPut = pvm + "-" + nimi;
                               ListElementsArrayList.add(outPut);
                               //ListElementsArrayList.add("Moi");
                           }
                           adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // textView.setText("That didn't work!");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isNetworkAvailable() == false){
                    toast.show();
                }
                else {
// Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }
            }
        });
    }
}


