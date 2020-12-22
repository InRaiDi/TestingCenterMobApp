package com.TestingCenter.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.TestingCenter.Models.Test;
import com.TestingCenter.R;
import com.TestingCenter.ViewModels.TestViewModel;

public class TestInfoActivity extends AppCompatActivity {

    // Creating objects
    private TestViewModel testViewModel;
    private TextView et_BPL, et_BML, et_Temprature,et_BAuscutation,et_BInspection,et_nurseId,et_testId;

    private int TestId;
    private Test testObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_info);

        BottomNavigationView navView = (BottomNavigationView)findViewById(R.id.navView);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Class activity;
                Intent intent;
                switch (item.getItemId()){
                    case R.id.nav_patients:
                        activity = PatientActivity.class;
                        intent = new Intent(getApplicationContext(), activity);
                        startActivity(intent);
                        return false;
                    case R.id.nav_home:
                        activity = MainActivity.class;
                        intent = new Intent(getApplicationContext(), activity);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });

        // Getting the information
        TestId = getIntent().getIntExtra("testId", 0);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        testObj = testViewModel.getTestById(TestId);

        // Initializing those objects.
        et_nurseId=findViewById(R.id.tV_Nurse_ID);
        et_testId=findViewById(R.id.tV_TestId);
        et_BPL=findViewById(R.id.tV_Patient_BPL);
        et_BML= findViewById(R.id.tV_Patient_BPM);
        et_Temprature= findViewById(R.id.tV_Patient_Temp);
        et_BAuscutation= findViewById(R.id.tV_Patient_Aus);
        et_BInspection= findViewById(R.id.tV_Patient_BInsepection);
        testViewModel= ViewModelProviders.of(this).get(TestViewModel.class);

        // Attributing values.
        et_nurseId.setText(String.valueOf(testObj.getNurseId()));
        et_testId.setText(String.valueOf(testObj.getTestId()));
        et_BPL.setText(String.valueOf(testObj.getBPL()));
        et_BML.setText(String.valueOf(testObj.getBPM()));
        et_BAuscutation.setText(testObj.getAuscultation());
        et_BInspection.setText(testObj.getBodyInspection());
        et_Temprature.setText(String.valueOf(testObj.getTemperature()));

    }
    public void backToMainActivity(View view){
        Intent intent=new Intent(this,PatientHistoryActivity.class);
        intent.putExtra("patientId", testObj.getPatientId());
        startActivity(intent);

    }

    public void deleteTest(View view)
    {
        testViewModel.delete(testObj);
        Intent intent=new Intent(this,PatientHistoryActivity.class);
        intent.putExtra("patientId", testObj.getPatientId());
        startActivity(intent);
    }






}
