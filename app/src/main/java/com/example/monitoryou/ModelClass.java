package com.example.monitoryou;

public class ModelClass {
    String Quetions;
    String o1;
    String o2;

    public ModelClass() {

    }

    public ModelClass( String quetions, String o1, String o2 ) {
        Quetions = quetions;
        this.o1 = o1;
        this.o2 = o2;
    }

    public String getQuetions() {
        return Quetions;
    }

    public void setQuetions( String quetions ) {
        Quetions = quetions;
    }

    public String getO1() {
        return o1;
    }

    public void setO1( String o1 ) {
        this.o1 = o1;
    }

    public String getO2() {
        return o2;
    }

    public void setO2( String o2 ) {
        this.o2 = o2;
    }
}