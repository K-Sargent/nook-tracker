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
import androidx.navigation.ui.NavigationUI;

import com.example.nooktracker.R;
import com.example.nooktracker.databinding.FragmentPreferencesBinding;
import com.example.nooktracker.models.User;
import com.example.nooktracker.repositories.UserRepository;

import java.util.Observable;

public class PreferencesFragment extends Fragment {

    private PreferencesViewModel preferencesViewModel;
    private FragmentPreferencesBinding binding;
    private boolean isSaving = false;
    private boolean userExistsInDb = false;
    private User userToBeUpdated;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        preferencesViewModel = new ViewModelProvider(this).get(PreferencesViewModel.class);
        binding = FragmentPreferencesBinding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);
        UserRepository repository = new UserRepository();
        User currentUser = repository.getCurrentUser();
//        if (preferencesViewModel.user == null) {
//            preferencesViewModel.setSelectedUser(currentUser);
//        }
//        preferencesViewModel.loadUser(currentUser.getUserId());
//        MutableLiveData<User> userFromDb = preferencesViewModel.getUser();

        binding.pUserDisplay.setText(currentUser.getEmail());
//        binding.editIslandName.setText(userFromDb.getValue().getIslandName());
//        binding.editCharacterName.setText(userFromDb.getValue().getCharacterName());


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
                preferencesViewModel.createUser(currentUser.email, currentUser.getUserId(), binding.editIslandName.getText().toString(), binding.editCharacterName.getText().toString());
                binding.pSaveButton.setEnabled(true);
            });
        } else {
            binding.pSaveButton.setOnClickListener(view -> {
                binding.pSaveButton.setEnabled(false);
                preferencesViewModel.setUser(
                        currentUser,
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