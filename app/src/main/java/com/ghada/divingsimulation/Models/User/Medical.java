package com.ghada.divingsimulation.Models.User;

import java.io.Serializable;

public class Medical implements Serializable {
    String question1;
    String question2;
    String question3;
    String question4;
    String question5;
    String question6;
    String question7;
    String question8;
    String question9;
    String question10;

    public Medical() {
        question1 = "";
        question2 = "";
        question3 = "";
        question4 = "";
        question5 = "";
        question6 = "";
        question7 = "";
        question8 = "";
        question9 = "";
        question10 = "";
    }

    public Medical(String question1, String question2, String question3, String question4, String question5, String question6, String question7, String question8, String question9, String question10) {
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.question7 = question7;
        this.question8 = question8;
        this.question9 = question9;
        this.question10 = question10;
    }

    public String getQuestion1() {
        return question1;
    }

    public void setQuestion1(String question1) {
        this.question1 = question1;
    }

    public String getQuestion2() {
        return question2;
    }

    public void setQuestion2(String question2) {
        this.question2 = question2;
    }

    public String getQuestion3() {
        return question3;
    }

    public void setQuestion3(String question3) {
        this.question3 = question3;
    }

    public String getQuestion4() {
        return question4;
    }

    public void setQuestion4(String question4) {
        this.question4 = question4;
    }

    public String getQuestion5() {
        return question5;
    }

    public void setQuestion5(String question5) {
        this.question5 = question5;
    }

    public String getQuestion6() {
        return question6;
    }

    public void setQuestion6(String question6) {
        this.question6 = question6;
    }

    public String getQuestion7() {
        return question7;
    }

    public void setQuestion7(String question7) {
        this.question7 = question7;
    }

    public String getQuestion8() {
        return question8;
    }

    public void setQuestion8(String question8) {
        this.question8 = question8;
    }

    public String getQuestion9() {
        return question9;
    }

    public void setQuestion9(String question9) {
        this.question9 = question9;
    }

    public String getQuestion10() {
        return question10;
    }

    public void setQuestion10(String question10) {
        this.question10 = question10;
    }

    @Override
    public String toString() {
        return "Medical{" +
                "question1='" + question1 + '\'' +
                ", question2='" + question2 + '\'' +
                ", question3='" + question3 + '\'' +
                ", question4='" + question4 + '\'' +
                ", question5='" + question5 + '\'' +
                ", question6='" + question6 + '\'' +
                ", question7='" + question7 + '\'' +
                ", question8='" + question8 + '\'' +
                ", question9='" + question9 + '\'' +
                ", question10='" + question10 + '\'' +
                '}';
    }
}
