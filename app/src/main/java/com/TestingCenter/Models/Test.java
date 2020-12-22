package com.TestingCenter.Models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity=Patient.class,
                        parentColumns="patientId",
                        childColumns = "patientId"),


                        @ForeignKey(entity=Nurse.class,
                                parentColumns="nurseId",
                                childColumns = "nurseId")
        },
        indices={@Index("patientId"),@Index("nurseId")})



public class Test {
    @PrimaryKey(autoGenerate = true)
    private int testId;

    private int patientId,nurseId,BPL, BPM,temperature;
    private String auscultation,bodyInspection;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public int getBPL() {
        return BPL;
    }

    public void setBPL(int BPL) {
        this.BPL = BPL;
    }

    public int getBPM() {
        return BPM;
    }

    public void setBPM(int BPM) {
        this.BPM = BPM;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getAuscultation() {
        return auscultation;
    }

    public void setAuscultation(String auscultation) {
        this.auscultation = auscultation;
    }

    public String getBodyInspection() {
        return bodyInspection;
    }

    public void setBodyInspection(String bodyInspection) {
        this.bodyInspection = bodyInspection;
    }
}
