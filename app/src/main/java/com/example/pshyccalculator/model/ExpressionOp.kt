package com.example.pshyccalculator.model


// list of operators arranged based on their priority (less to high)
private val operators: List<Char> = listOf('+', '-', '*', '/', '%');

/*
 * class to process all the operation of the expression
 */
class ExpressionOp{

    /*
    * method to determine the operation
    *
    * input ==> String operator(+,-,*,/,%), String number1, String number2
    * output ==> String resultant of the operation with respect to operator
    */
    @Throws(Exception::class)
    private fun doThisOperation(operation: String, number1: String, number2: String): String {
        var result = "";
        when (operation) {
            "+" -> {
                result = "" + BasicMath.addThis(number1.toDouble(), number2.toDouble());
            }

            "-" -> {
                result = "" + BasicMath.subtract(number1.toDouble(), number2.toDouble());
            }

            "*" -> {
                result = "" + BasicMath.multiplyThis(number1.toDouble(), number2.toDouble());
            }

            "/" -> {
                result = "" + BasicMath.divisionOf(number1.toDouble(), number2.toDouble());
            }

            "%" -> {
                result = "" + BasicMath.remainderOf(number1.toDouble(), number2.toDouble());
            }
        }
        return result;
    }


    /*
     * method to solve the simple equation using priority (@recursion method)
     *
     * input ==> ArrayList<String> (splited Expression ==> [1, +, 3, -, 5])
     * output ==> ArrayList<String> (single resultant value, ==> [-1] as per above)
     *
     * solve high priority, replace, & call itself  ==>  [1, +, 3, -, 5] ==> [1, +, -2] ==> itself([1, +, -2])
     * if ArrayList length == 1         ==> return [resultantValue]
     */
    @Throws(Exception::class)
    private fun solveSimple(col: ArrayList<String>): ArrayList<String> {
        var priority = 0;
        var opIndex = 0;
        if (col.size == 1) return col;
        for (index in col.indices) {

            // checking whether it is operator
            // if yes:
            //      if priority > ofAllBefore:
            //          priority=itsPriority
            //      else:
            //          pass
            if (operators.contains(col[index][0]) && col[index].length == 1) {
                if (operators.indexOf(col[index][0]) + 1 > priority) {
                    priority = operators.indexOf(col[index][0]) + 1;
                    opIndex = index;
                }
            }
        }

        //  do operation for high priority operator
        // replace with resultant
        val result = doThisOperation(col[opIndex], col[opIndex - 1], col[opIndex + 1]);
        col.removeAt(opIndex - 1);
        col.removeAt(opIndex - 1);
        col.removeAt(opIndex - 1);
        col.add(opIndex - 1, result);
        return solveSimple(col);
    }


    /*
     * method to split simple expression
     *
     * input ==> String Expression (1+3-5)
     * output ==> ArrayList<String> splited Expression ([1, +, 3, -, 5])
     *
     * iterate through charArray of expression:
     *      if operator other than unary '-':
     *          append previous number to list
     *          append operator
     *      else
     *          append number
     *
     * finally  ==> returns ArrayList<String>
     */
    private fun splitSimple(expression: String): java.util.ArrayList<String> {
        val col = java.util.ArrayList<String>();
        var operatorCount = 0;
        var expressionElement = "";
        for ((index, character) in expression.toCharArray().withIndex()) {
            if (operators.contains(character) && operatorCount == 0 && index != 0) {
                col.add(expressionElement);
                expressionElement = "";
                col.add(character.toString() + "");
                operatorCount++;
            } else {
                if (operatorCount > 0) operatorCount--;
                expressionElement += character;
            }
        }
        col.add(expressionElement);
        return col;
    }


    /*
     * method to check whether the expression has parenthesis
     */
    private fun isParenthesisAvailable(expression: String): Boolean {
        for (character in expression.toCharArray()) {
            if (character == '(') return true;
        }
        return false;
    }


    /*
     * method to solve parenthesis expression
     *
     * iterate through expression charArray:        ((3+2)+3+(4+2))
     *      if parenthesis found:
     *          append to temp till respective close parenthesis    ==> (3+2)  ==> +3+   ==>  (4+2)
     *          then append to ArrayList    ==>[(3+2)]     ==> [(3+2), +3+]    ==> [(3+2), +3+, (4+2)]
     * iterate through ArrayList:
     *      solve all splited parenthesis and replace with resultant        ==> [5, +3+, 6]
     *
     * joinArrayList Strings        ==> 5+3+6
     *
     * send solvedStringExpression to splitSimple(5+3+6)   ==> returns splitedArrayList<String> [5, +, 3, +, 6]
     * send splitedArrayList<String> to solveSimple([5, +, 3, +, 6]) ==> return ArrayList<String> of single resultant [14];
     *
     * finally      ==> returns String resultantValue 14
     */
    @Throws(Exception::class)
    private fun solveParenthesis(expression: String): String {
        var result = "";
        var temp = "";
        var parenthesis = 0;
        val col = java.util.ArrayList<String>();
        for (character in expression.toCharArray()) {
            if (parenthesis > 0) {
                if (character == '(') {
                    temp += character;
                    parenthesis++;
                } else if (character == ')' && parenthesis == 1) {
                    temp += character;
                    col.add(temp);
                    temp = "";
                    parenthesis--;
                } else if (character == ')') {
                    temp += character;
                    parenthesis--;
                } else {
                    temp += character;
                }
            } else if (character == '(' && temp !== "") {
                col.add(temp);
                temp = "" + character;
                parenthesis++;
            } else if (character == '(') {
                temp += character;
                parenthesis++;
            } else {
                temp += character;
            }
        }
        // if temp not empty append to arrayList
        if (temp !== "") col.add(temp)
        for (index in col.indices) {
            if (col[index].startsWith("(")) {
                temp = col[index];
                col[index] = solveParenthesis(temp.substring(1, temp.length - 1));
            }
        }

        for (ele in col) {

            result += if (ele[0] in '0'..'9' && ele[ele.length - 1] >= '0' && ele[0] <= '9') {
                solveSimple(splitSimple(ele))[0];
            } else ele;
        }
        return result;
    }


    /*
     *  input ==> String Expression     (3+4)+2+(4+5)
     *  output ==> resultant value of the expression
     *
     *  if parenthesis available:
     *       solve parenthesis     ==> 7+2+(4+5)    ==> 7+2+9
     *  solve simple equation   ==> [7, +, 2, +, 9] ==> 18
     *
     * finally      ==> returns result  ==>18
     *
     */
    @Throws(Exception::class)
    private fun getAnswerOf(expression: String): Double {
        var expression = expression;
        var result:Double = 0.0;
        if (isParenthesisAvailable(expression)) {
            expression = solveParenthesis(expression);
        }
        result = solveSimple(splitSimple(expression))[0].toDouble();
        return result;
    }

    /*
     * method to initialize solving process
     */
    fun solve(expression: String):Double = getAnswerOf(expression);


    /*
     * method to reassign 'of' with '*' in mathematical expression
     */
    @Throws(Exception::class)
    fun updateExpressionIfRequired(_expression:String):String{
        var expression:String = _expression;
        var expressionArray: CharArray = expression.toCharArray();
        var len = expressionArray.size-2;
        for(index in 0..len){
            if(expressionArray[index+1] == '(' && !operators.contains(expressionArray[index])){
                return updateExpressionIfRequired(expression.substring(0,index+1) + "*" + expression.substring(index+1,expression.length));
            }
        }
        return expression;
    }
}