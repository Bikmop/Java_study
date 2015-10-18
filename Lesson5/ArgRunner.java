/**
 * Class for run calculator. Set input parameters by running arguments.
 * Needs 3 parameters - first integer number, mathematical operation and second integer number.
 */
public class ArgRunner {

	/** Instance of Calculator for calculation */
	private Calculator calc = new Calculator();
	
	/** Arguments for calculation */
	private int[] arguments = new int[2];
	
	/** Mathematical operation for calculation */
	private MathOperation operation;
	
	
	/**
	 * Check and set arguments of calculation
	 * @param first First string number to convert into integer
	 * @param second Second string number to convert into integer
	 * @return True if string numbers are correct
	 */
	private boolean setArguments(String first, String second) {
		boolean correctNumbers = false;
		try {
			arguments[0] = Integer.parseInt(first);
			arguments[1] = Integer.parseInt(second);
			correctNumbers = true;
		} catch (NumberFormatException nfe) {
			System.out.println("Incorrect integer number!");
	    }
		return correctNumbers;
	}
	
	
	/**
	 * Check and set operation of calculation
	 * @param operationString String operation of calculation
	 * @return True if operationString is correct
	 */
	private boolean setOperation(String operationString) {
		boolean correctOperation = false;
		try {
    		this.operation = MathOperation.getOperationBySymbol(convertOperationStringToChar(operationString));
    		correctOperation = true;
    	} catch (IllegalArgumentException iae) {
    		System.out.println("Incorrect mathematical operation!");
    		System.out.println(
    				"Please, use correct mathematical operation: (+, -, x(multiplication), /, e(exponentiation)");
    	}
		
		return correctOperation;
	}
	
	
	/**
	 * Convert operation from string to char. 
	 * Because of the problem of running arguments "*" and "^".
	 * @param operation String of operation
	 * @return Char symbol of operation
	 */
	private char convertOperationStringToChar(String operation) {
		char symbol = ' ';
		if ("x".equals(operation)) {			// Because of the problem with "*" as running argument
			symbol = '*';
		} else if ("e".equals(operation)) {		// Because of the problem with "^" as running argument
			symbol = '^';
		} else if (operation.length() == 1) {
			symbol = operation.charAt(0);
		}
			
		return symbol;
	}
	
	
	/**
	 * Check input arguments quantity
	 * @param args Input arguments
	 * @return True if quantity of input arguments is three
	 */
	private boolean checkInputArgsQuantity(String[] args) {
		boolean correctQuantity = false;
		if (args.length != 3) {
			System.out.println("Please, use three parameters. Example: -5 / 7");
			System.out.println("And correct mathematical operation: (+, -, x(multiplication), /, e(exponentiation)");
		} else {
			correctQuantity = true;
		}
			
		return correctQuantity;
	}
	
	
	/**
	 * Check and set all arguments for calculation
	 * @param args Input arguments
	 * @return True if all arguments are correct
	 */
	public boolean setCalculationParameters(String[] args) {
		return (this.checkInputArgsQuantity(args) && 
				this.setArguments(args[0], args[2]) &&
				this.setOperation(args[1]));
	}
	
	
	/**
	 * Calculate and show result to console
	 */
	public void showCalculationResult() {
		try {
			System.out.println("Calculate...");
			this.calc.calculate(this.arguments[0], this.operation, this.arguments[1]);
			System.out.println("Result: " + this.calc.getResult());
		} catch (ArithmeticException ae) {
			System.out.println("!!! Problems during calculation !!!");
			System.out.println(ae.getMessage());
		}
	}
	
		
	public static void main(String[] args) {

		ArgRunner argRunner = new ArgRunner();
		if (argRunner.setCalculationParameters(args)) {
			argRunner.showCalculationResult();
		}
		
	}
	
}