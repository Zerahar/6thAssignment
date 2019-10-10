package com.example.a5thassignment;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    private List<ListObject> rowItems;

    public CustomAdapter(Context context, ArrayList<ListObject> dates) {
        super(context, 0, dates);
        this.rowItems = dates;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get object
        final ListObject rowItem = rowItems.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the layout components
        TextView listText = convertView.findViewById(R.id.text_date);

        // Set text
        listText.setText(rowItem.getDateString());

        return convertView;
    }
}

