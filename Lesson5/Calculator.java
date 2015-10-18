import java.lang.Math;

/**
 * Class implements calculator
 */
public class Calculator {
	
    /** Calculation result */
    private int result;


    /**
     * Get the result
     * @return Calculation result
     */
    public int getResult() {
        return this.result;
    }


    /**
     * Clear calculation result
     */
    public void clearResult() {
        this.result = 0;
    }


    /**
     * Make calculation depending on the arguments
     * @param firstNumber First number for calculation
     * @param operation Mathematics operation for calculation
     * @param secondNumber Second number for calculation
     */
    public void calculate(int firstNumber, MathOperation operation, int secondNumber) {
		switch (operation) {
			case ADD:	
				this.add(firstNumber, secondNumber);
				break;
			case SUBTRACT:	
				this.subtract(firstNumber, secondNumber);
				break;
			case MULTIPLY:	
				this.multiply(firstNumber, secondNumber);
				break;
			case DIVIDE:	
				this.divide(firstNumber, secondNumber);
				break;
			case EXPONENTIATION:	
				this.exponentiation(firstNumber, secondNumber);
				break;
		}
    }


    /**
     * Adding of the arguments
     * @param firstNumber First number for adding
     * @param secondNumber Second number for adding
     */
    public void add(int firstNumber, int secondNumber) {
        this.result = firstNumber + secondNumber;
    }


    /**
     * Subtracting of the arguments
     * @param firstNumber First number for subtracting
     * @param secondNumber Second number for subtracting
     */
    public void subtract(int firstNumber, int secondNumber) {
        this.result = firstNumber - secondNumber;
    }


    /**
     * Multiplication of the arguments
     * @param firstNumber First number for multiplication
     * @param secondNumber Second number for multiplication
     */
    public void multiply(int firstNumber, int secondNumber) {
        this.result = firstNumber * secondNumber;
    }


    /**
     * Division of the arguments
     * @param firstNumber First number for division
     * @param secondNumber Second number for division
     */
    public void divide(int firstNumber, int secondNumber) {
        this.result = firstNumber / secondNumber;
    }


    /**
     * Exponentiation of the arguments
     * @param firstNumber First number of x to the power y
     * @param secondNumber Second number of x to the power y
     */
    public void exponentiation(int firstNumber, int secondNumber) {
        this.result = (int)Math.pow(firstNumber, secondNumber);
    }

}