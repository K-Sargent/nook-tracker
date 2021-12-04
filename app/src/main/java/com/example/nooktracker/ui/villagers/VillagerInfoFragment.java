package com.example.nooktracker.ui.villagers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nooktracker.databinding.FragmentVillagerInfoBinding;

public class VillagerInfoFragment extends Fragment {
    private FragmentVillagerInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVillagerInfoBinding.inflate(inflater, container, false);

        //Variables Needed For API
//        binding.villagerNameVar;
//        binding.villagerBdayVar;
//        binding.villagerPersonalityVar;
//        binding.villagerSpeciesVar;
//        binding.villagerInfoImage;

        return binding.getRoot();
    }


}