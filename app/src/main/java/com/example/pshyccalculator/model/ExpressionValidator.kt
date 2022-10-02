package com.example.pshyccalculator.model

import java.util.*


// list of operators arranged based on their priority (less to high)
private val operators: List<Char> = listOf('+', '-', '*', '/', '%');

/*
 * class that contains all the operation for an Expression
 */
class ExpressionValidator {
    /*
     * method to validate parenthesis in mathematical expressions
     *
     * input ==> String expression
     * output ==> boolean value
     *
     * applied stack mechanism with integer to validate parenthesis
     */
    private fun isParenthesisOk(expression: String): Boolean {
        var parenthesis = 0
        // converting the expression to char Array to validate each character
        val newArr = expression.toCharArray()
        val len = newArr.size-1;
        for (index in 0..len) {
            /*
             * parenthesis count increases for each '(' & decreases for each ')'
             *
             * validation 1:
             *      if empty '()' available then   ==> return false
             *      else if any Parenthesis ')' appears before '('  ==> return false
             *
             * validation 2:
             *      (if all above true then),
             *      if final parenthesis count is 0   ==> returns true
             *      else    ==> returns false
             *
             */
            if (newArr[index] == '(' && newArr[index+1] == ')')
                return false;
            else if (newArr[index] == '(')
                parenthesis++
            else if (newArr[index] == ')' && parenthesis == 0)
                return false
            else if (newArr[index] == ')')
                parenthesis--
        }
        return parenthesis == 0
    }


    /*
     * method to validate the operator
     *
     * input ==> String expression
     * output ==> boolean value
     *
     * validation:
     *      if two operators appears next to another    ==> returns false
     *      if empty space      ==> ignored
     *      else        ==> returns true
     */
    private fun isOperatorOk(expression: String): Boolean {
        var symbolCount = 0
        var symbol = ' '
        var expressionArray = expression.toCharArray();
        if (operators.contains(expressionArray[expressionArray.size-1]))
            return false;
        for (letter in expressionArray) {
            if (symbol != ' ' && operators.contains(letter) || symbolCount > 1) {
                return false
            } else if (letter == ' ') {
                // ignored
            } else if (operators.contains(letter)) {
                symbol = letter
                symbolCount++
            } else {
                symbolCount = 0
                symbol = ' '
            }
        }
        return true
    }


    /*
     * method to validate dots in mathematical expressions
     *
     *  input ==> expression String
     *  output ==> boolean
     *
     * iteration through expression.toCharArray:
     *      if any number has two dot between operator or parenthesis or between operator & parenthesis  ==>  +2.3.3+ / (2.3.3) / (2.3.3+:
     *          ==> return false
     * else == returns true
     */
    private fun isDotOk(expression: String):Boolean{
        var dotCount =0;
        for(ele in expression.toCharArray()){
            if(ele == '.' && dotCount>0)
                return false
            else if(ele == '.')
                dotCount++;
            else if(ele=='(' || ele==')' || operators.contains(ele))
                dotCount=0;
        }
        return true;
    }


    /*
     * input ==> string equation
     * output ==> Boolean value
     *
     * method to validate the equation
     * parenthesis,  operator and dots are validated
     *
     */
    fun isValidExpression(expression: String): Boolean {
        return isParenthesisOk(expression) && isOperatorOk(expression) && isDotOk(expression)
    }
}
