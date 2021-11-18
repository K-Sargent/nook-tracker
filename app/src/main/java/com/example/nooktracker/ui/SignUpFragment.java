package com.example.nooktracker.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nooktracker.R;
import com.example.nooktracker.databinding.FragmentSignUpBinding;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment {

//    public SignUpFragment() {
//        // Required empty public constructor
//        super(R.layout.fragment_sign_up);               //kate, this one might need to go too. not sure how it works with the nav component.
//    }
    boolean emailValid = false;
    boolean passValid = false;
    FragmentSignUpBinding binding;
    public SignUpFragment() {
        super(R.layout.fragment_sign_up);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        binding.SignUpButton.setOnClickListener((view1 -> {
            //validate email
            if (binding.SignUpEmailInput.getText().toString().length() == 0) {
                binding.SignUpEmailInput.setError("Email cannot be empty");
            } else if (!binding.SignUpEmailInput.getText().toString().contains("@")) {
                binding.SignUpEmailInput.setError("Must enter a valid email");
            } else if (!binding.SignUpEmail.getText().toString().equals(binding.SignUpConfirmEmailInput.getText().toString())) {
                binding.SignUpEmail.setError("Emails do not match");
                binding.SignUpConfirmEmailInput.setError("Emails do not match");
            } else {
                emailValid = true;
            }

            //validate password
            if (binding.SignUpPasswordInput.getText().toString().length() < 8) {
                binding.SignUpPasswordInput.setError("Password must be 8 characters or more");
            } else if (!binding.SignUpPassword.getText().toString().equals(binding.SignUpConfirmPasswordInput.getText().toString())) {
                binding.SignUpPassword.setError("Passwords do not match");
                binding.SignUpConfirmPasswordInput.setError("Passwords do not match");
            } else {
                passValid = true;
            }

            if (emailValid && passValid) {
                auth.createUserWithEmailAndPassword(
                        binding.SignUpEmail.getText().toString(),
                        binding.SignUpPassword.getText().toString()
                ).addOnCompleteListener((task)  -> {
                    if (task.isSuccessful()) {
                        controller.navigate(R.id.action_signUpFragment_to_navigation_home);
                    } else {
                        binding.SignUpErrorMessage.setText(task.getException().getMessage());
                    }
                });
            }
        }));
        return binding.getRoot();
    }
}