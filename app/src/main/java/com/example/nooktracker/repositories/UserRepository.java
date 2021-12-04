package com.example.nooktracker.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.nooktracker.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRepository {
    FirebaseAuth auth;
    MutableLiveData<User> user = new MutableLiveData<>();

    public static interface OnFailureListener {
        public void onFailure(Exception e);
    }

    public static interface OnAuthStateChanged {
        public void onAuthStateChanged(User user);
    }

    public UserRepository() {
        auth = FirebaseAuth.getInstance();
    }

    public void setAuthStateChangedListener(OnAuthStateChanged listener) {
        auth.addAuthStateListener(auth -> {
            listener.onAuthStateChanged(getCurrentUser().getValue());
        });
    }

    public MutableLiveData<User> getCurrentUser() {
        return user;
    }

    public void loadCurrentUser() {
        if (auth.getCurrentUser() == null) {
            user.postValue(null);
        } else {
            User userFromAuth = new User();
            userFromAuth.setUserId(auth.getCurrentUser().getUid());
            userFromAuth.setEmail(auth.getCurrentUser().getEmail());
            user.postValue(userFromAuth);
        }
    }

    public void signIn(String email, String password, OnFailureListener onFailureListener ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                System.out.println(task.getException().getMessage());
                onFailureListener.onFailure(task.getException());
                // handle the error
            }
        });
    }

    public void signUp(String email, String password, OnFailureListener onFailureListener) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                System.out.println(task.getException().getMessage());
                onFailureListener.onFailure(task.getException());
                // handle the error
            }
        });
    }

    public void setIslandName(String islandName){
        User newSetting = new User();
        newSetting.userId = user.getValue().userId;
        newSetting.email = user.getValue().email;
        newSetting.characterName = user.getValue().characterName;
        newSetting.islandName = islandName;
        user.postValue(newSetting);
    }

    public void setCharacterName(String characterName){
        User newSetting = new User();
        newSetting.userId = user.getValue().userId;
        newSetting.email = user.getValue().email;
        newSetting.islandName = user.getValue().islandName;
        newSetting.characterName = characterName;
        user.postValue(newSetting);
    }

    public void logout() {

        auth.signOut();
        user.postValue(null);
    }
}
