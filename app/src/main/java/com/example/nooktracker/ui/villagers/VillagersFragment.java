package com.example.nooktracker.ui.villagers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nooktracker.adapters.VillagersAdapter;
import com.example.nooktracker.APICall;
import com.example.nooktracker.databinding.FragmentVillagersBinding;
import com.example.nooktracker.models.Villager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class VillagersFragment extends Fragment {

    private VillagersViewModel villagersViewModel;
    private FragmentVillagersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        villagersViewModel =
                new ViewModelProvider(this).get(VillagersViewModel.class);

        binding = FragmentVillagersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        APICall apiCall = new APICall(this.getContext());

        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        String url ="https://acnhapi.com/v1a/villagers/";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<Villager> villagers = apiCall.getVillagers(response);
                        Log.i("VILLAGERS", villagers.toString());
                        VillagersAdapter adapter = new VillagersAdapter(villagers);
                        binding.recyclerView.setAdapter(adapter);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("VILLAGER", error.getMessage());
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}