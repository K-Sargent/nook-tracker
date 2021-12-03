package com.example.nooktracker;

import android.content.Context;
import android.util.Log;

import androidx.databinding.ObservableArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nooktracker.models.Villager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class APICall {
    private ArrayList<Villager> villagerArr = new ArrayList<Villager>();
    private Context context;

    public APICall(Context context) {
        this.context = context;
    }

    public ArrayList<Villager> getVillagers(String response){
        JSONArray villagers = new JSONArray();
        try {
            villagers = new JSONArray(response);
        } catch (JSONException e) {
            Log.i("VILLAGER", "ERROR: " + e.getMessage());
        }
        for (int i = 0; i < villagers.length(); i++) {
            try {
                JSONObject object = villagers.getJSONObject(i);
                Villager villager = new Villager(object.getJSONObject("name").getString("name-USen"), object.getString("id"), object.getString("birthday"), object.getString("personality"), object.getString("species"), object.getString("icon_uri"));
                this.villagerArr.add(villager);
//                         Log.i("VILLAGER", "" + villager.getName());
            } catch (JSONException e) {
                Log.i("VILLAGER", "ERROR: " + e.getMessage());
            }
        }

        return this.villagerArr;
    }
}
