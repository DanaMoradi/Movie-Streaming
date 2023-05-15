package com.example.bbc.Fragments;

import static com.example.bbc.R.id.btn_forgetPass_gotoSignIn;
import static com.example.bbc.R.id.btn_forgetPass_gotoSignUp;
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

import com.example.bbc.databinding.ForgetPasswordFragementBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ForgetPasswordFragment extends Fragment implements View.OnClickListener {

    private ForgetPasswordFragementBinding binding;
    private TextInputEditText phoneEt;
    private MaterialButton gotoSignUp;
    private MaterialButton gotoSignIn;
    private MaterialButton send;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ForgetPasswordFragementBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        gotoSignUp.setOnClickListener(this);
        gotoSignIn.setOnClickListener(this);
    }


    private void init() {
        phoneEt = binding.etForgetPassPhone;

        gotoSignIn = binding.btnForgetPassGotoSignIn;
        gotoSignUp = binding.btnForgetPassGotoSignUp;
        send = binding.btnForgetPassSend;

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case btn_forgetPass_gotoSignIn:
                fragmentTransaction.replace(fram_register, new SignInFragment());
                fragmentTransaction.commit();
                break;
            case btn_forgetPass_gotoSignUp:
                fragmentTransaction.replace(fram_register, new SignUpFragment());
                fragmentTransaction.commit();
        }
    }
}
