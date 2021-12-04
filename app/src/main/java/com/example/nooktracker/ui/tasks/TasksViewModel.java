package com.example.nooktracker.ui.tasks;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nooktracker.models.Tasks;
import com.example.nooktracker.repositories.UserRepository;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TasksViewModel extends ViewModel {
    UserRepository repository;
    //ObservableArrayList<Tasks> allTasks;
    MutableLiveData<Tasks> tasks = new MutableLiveData<>();
    MutableLiveData<Boolean> saving = new MutableLiveData<>();
    MutableLiveData<String> errorMessage = new MutableLiveData<>();
    FirebaseFirestore db;

    public TasksViewModel() {
        errorMessage.setValue("");
        db = FirebaseFirestore.getInstance();
        saving.setValue(false);
    }

    public MutableLiveData<Boolean> getSaving() { return saving; }

    public MutableLiveData<Tasks> getAllTasks() {
        return tasks;
    }

    public void nullifyTasks(){
        tasks = null;
    }

    public MutableLiveData<Tasks> getTasks() { return tasks; }

    public void setSelectedUser(Tasks selectedUser) { this.tasks.setValue(selectedUser);}

    public MutableLiveData<String> getErrorMessage() { return errorMessage; }

    public void createTasks(String userId) {
        boolean villagerCheckbox1 = false;
        boolean villagerCheckbox2 = false;
        boolean villagerCheckbox3 = false;
        boolean villagerCheckbox4 = false;
        boolean villagerCheckbox5 = false;
        boolean villagerCheckbox6 = false;
        boolean villagerCheckbox7 = false;
        boolean villagerCheckbox8 = false;

        boolean shoppingCheckbox1 = false;
        boolean shoppingCheckbox2 = false;

        boolean rocksCheckbox1 = false;
        boolean rocksCheckbox2 = false;
        boolean rocksCheckbox3 = false;
        boolean rocksCheckbox4 = false;
        boolean rocksCheckbox5 = false;
        boolean rocksCheckbox6 = false;

        boolean recipeCheckbox1 = false;
        boolean recipeCheckbox2 = false;

        boolean moneyTreeCheckbox1 = false;

        boolean flowersCheckbox1 = false;

        saving.setValue(true);
        errorMessage.setValue("");
        Tasks tasks = new Tasks(userId);
        db
                .collection("userTasks")
                .document(tasks.getUserId())
                .set(tasks)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                    saving.setValue(false);
                });
    }

    public void setVillagerTasks(
            Tasks tasks,
            boolean villagerCheckbox1,
            boolean villagerCheckbox2,
            boolean villagerCheckbox3,
            boolean villagerCheckbox4,
            boolean villagerCheckbox5,
            boolean villagerCheckbox6,
            boolean villagerCheckbox7,
            boolean villagerCheckbox8

    ) {
        saving.setValue(true);
        errorMessage.setValue("");

        tasks.setVillagerCheckbox1(villagerCheckbox1);
        tasks.setVillagerCheckbox2(villagerCheckbox2);
        tasks.setVillagerCheckbox3(villagerCheckbox3);
        tasks.setVillagerCheckbox4(villagerCheckbox4);
        tasks.setVillagerCheckbox5(villagerCheckbox5);
        tasks.setVillagerCheckbox6(villagerCheckbox6);
        tasks.setVillagerCheckbox7(villagerCheckbox7);
        tasks.setVillagerCheckbox8(villagerCheckbox8);


        db
                .collection("userTasks")
                .document(tasks.getUserId())
                .set(tasks)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                    saving.setValue(false);
                });
    }

    public void setShoppingTasks(
            Tasks tasks,
            boolean shoppingCheckbox1,
            boolean shoppingCheckbox2

    ) {
        saving.setValue(true);
        errorMessage.setValue("");

        tasks.setShoppingCheckbox1(shoppingCheckbox1);
        tasks.setShoppingCheckbox2(shoppingCheckbox2);


        db
                .collection("userTasks")
                .document(tasks.getUserId())
                .set(tasks)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                    saving.setValue(false);
                });
    }

    public void setRocksTasks(
            Tasks tasks,

            boolean rocksCheckbox1,
            boolean rocksCheckbox2,
            boolean rocksCheckbox3,
            boolean rocksCheckbox4,
            boolean rocksCheckbox5,
            boolean rocksCheckbox6

    ) {
        saving.setValue(true);
        errorMessage.setValue("");

        tasks.setRocksCheckbox1(rocksCheckbox1);
        tasks.setRocksCheckbox2(rocksCheckbox2);
        tasks.setRocksCheckbox3(rocksCheckbox3);
        tasks.setRocksCheckbox4(rocksCheckbox4);
        tasks.setRocksCheckbox5(rocksCheckbox5);
        tasks.setRocksCheckbox6(rocksCheckbox6);


        db
                .collection("userTasks")
                .document(tasks.getUserId())
                .set(tasks)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                    saving.setValue(false);
                });
    }

    public void setRecipeTasks(
            Tasks tasks,

            boolean recipeCheckbox1,
            boolean recipeCheckbox2

    ) {
        saving.setValue(true);
        errorMessage.setValue("");


        tasks.setRecipeCheckbox1(recipeCheckbox1);
        tasks.setRecipeCheckbox2(recipeCheckbox2);


        db
                .collection("userTasks")
                .document(tasks.getUserId())
                .set(tasks)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                    saving.setValue(false);
                });
    }

    public void setMoneyTreeTasks(
            Tasks tasks,

            boolean moneyTreeCheckbox1

    ) {
        saving.setValue(true);
        errorMessage.setValue("");

        tasks.setMoneyTreeCheckbox1(moneyTreeCheckbox1);

        db
                .collection("userTasks")
                .document(tasks.getUserId())
                .set(tasks)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                    saving.setValue(false);
                });
    }

    public void setFlowersTasks(
            Tasks tasks,
            boolean flowersCheckbox1
    ) {
        saving.setValue(true);
        errorMessage.setValue("");

        tasks.setFlowersCheckbox1(flowersCheckbox1);

        db
                .collection("userTasks")
                .document(tasks.getUserId())
                .set(tasks)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                    saving.setValue(false);
                });
    }

    public void loadTasks(String userId) {
        errorMessage.setValue("");
        db
                .collection("userTasks")
                .whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(task-> {
                    if (task.isSuccessful()) {
                        QuerySnapshot collection = task.getResult();
                        ArrayList<Tasks> tasksArrayList = (ArrayList<Tasks>) collection.toObjects(Tasks.class);
                        if(tasksArrayList.isEmpty()){
                           Tasks newTasks = new Tasks(userId);
                           tasks.postValue(newTasks);
                           return;
                        }
                        tasksArrayList.forEach((theTask) -> {
                            //Log.d("__FIREBASE", theTask.getUserId());
                            tasks.postValue(theTask);

                        });
                    } else {
                        errorMessage.postValue(task.getException().getMessage());
                    }
                });
    }

//    public Tasks getTasks(String userId) {
//        if (allTasks == null) {
//            allTasks = new ObservableArrayList<>();
//            loadTasks(userId);
//        }
//        return allTasks.get(0);
//    }

}
