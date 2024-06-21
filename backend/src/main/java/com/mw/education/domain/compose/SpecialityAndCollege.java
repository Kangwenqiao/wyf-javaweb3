package com.mw.education.domain.compose;

import com.mw.education.domain.Speciality;
import com.mw.education.domain.College;

public class SpecialityAndCollege {
    private Speciality speciality;
    private College college;

    // Getters and Setters

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
