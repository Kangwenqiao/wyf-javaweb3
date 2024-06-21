package com.mw.education.domain.compose;

import com.mw.education.domain.College;
import com.mw.education.domain.Teacher;

public class TeacherAndCollege {
    private Teacher teacher;
    private College college;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}
