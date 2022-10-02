package com.example.pshyccalculator.model

import com.example.pshyccalculator.view_model.MainViewModel



// list of operators arranged based on their priority (less to high)
private val operators: List<String> = listOf("+", "-", "*", "/", "%");

/*
 * MainModelRepository is the class that will have all the business logics. here in this case
 * calculator logics
 */
class MainModelRepository {
    companion object {

        /*
         * static method to solve the expression and update it in the history
         *  input ==> String operation type
         *  output ==> Unit
         */
        @JvmStatic fun textOperations(opType: String) {
            if(History.getInstance().expression == "0")
                History.getInstance().expression = "";

            // assigning temporary variable for expression data
            var tempExpression = History.getInstance().expression;


            /*
             * completing corresponding operation for the corresponding operation type
             *
             * if operationType == del:
             *      removes the last character of the expression if (length > 1) else replace it with 0
             * else if operationType == clear:
             *      assign expression =0
             * else:
             *      if expression is 0 and operator is one among (*, /, %):
             *          expression = 1 + operatorType
             *      else if expression is 0 and operator is one among (+, -):
             *          expression = 0 + operator
             *      else:
             *          expression += opType(number/operator)
             *
             * finally updates value
             */
            History.getInstance().expression = when (opType) {
                ("del") -> {
                    if(tempExpression == "" || tempExpression == "0" ) {
                        "0";
                    }else {
                        tempExpression.substring(0, tempExpression.length - 1);
                    }
                };
                ("clear") -> "0";

                else  -> {

                    if(opType in listOf<String>("*", "/", "%")  && tempExpression == "") {
                        if(History.getInstance().result != "0") {
                            History.getInstance().result + opType
                        }else{
                            "1$opType"
                        }
                    }else if (opType in listOf<String>("+", "-")  && tempExpression == ""){
                        if(History.getInstance().result != "0") {
                            History.getInstance().result + opType
                        }else{
                            "0$opType"
                        }
                    }else if (operators.contains(opType) && operators.contains(tempExpression.substring(tempExpression.length-1, tempExpression.length-0))){
                        // TODO need to update for 0 multiplication and division
                        tempExpression.substring(0, tempExpression.length-1) + opType
                    }else{
                        tempExpression+opType;
                    }
                };
            }
            MainViewModel().getViewModel().setValue();
        }
    }


    /*
      * method that initialize the expression solving
      * input ==> String expression
      * output ==> boolean (true if valid expression else false)
     */
    fun solveThis(_expression:String):Boolean{
        var expression:String = _expression;
        if(ExpressionValidator().isValidExpression(expression)){
            try{
                expression = ExpressionOp().updateExpressionIfRequired(expression);
                var result = ExpressionOp().solve(expression);
                History.setCalHistory(expression, result.toString());
                History.getInstance().result = if(result%1 > 0){
                    result.toString()
                }else{
                    result.toInt().toString()
                };
                textOperations("clear");
                return true;
            }catch (ignored:Exception){}
        }
        return false;
    }
}


/*
 * user defined Exception @ZeroDivisionException
 * Exception to report can't divide by zero
 */
internal class ZeroDivisionException(message: String?) : Exception(message) // sending the message to parent (Exception) Class

