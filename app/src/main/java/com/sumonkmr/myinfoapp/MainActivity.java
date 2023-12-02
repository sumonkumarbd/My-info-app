package com.sumonkmr.myinfoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edName,edAddress,edEmail,edMobile,edBloodG;
    Button saveBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("my_info",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        HookUps();
        saveBtn.setOnClickListener(v -> {
            SetData();
        });
        GetData();
    }

    private void HookUps() {
        edName = findViewById(R.id.edName);
        edAddress = findViewById(R.id.edAddress);
        edEmail = findViewById(R.id.edEmail);
        edMobile = findViewById(R.id.edMobile);
        edBloodG = findViewById(R.id.edBloodG);
        saveBtn = findViewById(R.id.saveBtn);

        EditTextHelper(edName);
    }

    private void SetData(){
        if (edName != null) editor.putString("name",edName.getText().toString());
        else ShowToast("Please Inter your name.");
        if (edAddress != null) editor.putString("address",edAddress.getText().toString());
        else ShowToast("Please Inter your address.");
        if (edEmail != null) editor.putString("mail",edEmail.getText().toString());
        else ShowToast("Please Inter your Email.");
        if (edMobile != null) editor.putString("mobile",edMobile.getText().toString());
        else ShowToast("Please Inter your Mobile no.");
        if (edBloodG != null) editor.putString("bloodG",edBloodG.getText().toString());
        else ShowToast("Please Inter your blood group.");
        editor.apply();
        if (edName != null && edAddress != null && edEmail != null && edMobile != null && edBloodG != null){
            ShowToast("Your Details is added successfully!");
        }else {
            ShowToast("Please be careful!");
        }
    }

    private void GetData(){
        String name = sharedPreferences.getString("name",null);
        String address = sharedPreferences.getString("address",null);
        String email = sharedPreferences.getString("mail",null);
        String mobile = sharedPreferences.getString("mobile",null);
        String bloodG = sharedPreferences.getString("bloodG",null);

        if (name != null) edName.setText(name);
        else return;
        if (address != null) edAddress.setText(address);
        else return;
        if (email != null) edEmail.setText(email);
        else return;
        if (mobile != null) edMobile.setText(mobile);
        else return;
        if (bloodG != null) edBloodG.setText(bloodG);
        else return;
    }

    private void EditTextHelper(EditText editText){
        if (editText.getText().toString().length() == 0){
            editText.setCursorVisible(true);
        }else {
            editText.setCursorVisible(false);
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() <= 0){
                    editText.setError("Invalid Value!");
                }
            }
        });
    }


    private void ShowToast(String toastMsg){
        Toast.makeText(this, toastMsg, Toast.LENGTH_SHORT).show();
    }
}