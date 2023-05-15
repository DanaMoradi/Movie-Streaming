package com.example.bbc.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.chaos.view.PinView;
import com.example.bbc.R;
import com.example.bbc.activity.MainActivity;
import com.example.bbc.application.app;
import com.example.bbc.databinding.VerifyPhoneFragmentBinding;
import com.example.bbc.db.sharedPrefrences.UserSharedPref;
import com.example.bbc.model.UserModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class VerifyPhoneFragment extends Fragment implements View.OnClickListener {

    private VerifyPhoneFragmentBinding binding;
    private UserSharedPref userSharedPref;


    private MaterialButton back;
    private MaterialButton confirm;

    private TextView phoneNumber;
    private PinView pinView;

    private static int random;
    private UserModel user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = VerifyPhoneFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();

        user = getArguments().getParcelable(app.REGISTER);

        phoneNumber.setText(user.getPhone());
        back.setOnClickListener(this);
        confirm.setOnClickListener(this);

        generateCode();
        showKeyboard();

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            sendCode();
        } else {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 100);
        }

    }


    private void init() {
        userSharedPref = new UserSharedPref(getContext());

        back = binding.btnVerifyBack;
        confirm = binding.btnVerifyConfirm;
        phoneNumber = binding.tvPhoneNumber;
        pinView = binding.pvVerifyCode;
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_verify_back) {
            if (user.getFull_name() != null) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fram_register, new SignUpFragment());
                fragmentTransaction.commit();
            } else {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fram_register, new SignInFragment());
                fragmentTransaction.commit();
            }

        }
        if (v.getId() == R.id.btn_verify_confirm) {
            int pinCode = Integer.parseInt(pinView.getText().toString());
            if (pinCode == random) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                finishRegister();

            } else
                Toast.makeText(getContext(), "کد وارد شده صحیح نمیباشد.", Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressLint("SetTextI18n")
    private void generateCode() {
        random = (int) (Math.random() * 10000);
        while (true) {
            if (random < 999) generateCode();
            else break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.e("onRequestPerm", requestCode + "");
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendCode();
            } else {
                Toast.makeText(getContext(), "not having permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendCode() {
        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts = smsManager.divideMessage("\nکد احراز هویت شما :" + random);
        smsManager.sendMultipartTextMessage(user.getPhone(), null, parts, null, null);
    }


    private void showKeyboard() {
        pinView.requestFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.showSoftInput(pinView, InputMethodManager.SHOW_IMPLICIT);
    }

    private void finishRegister() {
        userSharedPref.clearAll();
        userSharedPref.setUserLogIn(true);
    }


}
