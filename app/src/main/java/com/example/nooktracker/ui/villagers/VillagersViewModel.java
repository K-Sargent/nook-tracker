package com.example.nooktracker.ui.villagers;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nooktracker.models.Tasks;
import com.example.nooktracker.models.Villager;
import com.example.nooktracker.models.Villagers;
import com.example.nooktracker.repositories.UserRepository;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class VillagersViewModel extends ViewModel {
    UserRepository repository;
    //ObservableArrayList<Tasks> allTasks;
    MutableLiveData<Villagers> villagers = new MutableLiveData<>();
    MutableLiveData<Boolean> saving = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();
    FirebaseFirestore db;

    public VillagersViewModel() {
        errorMessage.setValue("");
        db = FirebaseFirestore.getInstance();
        saving.setValue(false);
    }

    public MutableLiveData<Boolean> getSaving() { return saving; }

    public MutableLiveData<Villagers> getAllVillagers() {
        return villagers;
    }

    public void nullifyTasks(){
        villagers = null;
    }

    public MutableLiveData<Villagers> getTasks() { return villagers; }

    public void setSelectedUser(Villagers selectedUser) { this.villagers.setValue(selectedUser);}

    public MutableLiveData<String> getErrorMessage() { return errorMessage; }

    public void createVillagers(String userId) {

        Villager villager1 = new Villager();
        Villager villager2 = new Villager();
        Villager villager3 = new Villager();
        Villager villager4 = new Villager();
        Villager villager5 = new Villager();
        Villager villager6 = new Villager();
        Villager villager7 = new Villager();
        Villager villager8 = new Villager();

        saving.setValue(true);
        errorMessage.setValue("");
        Villagers villagers = new Villagers(userId);
        db
                .collection("userVillagers")
                .document(villagers.getUserId())
                .set(villagers)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                    saving.setValue(false);
                });
    }

    public void setVillagers(
            Villagers villagers,
            Villager villager1,
            Villager villager2,
            Villager villager3,
            Villager villager4,
            Villager villager5,
            Villager villager6,
            Villager villager7,
            Villager villager8

    ) {
        saving.setValue(true);
        errorMessage.setValue("");

        villagers.setVillager1(villager1);
        villagers.setVillager2(villager2);
        villagers.setVillager3(villager3);
        villagers.setVillager4(villager4);
        villagers.setVillager5(villager5);
        villagers.setVillager6(villager6);
        villagers.setVillager7(villager7);
        villagers.setVillager8(villager8);


        db
                .collection("userVillagers")
                .document(villagers.getUserId())
                .set(villagers)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                    saving.setValue(false);
                });
    }

    public void loadVillagers(String userId) {
        errorMessage.setValue("");
        db
                .collection("userVillagers")
                .whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(task-> {
                    if (task.isSuccessful()) {
                        QuerySnapshot collection = task.getResult();
                        ArrayList<Villagers> villagersArrayList = (ArrayList<Villagers>) collection.toObjects(Villagers.class);
                        if(villagersArrayList.isEmpty()){
                            Villagers newVillagers = new Villagers(userId);
                            villagers.postValue(newVillagers);
                            return;
                        }
                        villagersArrayList.forEach((theTask) -> {
                            //Log.d("__FIREBASE", theTask.getUserId());
                            villagers.postValue(theTask);

                        });
                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                });
    }



//    ObservableArrayList<Villager> villagers;
//    MutableLiveData<Boolean> saving = new MutableLiveData<>();
//    MutableLiveData<Villager> selectedVillager = new MutableLiveData<>();
//
//    public VillagersViewModel() {
//        saving.setValue(false);
//    }
//
//    public MutableLiveData<Boolean> getSaving() {
//        return saving;
//    }
//
//    public MutableLiveData<Villager> getSelectedVillager() {
//        return selectedVillager;
//    }
//
//    public void setSelectedVillager(Villager selectedVillager) {
//        this.selectedVillager.setValue(selectedVillager);
//    }





    // REWORK BELOW FOR DATABASE CONNECTION

//    public void createVillager(String name, String id, String birthday, String personality, String species) {
//        saving.setValue(true);
//        Villager villager = new Villager(name, id, birthday, personality, species);
//        db
//                .collection("villagers")
//                .document(villager.getUserId() + "_" + villager.getTimestamp())
//                .set(villager)
//                .addOnCompleteListener(task -> {
//                            if (task.isSuccessful()) {
//                                villagers.add(villager);
//                            } else {
//                                // handle error
//                            }
//                            saving.setValue(false);
//                        }
//                );
//
//    }
//
//    public void updateVillager(Villager villager, String amount, String details) {
//        saving.setValue(true);
//        villager.setAmount(Double.parseDouble(amount));
//        villager.setDetails(details);
//        db
//                .collection("villagers")
//                .document(villager.getUserId() + "_" + villager.getTimestamp())
//                .set(villager)
//                .addOnCompleteListener(task -> {
//                            if (task.isSuccessful()) {
//                                int i = villagers.indexOf(villager);
//                                villagers.set(i, villager);
//                            } else {
//                                // handle error
//                            }
//                            saving.setValue(false);
//                        }
//                );
//    }
//
//    public void deleteVillager(Villager villager) {
//        saving.setValue(true);
//        db.collection("villagers")
//                .document(villager.getUserId() + "_" + villager.getTimestamp())
//                .delete()
//                .addOnCompleteListener(task -> {
//                    if(task.isSuccessful()) {
//                        villagers.remove(villager);
//                    } else {
//                        // handle errors
//                    }
//                    saving.setValue(false);
//                });
//    }
//
//    public ObservableArrayList<Villager> getVillagers(String userId) {
//        if (villagers == null) {
//            villagers = new ObservableArrayList<>();
//            loadVillagers(userId);
//        }
//        return villagers;
//    }
//
//    private void loadVillagers(String userId) {
//        db.collection("villagers")
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        villagers.addAll(task.getResult().toObjects(Villager.class));
//                    } else {
//                        // handle error
//                    }
//                });
//    }




//    private MutableLiveData<String> mText;
//
//    public VillagersViewModel() {
//        mText = new MutableLiveData<>();
//        mText.setValue("This is dashboard fragment");
//    }
//
//    public LiveData<String> getText() {
//        return mText;
//    }
}