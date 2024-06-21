package com.mw.education.domain.compose;

import com.mw.education.domain.ClassGroup;
import com.mw.education.domain.Speciality;
import com.mw.education.domain.College;

public class ClassGroupAndSpecialityAndCollege {
    private ClassGroup classGroup;
    private Speciality speciality;
    private College college;

    // Getters and Setters

    public ClassGroup getClassGroup() {
        return classGroup;
    }

    public void setClassGroup(ClassGroup classGroup) {
        this.classGroup = classGroup;
    }

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
