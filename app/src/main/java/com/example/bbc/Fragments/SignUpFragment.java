package com.example.bbc.Fragments;

import static com.example.bbc.R.id.btn_signUp;
import static com.example.bbc.R.id.btn_signUp_gotoSignIn;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.VolleyError;
import com.example.bbc.R;
import com.example.bbc.activity.MainActivity;
import com.example.bbc.application.app;
import com.example.bbc.databinding.SignUpFragmentBinding;
import com.example.bbc.db.sharedPrefrences.UserSharedPref;
import com.example.bbc.db.volley.UserApi;
import com.example.bbc.interfaces.RegisterCallBack;
import com.example.bbc.model.UserModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpFragment extends Fragment implements View.OnClickListener, RegisterCallBack {

    private SignUpFragmentBinding binding;

    private UserSharedPref userSharedPref;

    private TextInputEditText nameEt;
    private TextInputEditText emailEt;
    private TextInputEditText phoneEt;
    private TextInputEditText passwordEt;
    private TextInputEditText r_passwordEt;

    private TextInputLayout nameTil;
    private TextInputLayout emailTil;
    private TextInputLayout phoneTil;
    private TextInputLayout passwordTil;
    private TextInputLayout r_passwordTil;

    private MaterialButton signUp;
    private MaterialButton gotoSignIn;


    private String name;
    private String email;
    private String phone;
    private String password;
    private String r_password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SignUpFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        init();

        if (userSharedPref.isUserLogin()) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        }


        fillEditText();
        gotoSignIn.setOnClickListener(this);
        signUp.setOnClickListener(this);


    }


    private void init() {

        userSharedPref = new UserSharedPref(getContext());

        nameEt = binding.etSignUpName;
        emailEt = binding.etSignUpEmail;
        phoneEt = binding.etSignUpPhone;
        passwordEt = binding.etSignUpPassword;
        r_passwordEt = binding.etSignUpPasswordR;

        nameTil = binding.tilSignUpName;
        emailTil = binding.tilSignUpEmail;
        phoneTil = binding.tilSignUpPhone;
        passwordTil = binding.tilSignUpPassword;
        r_passwordTil = binding.tilSignUpPasswordR;


        signUp = binding.btnSignUp;
        gotoSignIn = binding.btnSignUpGotoSignIn;

    }

    private void getTextEditText() {
        name = nameEt.getText().toString().trim();
        email = emailEt.getText().toString().trim();
        phone = phoneEt.getText().toString().trim();
        password = passwordEt.getText().toString().trim();
        r_password = r_passwordEt.getText().toString().trim();
    }

    private void fillEditText() {
        nameEt.setText(userSharedPref.getName());
        emailEt.setText(userSharedPref.getEmail());
        phoneEt.setText(userSharedPref.getPhone());
        passwordEt.setText(userSharedPref.getPassword());
        r_passwordEt.setText(userSharedPref.getPassword());

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case btn_signUp_gotoSignIn:
                fragmentTransaction.replace(R.id.fram_register, new SignInFragment());
                fragmentTransaction.commit();
                break;
            case btn_signUp:
                getTextEditText();
                if (validation()) break;
                checkEmailAndPhone();

                break;
        }
    }

    private Boolean validation() {
        boolean error = false;
        if (!(nameEt.length() > 0)) {
            nameTil.setErrorEnabled(true);
            nameTil.setError("نام و نام خانوادگی نباید خالی باشد.");
            error = true;
        } else if (nameEt.length() > 25) {
            nameTil.setErrorEnabled(true);
            nameTil.setError("نام و نام خانوادگی بیش از حد مجاز است.");
            error = true;
        } else if (!name.contains(" ")) {
            nameTil.setErrorEnabled(true);
            nameTil.setError("نام و نام خانوادگی با فاصله جدا کنید.");
            error = true;
        } else {
            nameTil.setErrorEnabled(false);
        }
        if (phoneEt.length() < 10) {
            phoneTil.setErrorEnabled(true);
            phoneTil.setError("موبایل نباید کمتر از 11 رقم باشد.");
            error = true;
        } else if (!phone.startsWith("09")) {
            phoneTil.setErrorEnabled(true);
            phoneTil.setError("شماره مبایل صحیح نیست.");
            error = true;
        } else {
            phoneTil.setErrorEnabled(false);
        }
        if (!(passwordEt.length() > 6)) {
            passwordTil.setErrorEnabled(true);
            passwordTil.setError("پسورد نباید کمتر از 6 رقم باشد.");
            error = true;
        } else {
            passwordTil.setErrorEnabled(false);
        }
        if (!(r_passwordEt.length() > 6)) {
            r_passwordTil.setErrorEnabled(true);
            r_passwordTil.setError("تکرار رمز عبور نباید کمتر از 6 رقم باشد.");
            error = true;
        } else if (!(r_password.equals(password))) {
            r_passwordTil.setErrorEnabled(true);
            r_passwordTil.setError("تکرار رمز عبور با رمز عبور همخوانی ندارد.");
            error = true;
        } else {
            r_passwordTil.setErrorEnabled(false);
        }


        return error;
    }

    private void checkEmailAndPhone() {
        UserApi userApi = new UserApi(getContext(), app.TAG);
        userApi.isEmailPhoneExist(phone, email, this);
    }

    @Override
    public void onEmailAndPhoneCheck(String emailResult, String phoneResult) {
        boolean error = false;
        if (emailResult != null && emailResult.equals("Email Exists")) {
            emailTil.setErrorEnabled(true);
            emailTil.setError("ایمیل ثبت شده است");
            error = true;
        } else {
            emailTil.setErrorEnabled(false);
        }
        if (phoneResult != null && phoneResult.equals("Phone Exists")) {
            phoneTil.setErrorEnabled(true);
            phoneTil.setError("شماره مبایل قبلا ثبت شده است.");
            error = true;
        } else {
            phoneTil.setErrorEnabled(false);
        }


        if (!error) {
            intentToSendCode();
        }

    }

    @Override
    public void onAddUserCallBack(String item) {

    }

    @Override
    public void onError(VolleyError error) {

        Toast.makeText(getContext(), "خظایی به وجود آمده دوباره امتحان کنید.", Toast.LENGTH_SHORT).show();
    }


    private void intentToSendCode() {
        UserModel user = new UserModel();
        user.setFull_name(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);

        Bundle bundle = new Bundle();
        bundle.putParcelable(app.REGISTER, user);
        VerifyPhoneFragment verifyPhoneFragment = new VerifyPhoneFragment();
        verifyPhoneFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fram_register, verifyPhoneFragment);

        userSharedPref.saveRegister(user);

        fragmentTransaction.commit();
    }


}
