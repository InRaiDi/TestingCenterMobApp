package com.dinara.dinarasharipova_vladislavostrikov_comp304lab4_ex1;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int patientId;
    private String firstname;
    private String lastname;
    private String department;
    private int nurseId;
    private int room;

    public int getPatientId() { return patientId; }
    public void setPatientId(int id) { this.patientId = id; }

    public String getFirstName() { return firstname; }
    public void setFirstName(String firstname) { this.firstname = firstname; }

    public String getLastName() { return lastname; }
    public void setLastName(String lastname) { this.lastname = lastname; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public int getNurseId() { return nurseId; }
    public void setNurseId(int nurseId) { this.nurseId = nurseId; }

    public int getRoom() { return room; }
    public void setRoom(int room) { this.room = room; }

}
