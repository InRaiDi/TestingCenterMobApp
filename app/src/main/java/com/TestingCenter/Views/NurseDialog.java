package com.TestingCenter.Views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;
import com.TestingCenter.Models.Nurse;
import com.TestingCenter.R;
import com.TestingCenter.ViewModels.NurseViewModel;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class NurseDialog extends AppCompatDialogFragment {

    private EditText nurseId;
    private EditText nursePassword;
    private NurseViewModel nurseViewModel;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    NurseDialogListener listener;

    private EditText nurseIdEt,firstNameEt,lastNameEt,passwordEt;
    private Spinner spinDepartments;
    private String[] departments;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        nurseViewModel = ViewModelProviders.of(this).get(NurseViewModel.class);
        nurseViewModel.allNurses().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(List<Nurse> nurses) {

            }
        });

        if (MainActivity.Action == "Login") {
            View view = inflater.inflate(R.layout.activity_login, null);
            nurseId = view.findViewById(R.id.etNurseId);
            nursePassword = view.findViewById(R.id.etNursePassword);
            builder.setView(view)
                    .setPositiveButton("login", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (nurseViewModel.checkLogin(Integer.parseInt(nurseId.getText().toString()), nursePassword.getText().toString())) {
                                Nurse nurse = new Nurse();
                                nurseViewModel.allNurses().observe(getActivity(), new Observer<List<Nurse>>() {
                                    @Override
                                    public void onChanged(List<Nurse> nurses) {
                                        for (Nurse n : nurses) {
                                            if (n.getNurseId() == Integer.parseInt(nurseId.getText().toString())) {
                                                nurse.setNurseId(n.getNurseId());
                                                nurse.setFirstName(n.getFirstName());
                                                nurse.setLastname(n.getLastname());
                                                nurse.setDepartment(n.getDepartment());
                                                nurse.setPassword(n.getPassword());
                                                Gson gson = new Gson();
                                                String text = gson.toJson(nurse);
                                                editor.putString("Nurse", text);
                                                editor.commit();
                                                listener.changeAction("Account");
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }).setNeutralButton("register", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.changeAction("Register");
                }
            });

        }else if(MainActivity.Action == "Register"){
            View view = inflater.inflate(R.layout.activity_nurse_registration, null);
            nurseIdEt=view.findViewById(R.id.nurseIDEt);
            firstNameEt=view.findViewById(R.id.firstNameEt);
            lastNameEt=view.findViewById(R.id.lastNameEt);
            spinDepartments=view.findViewById(R.id.departmentSpin);
            departments = getResources().getStringArray(R.array.departments);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                    (getContext(), android.R.layout.simple_spinner_dropdown_item, departments);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinDepartments.setAdapter(adapter);
            passwordEt=view.findViewById(R.id.passwordEt);



            builder.setView(view)
                    .setPositiveButton("register", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Nurse nurse = new Nurse();

                            nurse.setNurseId(Integer.parseInt(nurseIdEt.getText().toString()));
                            nurse.setFirstName(firstNameEt.getText().toString());
                            nurse.setLastname(lastNameEt.getText().toString());
                            nurse.setDepartment(spinDepartments.getSelectedItem().toString());
                            nurse.setPassword(passwordEt.getText().toString());

                            listener.register(nurse);
                            listener.changeAction("Login");
                        }
                    });
        }
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (NurseDialogListener)context;
            sharedPreferences = context.getSharedPreferences("Nurse", MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement NurseDialogListener");
        }
    }

    public interface NurseDialogListener{
        void register(Nurse nurse);
        void changeAction(String action);
    }

}
