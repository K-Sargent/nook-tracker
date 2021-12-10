package com.example.nooktracker.adapters;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nooktracker.databinding.FragmentVillagerItemBinding;
import com.example.nooktracker.models.Villager;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

public class VillagersAdapter extends RecyclerView.Adapter<VillagersAdapter.ViewHolder> {
    ArrayList<Villager> villagers = new ArrayList<>();

    public VillagersAdapter(ArrayList<Villager> villagers) {
        this.villagers = villagers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentVillagerItemBinding binding = FragmentVillagerItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Button addVillager = holder.getBinding().addVillager;

        holder.getBinding().villagerName.setText(villagers.get(position).getName());
        Picasso.get().load(villagers.get(position).getImageUrl()).into(holder.getBinding().villagerImage);
//        addVillager.setOnClickListener(view -> {
//            if () {
//
//            }
//            else {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return villagers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        FragmentVillagerItemBinding binding;
        public ViewHolder(FragmentVillagerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public FragmentVillagerItemBinding getBinding() {
            return binding;
        }
    }
}
