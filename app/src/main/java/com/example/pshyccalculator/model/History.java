package com.example.pshyccalculator.model;

import java.util.HashMap;


/*
 * class  to store all data of the calculator for re-usability
 */
public class History {
    // hashmap that store expression as the key and result as the value
    private static final HashMap<String, String> calHistory = new HashMap<>();
    private static History history;
    private String result = "0";
    private String expression = "0";

    public static History getInstance(){
        if(history==null){
            history = new History();
        }
        return history;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExpression() {
        return this.expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }


    // method to get the calculator history
    public static HashMap<String, String> getCalHistory(){
        return calHistory;
    }

    /*
     * method to append new data
     *
     * input ==> String expression as key, String result as value
     */
    public static void setCalHistory(String key, String value){
        calHistory.put(key, value);
    }
}
