package com.TestingCenter.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.TestingCenter.Models.Nurse;
import com.TestingCenter.Models.Patient;
import com.TestingCenter.R;
import com.TestingCenter.ViewModels.PatientViewModel;

public class PatientAddActivity extends AppCompatActivity {

    private EditText etPatientId, etfirstName, etlastName, etroom;
    private PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_add);

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

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        //etPatientId = findViewById(R.id.etpatientId);
        etfirstName = findViewById(R.id.etpatientFirstName);
        etlastName = findViewById(R.id.etpatientLastName);
        etroom = findViewById(R.id.etpatientRoom);
        //etnurseId = findViewById(R.id.etn_NurseID);

    }

    public void addNewPatient(View view)
    {
        Patient patient = new Patient();
        //patient.setPatientId(Integer.valueOf(etPatientId.getText().toString()));
        patient.setFirstName(etfirstName.getText().toString());
        patient.setLastname(etlastName.getText().toString());
        patient.setRoom(Integer.valueOf(etroom.getText().toString()));

        SharedPreferences sharedPreferences = getSharedPreferences("Nurse",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Nurse", "");
        if(!json.matches("") && json != null){
            Nurse nurse = gson.fromJson(json, Nurse.class);
            patient.setNurseId(nurse.getNurseId());
            patient.setDepartment(nurse.getDepartment());
        }

        patientViewModel.insert(patient);

        startActivity(new Intent(this, PatientActivity.class));
    }
}
