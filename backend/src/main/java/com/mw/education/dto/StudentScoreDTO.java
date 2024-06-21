package com.mw.education.dto;

import java.math.BigDecimal;

public class StudentScoreDTO {
    private String studentName;
    private String studentCode;
    private BigDecimal score;

    // Getters and setters

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
