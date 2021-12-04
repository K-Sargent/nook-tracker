package com.example.nooktracker.ui.villagers;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nooktracker.models.Villager;

public class VillagersViewModel extends ViewModel {
    ObservableArrayList<Villager> villagers;
    MutableLiveData<Boolean> saving = new MutableLiveData<>();
    MutableLiveData<Villager> selectedVillager = new MutableLiveData<>();

    public VillagersViewModel() {
        saving.setValue(false);
    }

    public MutableLiveData<Boolean> getSaving() {
        return saving;
    }

    public MutableLiveData<Villager> getSelectedVillager() {
        return selectedVillager;
    }

    public void setSelectedVillager(Villager selectedVillager) {
        this.selectedVillager.setValue(selectedVillager);
    }

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