package com.example.interntestapp.MainActivity;

import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.interntestapp.MainActivityFragment;
import com.example.interntestapp.R;
import com.example.interntestapp.Util.BaseActivity;

public class MainActivity extends BaseActivity {
    private static final String MAIN_FRAG = "CREATE_FRAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();

        MainActivityFragment fragment = (MainActivityFragment) manager.findFragmentByTag(MAIN_FRAG);

        if(fragment==null){
            fragment = MainActivityFragment.newInstance();
        }

        addFragmentToActivity(manager,fragment,R.id.root_activity_list,MAIN_FRAG);

    }
}
