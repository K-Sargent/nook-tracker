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
// * Use the {@link ShoppingTaskFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class ShoppingTaskFragment extends Fragment {
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
//    public ShoppingTaskFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ShoppingTaskFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ShoppingTaskFragment newInstance(String param1, String param2) {
//        ShoppingTaskFragment fragment = new ShoppingTaskFragment();
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
//        return inflater.inflate(R.layout.fragment_shopping_task, container, false);
//    }
//}

package com.example.nooktracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
import com.example.nooktracker.databinding.FragmentShoppingTaskBinding;
import com.example.nooktracker.models.Tasks;
import com.example.nooktracker.models.User;
import com.example.nooktracker.repositories.UserRepository;
import com.example.nooktracker.ui.tasks.TasksViewModel;

import java.util.Observable;

public class ShoppingTaskFragment extends Fragment {

    private TasksViewModel tasksViewModel;
    private FragmentShoppingTaskBinding binding;
    private boolean isSaving = false;
    private boolean tasksExistsInDb = false;
    private MutableLiveData<Tasks> tasksToBeUpdated;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //tasksViewModel = new ViewModelProvider(this).get(TasksViewModel.class);
        tasksViewModel = new ViewModelProvider(requireActivity()).get(TasksViewModel.class);
        binding = FragmentShoppingTaskBinding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);
        UserRepository repository = new UserRepository();
        repository.loadUserFromAuth();
        User currentUser = repository.getTaskUser();
        tasksViewModel.loadTasks(currentUser.userId);

        tasksToBeUpdated = tasksViewModel.getAllTasks();
        tasksToBeUpdated.observe(getViewLifecycleOwner(), tasks -> {
            if(tasks != null){
                binding.shoppingCheckBox1.setChecked(tasks.shoppingCheckbox1);
                binding.shoppingCheckbox2.setChecked(tasks.shoppingCheckbox2);
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

        tasksViewModel.setShoppingTasks(
                tasksToBeUpdated.getValue(),
                binding.shoppingCheckBox1.isChecked(),
                binding.shoppingCheckbox2.isChecked()
        );

        super.onPause();
    }

    @Override
    public void onDestroyView() {

        tasksViewModel.setShoppingTasks(
                tasksToBeUpdated.getValue(),
                binding.shoppingCheckBox1.isChecked(),
                binding.shoppingCheckbox2.isChecked()
        );

        super.onDestroyView();
        binding = null;
    }
}