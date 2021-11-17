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

public class SignUp extends Fragment {

    public SignUp() {
        // Required empty public constructor
        super(R.layout.fragment_sign_up);               //kate, this one might need to go too. not sure how it works with the nav component.
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText SignUpEmailInput = view.findViewById(R.id.SignUpEmailInput);
        EditText SignUpPasswordInput = view.findViewById(R.id.SignUpPasswordInput);
        EditText SignUpConfirmEmailInput = view.findViewById(R.id.SignUpConfirmEmailInput);
        EditText SignUpConfirmPasswordInput = view.findViewById(R.id.SignUpConfirmPasswordInput);
        TextView SignUpError = view.findViewById(R.id.SignUpErrorMessage);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        view.findViewById(R.id.SignUpButton).setOnClickListener((view2) -> {
            if((SignUpEmailInput.getText().toString().length() > 0 ) && ( SignUpPasswordInput.getText().toString().length() > 0) && (SignUpConfirmEmailInput.getText().toString().length() > 0) && (SignUpConfirmPasswordInput.getText().toString().length() > 0)){
                if(SignUpPasswordInput.getText().toString().length() > 8){
                    if((SignUpEmailInput.getText().toString().equals(SignUpConfirmEmailInput.getText().toString())) && (SignUpPasswordInput.getText().toString().equals(SignUpConfirmPasswordInput.getText().toString()))) {
                        auth.createUserWithEmailAndPassword(
                            SignUpEmailInput.getText().toString(),
                            SignUpPasswordInput.getText().toString()
                        ).addOnCompleteListener((task) -> {
                            if (task.isSuccessful()) {
                                Log.d("__Firebase", "SIGN UP SUCCESS");


                                auth.signInWithEmailAndPassword(
                                    SignUpEmailInput.getText().toString(),
                                    SignUpPasswordInput.getText().toString()
                                ).addOnCompleteListener((task2) -> {
                                    if (task2.isSuccessful()) {
//                                        FragmentManager manager = getActivity().getSupportFragmentManager();
//                                        manager.beginTransaction()
//                                            .replace(R.id.fragment, MainAppScreen.class, null)
//                                            .setReorderingAllowed(true)
//                                            .commit();
                                    } else {
                                        Log.d("__FireBase", task.getException().toString());
                                        SignUpError.setText(task.getException().toString());
                                    }
                                });


                            } else {
                                Log.d("__FireBase", task.getException().toString());
                                SignUpError.setText(task.getException().toString());
                            }
                        });
                    } else {
                        SignUpError.setText("username must match confirm username, and password must match confirm password.");
                    };
                } else {
                    SignUpError.setText("password must be more than 8 characters.");
                };
            } else {
                SignUpError.setText("one or more fields are empty.");
            };
        });
    }
}