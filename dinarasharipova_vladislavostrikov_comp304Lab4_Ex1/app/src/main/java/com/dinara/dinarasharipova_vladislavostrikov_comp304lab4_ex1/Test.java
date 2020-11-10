package com.dinara.dinarasharipova_vladislavostrikov_comp304lab4_ex1;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Test {

    @PrimaryKey(autoGenerate = true)
    private int testId;
    private int patientId;
    private int nurseId;
    private int BPL;
    private int BPH;
    private int temperature;
    private int oximetry;
    private int erythrocytes;
    private int leukocytes;

    public int getTestId() { return testId; }
    public void setTestId(int id) { this.testId = id; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int id) { this.patientId = id; }
    public int getNurseId() { return nurseId; }
    public void setNurseId(int nurseId) { this.nurseId = nurseId; }

    public int getBPL() { return BPL; }
    public void setBPL(int BPL) { this.BPL = BPL; }

    public int getBPH() { return BPH; }
    public void setBPH(int BPH) { this.BPH = BPH; }

    public int getTemperature() { return temperature; }
    public void setTemperature(int temperature) { this.temperature = temperature; }

    public int getOximetry() { return oximetry; }
    public void setOximetry(int oximetry) { this.oximetry = oximetry; }

    public int getErythrocytes() { return erythrocytes; }
    public void setErythrocytes(int erythrocytes) { this.erythrocytes = erythrocytes; }

    public int getLeukocytes() { return leukocytes; }
    public void setLeukocytes(int leukocytes) { this.leukocytes = leukocytes; }
}
