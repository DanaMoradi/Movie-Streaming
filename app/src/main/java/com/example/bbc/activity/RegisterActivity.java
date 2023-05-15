package com.example.bbc.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bbc.databinding.ActivitySignUpBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {


    private ActivitySignUpBinding binding;
    private TextInputEditText nameEt;
    private TextInputEditText emailEt;
    private TextInputEditText phoneEt;
    private TextInputEditText passwordEt;
    private TextInputEditText r_passwordEt;
    private MaterialButton signUp;
    private MaterialButton gotoSignIn;

    private String name;
    private String email;
    private String phone;
    private String password;
    private String r_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();


    }


    private void init() {
        nameEt = binding.etSignUpName;
        emailEt = binding.etSignUpEmail;
        phoneEt = binding.etSignUpPhone;
        passwordEt = binding.etSignUpPassword;
        r_passwordEt = binding.etSignUpPasswordR;

        name = nameEt.getText().toString();
        email = emailEt.getText().toString();
        phone = phoneEt.getText().toString();
        password = passwordEt.getText().toString();
        r_password = r_passwordEt.getText().toString();

    }
}