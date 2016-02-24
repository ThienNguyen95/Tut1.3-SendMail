package com.example.thien.tut2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private static Button Done;
    public static final String EXTRA_FNAME = "first_name";
    public static final String EXTRA_LNAME = "last_name";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_PHONE = "phone";
    public static final String EXTRA_SALARY = "salary";
    private String mFirstname,mLastname,mEmail,mPhone,mSalary;
    private CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mFirstname=getIntent().getStringExtra(EXTRA_FNAME);
        mLastname=getIntent().getStringExtra(EXTRA_LNAME);
        mEmail=getIntent().getStringExtra(EXTRA_EMAIL);
        mPhone=getIntent().getStringExtra(EXTRA_PHONE);

        checkBox1 = (CheckBox)findViewById(R.id.CB2_1);
        checkBox2 = (CheckBox)findViewById(R.id.CB2_2);
        checkBox3 = (CheckBox)findViewById(R.id.CB2_3);
        checkBox4 = (CheckBox)findViewById(R.id.CB2_4);
        checkBox5 = (CheckBox)findViewById(R.id.CB2_5);
        checkBox6 = (CheckBox)findViewById(R.id.CB2_6);

        final SeekBar SB = (SeekBar) findViewById(R.id.SB2_1);
        final TextView salary = (TextView) findViewById(R.id.TV2_2);
        Done = (Button) findViewById(R.id.BT2_1);
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox1.isChecked()||checkBox2.isChecked()||checkBox3.isChecked()||checkBox4.isChecked()
                        ||checkBox5.isChecked()||checkBox6.isChecked()) {
                    Intent screen3 = new Intent(Main2Activity.this, Main3Activity.class);
                    screen3.putExtra(Main2Activity.EXTRA_FNAME, mFirstname);
                    screen3.putExtra(Main2Activity.EXTRA_LNAME, mLastname);
                    screen3.putExtra(Main2Activity.EXTRA_EMAIL, mEmail);
                    screen3.putExtra(Main2Activity.EXTRA_PHONE, mPhone);
                    screen3.putExtra(Main2Activity.EXTRA_SALARY, salary.getText().toString());
                    startActivity(screen3);
                }
                else
                {
                    Toast.makeText(Main2Activity.this, "Must check at least one box", Toast.LENGTH_SHORT).show();
                }
            }
        });
        SB.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        SB.setMax(10000);
                        progress /= 100;
                        progress *= 100;
                        salary.setText(String.valueOf(progress));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

    }


}
