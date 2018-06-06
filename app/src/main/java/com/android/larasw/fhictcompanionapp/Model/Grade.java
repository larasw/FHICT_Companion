package com.android.larasw.fhictcompanionapp.Model;

/**
 * Created by Biespiel-PC on 10/5/2017.
 */

public class Grade{
    private String subject, grade;

    public Grade(String subject, String grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
