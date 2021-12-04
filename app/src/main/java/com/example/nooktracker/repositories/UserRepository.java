package com.example.nooktracker.repositories;

import com.example.nooktracker.models.Tasks;
import com.example.nooktracker.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRepository {
    FirebaseAuth auth;

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
            listener.onAuthStateChanged(getCurrentUser());
        });
    }

    public User getCurrentUser() {
        User user = new User();
        FirebaseUser fbUser = auth.getCurrentUser();
        if (fbUser == null) return null;

        user.userId = fbUser.getUid();
        user.email = fbUser.getEmail();
        return user;
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
        getCurrentUser().setIslandName(islandName);
    }

    public void setCharacterName(String characterName){
        getCurrentUser().setCharacterName(characterName);
    }

    public void logout() {
        auth.signOut();
    }

    //----------------------------------------------------------------------------------------------

    public Tasks getCurrentTasks() {
        Tasks tasks = new Tasks();
        FirebaseUser fbUser = auth.getCurrentUser();
        if (fbUser == null) return null;

        tasks.userId = fbUser.getUid();

        return tasks;
    }

    public void setCheckboxes(Boolean[] checkboxes){
        getCurrentTasks().setAll(checkboxes);
    }

}
