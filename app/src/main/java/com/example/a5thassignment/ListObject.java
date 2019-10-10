package com.example.a5thassignment;

import org.json.JSONException;
import org.json.JSONObject;

public class ListObject {
    private String name;
    private String time;

    public ListObject(JSONObject jsonObj) {
        try {
            this.time = jsonObj.getString("pvm");
            this.name = jsonObj.getString("nimi");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getDateString() {
        //edit date string to include only date, not time
        String output = this.time.substring(0, 10);
        return output + " " + this.name;
    }
}