package com.TestingCenter.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.TestingCenter.Models.Nurse;
import com.TestingCenter.R;
import com.TestingCenter.ViewModels.NurseViewModel;

public class MainActivity extends AppCompatActivity implements NurseDialog.NurseDialogListener {
    private Button btnLogin;
    private Button btnPatients;
    private NurseViewModel nurseViewModel;

    public static String Action = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin=findViewById(R.id.loginBtn);
        btnPatients = findViewById(R.id.patientsBtn);
    }
    public void sendToLogin(View view){
        //Intent intent=new Intent(this,LoginActivity.class);
        //startActivity(intent);
        Action = "Login";
        openDialog();

    }

    public void sendToPatients(View view){
        Intent intent = new Intent(this, PatientActivity.class);
        startActivity(intent);
    }

    public void openDialog(){
        NurseDialog loginDialog = new NurseDialog();
        loginDialog.show(getSupportFragmentManager(), "login dialog");

    }

    @Override
    public void changeAction(String action) {
        if(action.matches("Login")){
            Action = action;
        }else if(action.matches("Register")){
            Action = action;
            openDialog();
        }else if(action.matches("Account")){
            Action = action;
            btnLogin.setVisibility(View.INVISIBLE);
            btnPatients.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void register(Nurse nurse) {
        try{
            nurseViewModel= ViewModelProviders.of(this).get(NurseViewModel.class);
            nurseViewModel.insert(nurse);
            Toast.makeText(this,"The new nurse was added to the system successfully!", Toast.LENGTH_LONG).show();
            changeAction("Login");
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }

}
