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
import com.example.nooktracker.databinding.FragmentSignInBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInFragment extends Fragment {

//    public SignInFragment() {
//        // Required empty public constructor
//        super(R.layout.fragment_sign_in);           //kate, this might need to go away for nav component.
//    }

    boolean emailValid = false;
    boolean passValid = false;
    FragmentSignInBinding binding;
    public SignInFragment() {
        super(R.layout.fragment_sign_in);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        NavController controller = NavHostFragment.findNavController(this);

        //set where to point upon loading app
        if (auth.getCurrentUser() != null) {
            controller.navigate(R.id.action_signInFragment_to_navigation_home);
        }

        //set up buttons for login view
        binding.SignInButton.setOnClickListener((view1 -> {

            //validation
            if (binding.SignInEmailInput.getText().toString().length() == 0 ) {
                binding.SignInEmailInput.setError("Email cannot be empty");
            } else if (!binding.SignInEmailInput.getText().toString().contains("@")){
                binding.SignInEmailInput.setError("Must input a proper email with @");
            } else {
                emailValid = true;
            }

            if (binding.SignInPassword.getText().toString().length() == 0) {
                binding.SignInPasswordInput.setError("Password cannot be empty");
            } else {
                passValid = true;
            }

            if (emailValid && passValid) {
                auth.signInWithEmailAndPassword(
                        binding.SignInEmailInput.getText().toString(),
                        binding.SignInPasswordInput.getText().toString()
                ).addOnCompleteListener((task)  -> {
                    if (task.isSuccessful()) {
                        controller.navigate(R.id.action_signInFragment_to_navigation_home);
                    } else {
                        binding.SignInErrorMessage.setText(task.getException().getMessage());
                    }
                });
            }
        }));

        binding.SignInSignUpButton.setOnClickListener((view1 -> {
            controller.navigate(R.id.action_signInFragment_to_signUpFragment);
        }));

        return binding.getRoot();
    }
}