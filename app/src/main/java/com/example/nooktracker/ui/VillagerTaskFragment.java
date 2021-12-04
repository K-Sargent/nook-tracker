//package com.example.nooktracker.ui;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.nooktracker.R;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link VillagerTaskFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class VillagerTaskFragment extends Fragment {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public VillagerTaskFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment VillagerTaskFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static VillagerTaskFragment newInstance(String param1, String param2) {
//        VillagerTaskFragment fragment = new VillagerTaskFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_villager_task, container, false);
//    }
//}

package com.example.nooktracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.nooktracker.R;
import com.example.nooktracker.databinding.FragmentPreferencesBinding;
import com.example.nooktracker.databinding.FragmentTasksBinding;
import com.example.nooktracker.databinding.FragmentVillagerTaskBinding;
import com.example.nooktracker.models.Tasks;
import com.example.nooktracker.models.User;
import com.example.nooktracker.repositories.UserRepository;
import com.example.nooktracker.ui.tasks.TasksViewModel;

import java.util.Observable;

public class VillagerTaskFragment extends Fragment {

    private TasksViewModel tasksViewModel;
    private FragmentVillagerTaskBinding binding;
    private boolean isSaving = false;
    private boolean tasksExistsInDb = false;
    private MutableLiveData<Tasks> tasksToBeUpdated;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tasksViewModel = new ViewModelProvider(requireActivity()).get(TasksViewModel.class);
        binding = FragmentVillagerTaskBinding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);
        UserRepository repository = new UserRepository();
        repository.loadUserFromAuth();
        User currentUser = repository.getTaskUser();
        tasksViewModel.loadTasks(currentUser.userId);

        tasksToBeUpdated = tasksViewModel.getAllTasks();
        tasksToBeUpdated.observe(getViewLifecycleOwner(), tasks -> {
            if(tasks != null){
                binding.villagerCheckbox1.setChecked(tasks.villagerCheckbox1);
                binding.villagerCheckbox2.setChecked(tasks.villagerCheckbox2);
                binding.villagerCheckbox3.setChecked(tasks.villagerCheckbox3);
                binding.villagerCheckbox4.setChecked(tasks.villagerCheckbox4);
                binding.villagerCheckbox5.setChecked(tasks.villagerCheckbox5);
                binding.villagerCheckbox6.setChecked(tasks.villagerCheckbox6);
                binding.villagerCheckbox7.setChecked(tasks.villagerCheckbox7);
                binding.villagerCheckbox8.setChecked(tasks.villagerCheckbox8);
            }
        });

        tasksViewModel.getSaving().observe(getViewLifecycleOwner(), saving -> {
            if (!isSaving && saving) {
                isSaving = true;
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onPause() {

        tasksViewModel.setVillagerTasks(
                tasksToBeUpdated.getValue(),
                binding.villagerCheckbox1.isChecked(),
                binding.villagerCheckbox2.isChecked(),
                binding.villagerCheckbox3.isChecked(),
                binding.villagerCheckbox4.isChecked(),
                binding.villagerCheckbox5.isChecked(),
                binding.villagerCheckbox6.isChecked(),
                binding.villagerCheckbox7.isChecked(),
                binding.villagerCheckbox8.isChecked()
        );

        super.onPause();
    }

    @Override
    public void onDestroyView() {

        tasksViewModel.setVillagerTasks(
                tasksToBeUpdated.getValue(),
                binding.villagerCheckbox1.isChecked(),
                binding.villagerCheckbox2.isChecked(),
                binding.villagerCheckbox3.isChecked(),
                binding.villagerCheckbox4.isChecked(),
                binding.villagerCheckbox5.isChecked(),
                binding.villagerCheckbox6.isChecked(),
                binding.villagerCheckbox7.isChecked(),
                binding.villagerCheckbox8.isChecked()
        );

        super.onDestroyView();
        binding = null;
    }
}