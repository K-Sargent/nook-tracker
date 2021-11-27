package com.example.nooktracker.ui.preferences;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nooktracker.models.User;
import com.example.nooktracker.repositories.UserRepository;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class PreferencesViewModel extends ViewModel {
    UserRepository repository;
    ObservableArrayList<User> users;
    MutableLiveData<User> user = new MutableLiveData<>();
    MutableLiveData<Boolean> saving = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();
    FirebaseFirestore db;


    public PreferencesViewModel() {
        errorMessage.setValue("");
        db = FirebaseFirestore.getInstance();
        saving.setValue(false);
    }

    public MutableLiveData<Boolean> getSaving() { return saving; }

    public MutableLiveData<User> getUser() { return user; }

    public void setSelectedUser(User selectedUser) { this.user.setValue(selectedUser);}

    public MutableLiveData<String> getErrorMessage() { return errorMessage; }

    public void createUser(String email, String userId, String islandName, String characterName) {
        saving.setValue(true);
        errorMessage.setValue("");
        User user = new User(
                email,
                userId,
                islandName,
                characterName
        );
        db
                .collection("users")
                .document(user.getUserId())
                .set(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                    saving.setValue(false);
                });
    }

    public void setUser(User user, String islandName, String characterName) {
        saving.setValue(true);
        errorMessage.setValue("");
        user.setIslandName(islandName);
        user.setCharacterName(characterName);
        db
                .collection("users")
                .document(user.getUserId())
                .set(user)
                .addOnCompleteListener(task -> {
                   if (task.isSuccessful()) {

                   } else {
                       errorMessage.postValue(task.getException().getMessage());
                   }
                   saving.setValue(false);
                });
    }

    public void loadUser(String userId) {
        errorMessage.setValue("");
        db
                .collection("users")
                .whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(task-> {
                    if (task.isSuccessful()) {
                        QuerySnapshot collection = task.getResult();
                        ArrayList<User> userArrayList = (ArrayList<User>) collection.toObjects(User.class);
                        userArrayList.forEach((user) -> {
                            Log.d("__FIREBASE", user.getCharacterName());
                        });
                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                });
    }

    public User getUser(String userId) {
        if (users == null) {
            users = new ObservableArrayList<>();
            loadUser(userId);
        }
        return users.get(0);
    }
}