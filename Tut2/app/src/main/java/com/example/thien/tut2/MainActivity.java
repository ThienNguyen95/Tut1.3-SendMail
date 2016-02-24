package com.example.thien.tut2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import javax.crypto.KeyAgreement;

public class MainActivity extends AppCompatActivity {
    private static RadioButton radioSexbuttonMale,radioSexbuttonFemale;
    private static RadioGroup RadionSex;
    private static Button NextButton;
    private static EditText Firstname;
    private static EditText Lastname;
    private static EditText Email;
    private static EditText Phone;

//    public void checkEmptyEditText(EditText s) {
//        if (TextUtils.isEmpty(s.getText().toString())) {
//            s.setError("You must enter something");
//            return;
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClickedListenerButton();
//        isValidEmail(sEmail);
//        isValidPhone(sPhone);


    }

    public void onClickedListenerButton() {
        RadionSex = (RadioGroup) findViewById(R.id.RG1);
        NextButton = (Button) findViewById(R.id.BT1_1);
        Firstname = (EditText) findViewById(R.id.ET1);
        Lastname = (EditText) findViewById(R.id.ET2);
        Email = (EditText) findViewById(R.id.ET3);
        Phone = (EditText) findViewById(R.id.ET4);
        radioSexbuttonMale = (RadioButton) findViewById(R.id.RB1);
        radioSexbuttonFemale = (RadioButton) findViewById(R.id.RB2);
//        int selectID = RadionSex.getCheckedRadioButtonId();
//        radioSexbutton = (RadioButton) findViewById(selectID);

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                checkEmptyEditText(Firstname);
//
//
//                checkEmptyEditText(Lastname);
//
//
//                checkEmptyEditText(Email);
//
//
//                checkEmptyEditText(Phone);

                if(Firstname.getText().toString().isEmpty()) Firstname.setError("you must enter Firstname");
                else if(Lastname.getText().toString().isEmpty()) Lastname.setError("you must enter Lastname");
                else if(Email.getText().toString().isEmpty()) Email.setError("you must enter Email");
                else if(!validationEmail(Email.getText().toString())){
                    Email.setError("invalid Email");
                    Email.requestFocus();
                }
                else if(Phone.getText().toString().isEmpty()) Phone.setError("you must enter Phone");
                else if(!validationPhone(Phone.getText().toString())){
                    Phone.setError("invalid Phone");
                    Phone.requestFocus();
                }
                else if (!radioSexbuttonMale.isChecked() && !radioSexbuttonFemale.isChecked()) {
                        Toast.makeText(MainActivity.this, "Forget check button", Toast.LENGTH_SHORT).show();
                    }

                else {
                        Intent screen2 = new Intent(MainActivity.this, Main2Activity.class);
                        screen2.putExtra(Main2Activity.EXTRA_FNAME, Firstname.getText().toString());
                        screen2.putExtra(Main2Activity.EXTRA_LNAME, Lastname.getText().toString());
                        screen2.putExtra(Main2Activity.EXTRA_EMAIL, Email.getText().toString());
                        screen2.putExtra(Main2Activity.EXTRA_PHONE, Phone.getText().toString());
                        startActivity(screen2);
                    }

            }

            private boolean validationPhone(String Phone) {
                if (TextUtils.isEmpty(Phone)) {
                    return false;
                } else {
                    return android.util.Patterns.PHONE.matcher(Phone).matches();
                }
            }

            private boolean validationEmail(String Email) {
                if (TextUtils.isEmpty(Email)) {
                    return false;
                } else {
                    return android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches();
                }
            }


        });
    }
}
