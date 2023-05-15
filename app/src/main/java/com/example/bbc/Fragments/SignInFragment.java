package com.example.bbc.Fragments;

import static com.example.bbc.R.id.btn_signIn_forgetPassword;
import static com.example.bbc.R.id.btn_signIn_gotoSignUp;
import static com.example.bbc.R.id.fram_register;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bbc.databinding.SignInFragmentBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignInFragment extends Fragment implements View.OnClickListener {

    private SignInFragmentBinding binding;
    private TextInputEditText phoneEt;
    private TextInputEditText passwordEt;

    private MaterialButton gotoSignUp;
    private MaterialButton forgetPassword;

    private String phone;
    private String password;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SignInFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        gotoSignUp.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);
    }


    private void init() {
        phoneEt = binding.etSignInPhone;
        passwordEt = binding.etSignInPassword;
        gotoSignUp = binding.btnSignInGotoSignUp;
        forgetPassword = binding.btnSignInForgetPassword;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        switch (v.getId()) {
            case btn_signIn_gotoSignUp:
                fragmentTransaction.replace(fram_register, new SignUpFragment());
                fragmentTransaction.commit();
                break;
            case btn_signIn_forgetPassword:
                fragmentTransaction.replace(fram_register, new ForgetPasswordFragment());
                fragmentTransaction.commit();
                break;
        }

    }

}