package com.example.a5thassignment;


import java.util.ArrayList;
import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.app.ListActivity;

public class ListViewDemo extends ListActivity {
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    int clickCounter=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v) {
        listItems.add("Clicked : "+clickCounter++);
        adapter.notifyDataSetChanged();
    }
}
