package com.dinara.dinarasharipova_vladislavostrikov_comp304lab4_ex1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Nurse {

    @PrimaryKey(autoGenerate = true)
    private int nurseId;
    private String firstname;
    private String lastname;
    private String department;
    private String password;

    public int getNurseId() { return nurseId; }
    public void setNurseId(int nurseId) { this.nurseId = nurseId; }

    public String getFirstName() { return firstname; }
    public void setFirstName(String firstname) { this.firstname = firstname; }

    public String getLastName() { return lastname; }
    public void setLastName(String lastname) { this.lastname = lastname; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
