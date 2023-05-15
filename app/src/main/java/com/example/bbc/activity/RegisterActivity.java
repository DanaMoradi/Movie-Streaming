package com.example.bbc.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.bbc.Fragments.SignUpFragment;
import com.example.bbc.R;
import com.example.bbc.databinding.ActivitySignUpBinding;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {


    private ActivitySignUpBinding binding;
    private Long backPressedTime = 0L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fram_register, new SignUpFragment());
        fragmentTransaction.commit();


    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 4000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Snackbar.make(binding.framRegister, "برای خروج محددا کلیک کنید", Snackbar.LENGTH_SHORT).show();

        }
        backPressedTime = System.currentTimeMillis();
    }

}