package com.example.pshyccalculator.model


/*
 * class to carry basic mathematical operations
 */
class BasicMath{
    companion object{
        /*
         * method to add two numbers
         *
         * input ==> two double values (num1, num2)
         * output ==> double value (addition value)
        */
        @JvmStatic fun addThis(num1: Double, num2: Double): Double = num1 + num2;

        /*
          * method to subtract two numbers
          *
          * input ==> two double values (num1, num2)
          * output ==> double value (num1-num2)
         */
        @JvmStatic fun subtract(num1: Double, num2: Double): Double = num1 - num2;

        /*
          * method to multiply two numbers
          *
          * input ==> two double values (num1, num2)
          * output ==> double value (multiplication of num1 & num2 )
         */
        @JvmStatic fun multiplyThis(num1: Double, num2: Double): Double = num1 * num2;


        /*
          * method to divide two numbers
          *
          * input ==> two double values (num1, num2)
          * output ==> double value (division value of num1 by num2)
          *
          * @Throws Exception when num2 is 0
         */
        @Throws(ZeroDivisionException::class)
        @JvmStatic fun divisionOf(num1: Double, num2: Double): Double {
            if (num2 == 0.0) throw ZeroDivisionException("Can't divide by Zero");
            return num1 / num2;
        }

        /*
          * method to find remainder two numbers
          *
          * input ==> two double values (num1, num2)
          * output ==> double value (remainder value)
          *
          * @Throws Exception when num2 is 0
         */
        @Throws(ZeroDivisionException::class)
        @JvmStatic fun remainderOf(num1: Double, num2: Double): Double {
            if (num2 == 0.0) throw ZeroDivisionException("Can't divide by Zero");
            return num1 % num2;
        }
    }
}