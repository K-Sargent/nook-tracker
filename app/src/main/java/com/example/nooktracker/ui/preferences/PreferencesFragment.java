package com.example.nooktracker.ui.preferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.nooktracker.R;
import com.example.nooktracker.databinding.FragmentPreferencesBinding;
import com.example.nooktracker.models.User;
import com.example.nooktracker.repositories.UserRepository;
//import com.google.android.gms.ads.AdRequest;

public class PreferencesFragment extends Fragment {

    private PreferencesViewModel preferencesViewModel;
    private FragmentPreferencesBinding binding;
    private boolean isSaving = false;
    private boolean userExistsInDb = false;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        preferencesViewModel = new ViewModelProvider(this).get(PreferencesViewModel.class);
        binding = FragmentPreferencesBinding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);
        UserRepository repository = new UserRepository();
        repository.loadCurrentUser();
        MutableLiveData<User> currentUser = repository.getCurrentUser();

        currentUser.observe(getViewLifecycleOwner(), authUser -> {
            repository.loadCurrentUser();
            if (authUser == null) {
                repository.logout();
                controller.navigate(R.id.action_navigation_preferences_to_signInFragment);
            } else {
                preferencesViewModel.loadUser(authUser.getUserId());
                binding.pUserDisplay.setText(authUser.getEmail());
                binding.pSaveButton.setEnabled(true);
            }
        });
        MutableLiveData<User> userFromDb = preferencesViewModel.getUser();
        userFromDb.observe(getViewLifecycleOwner(), (user -> {
            binding.dbSavedIsland.setText(user.getIslandName());
            binding.dbSavedCharacter.setText(user.getCharacterName());
        }));

        binding.logOutButton.setOnClickListener((view -> {

            repository.logout();
            controller.navigate(R.id.action_navigation_preferences_to_signInFragment);
        }));

        preferencesViewModel.getSaving().observe(getViewLifecycleOwner(), saving -> {
            if (!isSaving && saving) {
                isSaving = true;
            }
        });

        preferencesViewModel.getErrorMessage().observe(getViewLifecycleOwner(), errorMessage -> {
            if (!preferencesViewModel.getErrorMessage().getValue().equals("")) {
                binding.pErrorText.setText(preferencesViewModel.getErrorMessage().getValue());
            }
        });

        if (!userExistsInDb) {
            binding.pSaveButton.setOnClickListener(view -> {
                binding.pSaveButton.setEnabled(false);
                preferencesViewModel.createUser(currentUser.getValue().getEmail(), currentUser.getValue().getUserId(), binding.editIslandName.getText().toString(), binding.editCharacterName.getText().toString());
                binding.pSaveButton.setEnabled(true);
            });
        } else {
            binding.pSaveButton.setOnClickListener(view -> {
                binding.pSaveButton.setEnabled(false);
                preferencesViewModel.setUser(
                        currentUser.getValue(),
                        binding.editIslandName.getText().toString(),
                        binding.editCharacterName.getText().toString()
                );
                binding.pSaveButton.setEnabled(true);
            });
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}