package com.example.notetakingapp.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.notetakingapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends Fragment {

    public SignIn() {
        // Required empty public constructor
        super(R.layout.fragment_sign_in);           //kate, this might need to go away for nav component.
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText SignInEmailInput =  view.findViewById(R.id.SignInEmailInput);
        EditText SignInPasswordInput =  view.findViewById(R.id.SignInPasswordInput);
        TextView SignInError =  view.findViewById(R.id.SignInErrorMessage);


        FirebaseAuth auth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            FragmentManager manager = getActivity().getSupportFragmentManager();
                  manager.beginTransaction()
                         .replace(R.id.fragment, MainAppScreen.class, null)
                         .setReorderingAllowed(true)
                         .commit();
        } else {
            view.findViewById(R.id.SignInButton).setOnClickListener((view2) -> {
                if((SignInEmailInput.getText().toString().length() > 0) && (SignInPasswordInput.getText().toString().length() > 0)){
                    auth.signInWithEmailAndPassword(
                        SignInEmailInput.getText().toString(),
                        SignInPasswordInput.getText().toString()
                    ).addOnCompleteListener((task) -> {
                        if (task.isSuccessful()) {
//                            FragmentManager manager = getActivity().getSupportFragmentManager();  //this is the original fragment manager code.
//                            manager.beginTransaction()
//                                .replace(R.id.fragment, MainAppScreen.class, null)
//                                .setReorderingAllowed(true)
//                                .commit();
                        } else {
                            Log.d("__FireBase", task.getException().toString());
                            SignInError.setText(task.getException().toString());
                        }
                    });
                } else {
                    SignInError.setText("One or more fields are empty.");
                };
            });
        }

//        view.findViewById(R.id.SignInSignUpButton).setOnClickListener((view2) -> {   //this is the code that originally sets up the fragment transfer for the sign up button.
//            FragmentManager manager = getActivity().getSupportFragmentManager();
//            manager.beginTransaction()
//                    .replace(R.id.fragment, SignUp.class, null)
//                    .setReorderingAllowed(true)
//                    .commit();
//        });
    }
}