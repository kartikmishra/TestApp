package com.example.interntestapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.interntestapp.Data.UserDetailModel;
import com.example.interntestapp.viewModel.UserDetailCollectionsViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivityFragment extends Fragment {

    private static final String TAG = "MainActivityFragment";

    private UserListAdapter adapter;
    private RecyclerView recyclerView;

    private List<UserDetailModel> list;
    public MainActivityFragment() {
    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    UserDetailCollectionsViewModel userDetailCollectionsViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((RoomDemoApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);


    }

    public static MainActivityFragment newInstance(){
        return new MainActivityFragment();
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userDetailCollectionsViewModel =
                ViewModelProviders.of(this,viewModelFactory).get(UserDetailCollectionsViewModel.class);

        userDetailCollectionsViewModel.getUserDetail().observe(this, new Observer<List<UserDetailModel>>() {
            @Override
            public void onChanged(List<UserDetailModel> userDetailModels) {
                if(MainActivityFragment.this.list == null){
                    setListData(userDetailModels);
                }
            }
        });
    }

    private void setListData(List<UserDetailModel> userDetailModels) {
        this.list = userDetailModels;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new UserListAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);


        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                linearLayoutManager.getOrientation()
        );


        recyclerView.addItemDecoration(
                itemDecoration
        );

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_activity_fragment,container,false);

        recyclerView = view.findViewById(R.id.userDetailRecyclerView);

        if(list!=null){
            Log.d(TAG, "onCreateView: "+list.get(0).getName());
        }


        return view;
    }


    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            //not used, as the first parameter above is 0
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                userDetailCollectionsViewModel.deleteListItem(
                        list.get(position)
                );

                //ensure View is consistent with underlying data
                list.remove(position);
                adapter.notifyItemRemoved(position);


            }
        };
        return simpleItemTouchCallback;
    }
}
