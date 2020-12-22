package com.TestingCenter.Views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.TestingCenter.R;
import com.TestingCenter.ViewModels.PatientViewModel;
import com.TestingCenter.Models.Patient;

public class PatientEditActivity extends AppCompatActivity {
    private EditText etPatientId, etfirstName, etlastName, etroom, etnurseId;
    private Patient patient;
    private PatientViewModel patientViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_edit);

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

        //etPatientId=findViewById(R.id.etpatientId);
        etfirstName=findViewById(R.id.etpatientFirstName);
        etlastName=findViewById(R.id.etpatientLastName);
        //etdepartment=findViewById(R.id.etpatientDepartment);
        etroom=findViewById(R.id.etpatientRoom);

        int patientId = getIntent().getIntExtra("patientId", 0);
        Log.i("Patient", "Entered in PatientEditActivity class and patient is "+ String.valueOf(patientId));

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);

        patient = patientViewModel.getPatientById(patientId);
        Log.i("Patient", "Entered in PatientEditActivity class and patientId is "+ patient.toString());

        //etPatientId.setText(String.valueOf(patient.getPatientId()));
        etfirstName.setText(patient.getFirstName().toString());
        etlastName.setText(patient.getLastname().toString());
        //etdepartment.setText(patient.getDepartment().toString());
        etroom.setText(String.valueOf(patient.getRoom()).toString());


    }

    public void savePatient(View view)
    {
        patient.setFirstName(etfirstName.getText().toString());
        patient.setLastname(etlastName.getText().toString());
        //patient.setDepartment(etdepartment.getText().toString());
        patient.setRoom(Integer.parseInt(etroom.getText().toString()));

        //patient.setNurseId(Integer.valueOf(etnurseId.getText().toString()));

        patientViewModel.update(patient);
        Toast.makeText(this, "Patient Updated Successfully.", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this,PatientHistoryActivity.class);
        intent.putExtra("patientId", patient.getPatientId());
        startActivity(intent);
    }

}
