import java.util.Scanner;

/**
 * Work with console - Input/Output
 */
public class ConsoleWorker {

	/** Scanner to read string from console */
	private static Scanner reader = new Scanner(System.in);
	
	
	/**
	 * Write message to console
	 * @param message Output message
	 */
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    
    /**
     * Read string from console
     * @return Input string from user
     */
    public static String readString() {
        return reader.next();
    }
    
    
    /**
     * Get mathematical operation from console
     * @return Mathematical operation
     */
    public static MathOperation askMathematicalOperation() {
		MathOperation operation = null;
		boolean correct = false;
		while (!correct) {
			ConsoleWorker.writeMessage("Choose mathematical operation (+  -  *  /  ^): ");
			String symbol = ConsoleWorker.readString();
	    	try {
	    		operation = MathOperation.getOperationBySymbol(symbol.charAt(0));
	    		correct = symbol.length() == 1;		// True if correct symbol and one char input string
	    	} catch (IllegalArgumentException iae) {
	    		ConsoleWorker.writeMessage("Incorrect mathematical operation!");
	    	}
		}
    	return operation;
    }
    
    
    /**
     * Get if use result of previous calculation as first argument
     * @return True for "Y" or "y" input strings and false for any another cases 
     */
    public static boolean isUsePrevResult() {
		ConsoleWorker.writeMessage("Use previous result as first number? (Y/N): ");
		String clearResult = ConsoleWorker.readString();
		boolean reuse = false;
		if ("y".equals(clearResult.toLowerCase())) {
			reuse = true;
		}
    	return reuse;
    }
    
    
    /**
     * Get correct int argument from console
     * @param argumentNumber Example: first
     * @return Integer number
     */
    public static int askCorrectIntArgument(String argumentNumber) {
    	int argument = 0;
    	boolean correctNumber = false;
    	while (!correctNumber) {
			try {
				String message = String.format("Input %s integer number: ", argumentNumber);
				argument = Integer.parseInt(ConsoleWorker.askArgument(message));
				correctNumber = true;
			} catch (NumberFormatException nfe) {
		    	ConsoleWorker.writeMessage("Please, input correct integer number!");
		    	ConsoleWorker.writeMessage("");
		    }
    	}
    	return argument;
    }
    
    
    /**
     * Get one argument from console
     * @param message Asking message
     * @return Input string argument
     */
    private static String askArgument(String message) {
    	ConsoleWorker.writeMessage(message);
    	return ConsoleWorker.readString();
    }
    
    
    /**
     * Show result for calc-object to console
     * @param calc Instance of Calculator class to get result
     */
    public static void showResult(Calculator calc) {
		ConsoleWorker.writeMessage("Result: " + calc.getResult());
		ConsoleWorker.writeMessage("");
    }
    
    /**
     * Show Exception during calculation
     * @param exceptionMessage Message of appeared Exception
     */
    public static void showCalcException(String exceptionMessage) {
		ConsoleWorker.writeMessage("!!! Problems during calculation !!!");
		ConsoleWorker.writeMessage(exceptionMessage);
		ConsoleWorker.writeMessage("");
		ConsoleWorker.writeMessage("Result cleared.");	
    }
    
    
    /**
     * Ask about exit
     * @return True means exit, when get from console "yes" or "y". Another cases will be false result.
     */
    public static boolean isExit() {
    	boolean result = false;
    	ConsoleWorker.writeMessage("Exit? (Yes/No): ");
		String exitString = reader.next();
		ConsoleWorker.writeMessage("");
		if ("yes".equals(exitString.toLowerCase()) || "y".equals(exitString.toLowerCase()))
			result = true;
		return result;
    }
    
}