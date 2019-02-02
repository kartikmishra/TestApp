package com.example.interntestapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.interntestapp.Data.UserDetailModel;
import com.example.interntestapp.MainActivity.MainActivity;
import com.example.interntestapp.viewModel.NewUserDetailViewModel;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpFragment extends Fragment implements Validator.ValidationListener {

    private static final String TAG = "SignUpFragment";

    @NotEmpty
    @Length(min = 4)
    @BindView(R.id.nameEditText)
    EditText nameEt;

    @NotEmpty
    @Length(min = 10)
    @BindView(R.id.addressEditText) EditText addressEt;

    @NotEmpty
    @Length(min = 10)
    @BindView(R.id.phoneNumberEditText) EditText phoneNumberEt;

    @NotEmpty
    @Email
    @BindView(R.id.emeditText) EditText emailEt;

    @NotEmpty
    @Password(min = 8, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE)
    @BindView(R.id.passwordEditText) EditText passwordEt;


    @BindView(R.id.submitButton)
    Button signUpBtn;

    @BindView(R.id.logInButton)
    Button logInBtn;

    private String nameStr,addressStr,phoneNumberStr,emailStr,passwordStr;
    private Validator validator;
    private boolean validated = false;



    public SignUpFragment() {
    }

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    NewUserDetailViewModel userDetailViewModel;
    int currentId;
    int prevId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((RoomDemoApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
    }





    public static SignUpFragment newInstance(){
        return new SignUpFragment();
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        userDetailViewModel = ViewModelProviders.of(this,viewModelFactory).get(NewUserDetailViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.sign_up_fragment,container,false);


        ButterKnife.bind(this,view);

        validator = new Validator(this);
        validator.setValidationListener(this);

        Log.d(TAG, "onCreateView: "+prevId);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameStr = nameEt.getText().toString();
                addressStr = addressEt.getText().toString();
                phoneNumberStr = phoneNumberEt.getText().toString();
                emailStr = emailEt.getText().toString();
                passwordStr = passwordEt.getText().toString();
               prevId++;
               currentId = prevId;
                validator.validate();
                if(validated){

                    UserDetailModel userDetailModel = new UserDetailModel(
                           prevId,nameStr,phoneNumberStr,addressStr,emailStr,passwordStr
                    );
                    userDetailViewModel.addNewUserDetailToDatabase(userDetailModel);

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(), "Fill fields correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });

        return view;

    }

    @Override
    public void onValidationSucceeded() {
        validated = true;

    }

    @Override
    public void onResume() {
        super.onResume();
        if(UserListAdapter.list==null){
            prevId = 0;
        }
        else {
            prevId = currentId;
        }

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        validated = false;
        for(ValidationError error:errors){
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());
            if(view instanceof EditText){

                ((EditText)view).setError(message);
            }
            else {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
