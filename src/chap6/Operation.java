package chap6;

/**
 * Created by taoyong on 7/2/16.
 */
// Enum type with constant specific data and methods.
public enum Operation {
    // separated by colon
    PLUS ("+")   { // constant-specific class body
        double eval(double x, double y) {  // constant-specific method implementation.
            return x + y;
        }
    },
    MINUS ("-") {
        double eval(double x, double y) {
            return x - y;
        }
    },
    TIMES ("*")  {
        double eval(double x, double y) {
            return x * y;
        }
    },
    DIVIDE ("/") {
        double eval(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    // Do arithmetic op represented by this constant
    abstract double eval(double x, double y);
}
