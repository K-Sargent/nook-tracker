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
import androidx.lifecycle.MutableLiveData;
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
import com.example.nooktracker.models.User;
import com.example.nooktracker.models.Villager;
import com.example.nooktracker.models.Villagers;
import com.example.nooktracker.repositories.UserRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import com.squareup.picasso.Picasso;

public class VillagersFragment extends Fragment {

    private VillagersViewModel villagersViewModel;
    private FragmentVillagersBinding binding;
    private boolean isSaving = false;
    private MutableLiveData<Villagers> villagersToBeUpdated;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        villagersViewModel = new ViewModelProvider(this).get(VillagersViewModel.class);
        //villagersViewModel = new ViewModelProvider(requireActivity()).get(VillagersViewModel.class); //might be the same
        binding = FragmentVillagersBinding.inflate(inflater, container, false);
        UserRepository repository = new UserRepository();
        repository.loadUserFromAuth();
        User currentUser = repository.getTaskUser();
        villagersViewModel.loadVillagers(currentUser.userId); //

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


        villagersToBeUpdated = villagersViewModel.getAllVillagers();                              //
        villagersToBeUpdated.observe(getViewLifecycleOwner(), villagers -> {                      //
            if (villagers != null){                                                               //
                Picasso.get().load(villagers.villager1.getImageUrl()).into(binding.ownedVillagerImageView1);//
                Picasso.get().load(villagers.villager2.getImageUrl()).into(binding.ownedVillagerImageView2);//
                Picasso.get().load(villagers.villager3.getImageUrl()).into(binding.ownedVillagerImageView3);//
                Picasso.get().load(villagers.villager4.getImageUrl()).into(binding.ownedVillagerImageView4);//
                Picasso.get().load(villagers.villager5.getImageUrl()).into(binding.ownedVillagerImageView5);//
                Picasso.get().load(villagers.villager6.getImageUrl()).into(binding.ownedVillagerImageView6);//
                Picasso.get().load(villagers.villager7.getImageUrl()).into(binding.ownedVillagerImageView7);//
                Picasso.get().load(villagers.villager8.getImageUrl()).into(binding.ownedVillagerImageView8);//
            }                                                                                     //
        });                                                                                       //

        villagersViewModel.getSaving().observe(getViewLifecycleOwner(), saving -> {               //
            if (!isSaving && saving) {                                                            //
                isSaving = true;                                                                    //
            }                                                                                     //
        });                                                                                         //


        return root;
    }


//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }

    @Override
    public void onPause() {

//        villagersViewModel.setVillagers(
//                villagersToBeUpdated.getValue()
//                //binding.ownedVillagerImageView1.get   //put the villager objects here
//
//        );

        super.onPause();
    }

    @Override
    public void onDestroyView() {

//        tasksViewModel.setVillagerTasks(
//                tasksToBeUpdated.getValue(),
//                binding.villagerCheckbox1.isChecked(),
//                binding.villagerCheckbox2.isChecked(),
//                binding.villagerCheckbox3.isChecked(),
//                binding.villagerCheckbox4.isChecked(),
//                binding.villagerCheckbox5.isChecked(),
//                binding.villagerCheckbox6.isChecked(),
//                binding.villagerCheckbox7.isChecked(),
//                binding.villagerCheckbox8.isChecked()
//        );

        super.onDestroyView();
        binding = null;
    }
}