package com.example.interntestapp.SignUpActivity;

import android.os.Bundle;

import com.example.interntestapp.R;
import com.example.interntestapp.SignUpFragment;
import com.example.interntestapp.Util.BaseActivity;

import androidx.fragment.app.FragmentManager;

public class SignUpActivity extends BaseActivity {

    private static final String SIGN_UP_FRAG = "CREATE_FRAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        FragmentManager manager = getSupportFragmentManager();

        SignUpFragment fragment = (SignUpFragment) manager.findFragmentByTag(SIGN_UP_FRAG);

        if(fragment==null){
            fragment = SignUpFragment.newInstance();
        }

        addFragmentToActivity(manager,fragment,R.id.root_activity_signUp,SIGN_UP_FRAG);

    }


}
