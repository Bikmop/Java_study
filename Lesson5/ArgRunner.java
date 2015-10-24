/**
 * Class for run calculator. Set input parameters by running arguments.
 * Needs 3 parameters - first integer number, mathematical operation and second integer number.
 * Input strings for operation: +, -, x(multiplication), /, e(exponentiation)
 */
public class ArgRunner {

	private Calculator calc = new Calculator();
	private Integer[] arguments = {null, null};
	private MathOperation operation = null;
	
	
	public static void main(String[] args) {
		ArgRunner ar = new ArgRunner();
		ar.showResult(args);
	}	
	
	
	public void showResult(String[] args) {
		try {
			int calcResult = getResult(args);
			printToConsole("Result: " + calcResult);
		} catch (Exception e) {
			printToConsole(e.getMessage());
		}
	}
	
	
	public int getResult(String[] args) {
		if (isCorrectInputArgsQuantity(args)) {
			setCalculationParameters(args);
			calculate();
		} else {
			throw new IllegalArgumentException(incorrectInputArgsQuantityMessage());
		}
		return calc.getResult();
	}

	
	private boolean isCorrectInputArgsQuantity(String[] args) {
		final int CORRECT_ARGS_QUANTITY = 3;
		boolean isCorrectQuantity = true;
		
		if (args.length != CORRECT_ARGS_QUANTITY)
			isCorrectQuantity = false;
			
		return isCorrectQuantity;
	}	
	
	
	private void setCalculationParameters(String[] args) {
		setTwoArguments(args[0], args[2]);
		setOperation(args[1]);
	}	
	
	
	private void setTwoArguments(String firstNumber, String secondNumber) {
		try {
			arguments[0] = Integer.parseInt(firstNumber);
			arguments[1] = Integer.parseInt(secondNumber);
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException(incorrectIntegerNumberOfArgumentMessage());
	    }
	}	
	
	
	private String incorrectIntegerNumberOfArgumentMessage() {
		return "Incorrect integer number!";
	}	
	

	private void setOperation(String operationsString) {
   		try {
   			char operationsSymbol = convertOperationStringToChar(operationsString);
   			operation = getOperationBySymbol(operationsSymbol); 
   		} catch (UnsupportedOperationException uoe) {
   			throw new UnsupportedOperationException(incorrectMathematicalOperationMessage());
   		}
	}	
	
	
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
	
	
	private MathOperation getOperationBySymbol(char operationsSymbol) {
		return MathOperation.getOperationBySymbol(operationsSymbol);
	}
	

	private String incorrectMathematicalOperationMessage() {
		String message = "Incorrect mathematical operation!" + System.lineSeparator() + 
				"Please, use correct mathematical operation: " + System.lineSeparator() +
   				"(+, -, x(multiplication), /, e(exponentiation)";
		return message;
	}	
	
	
	private void calculate() {
		try {
			printToConsole("Calculation...");
			calc.calculate(arguments[0], operation, arguments[1]);
		} catch (ArithmeticException ae) {
			throw new ArithmeticException(arithmeticCalculationProblemMessage(ae.getMessage()));
		}
	}	
	
	
	private void printToConsole(String message) {
		System.out.println(message);
	}	

	
	private String arithmeticCalculationProblemMessage(String exceptionMessage) {
		String message = "!!! Problems during calculation !!!" +
				System.lineSeparator() + exceptionMessage;
		return message;
	}	
	
	
	private String incorrectInputArgsQuantityMessage() {
		String message = "Please, use three parameters." + System.lineSeparator() +
				"Example: -5 / 7" + System.lineSeparator() + 
				"And correct mathematical operation:" + System.lineSeparator() +
				"(+, -, x(multiplication), /, e(exponentiation)";
		return message;
	}
	
}