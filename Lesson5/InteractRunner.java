/**
 * Class for run calculator. Support input of parameters by user.
 */
public class InteractRunner {

	/** Instance of Calculator for calculations */
	private Calculator calc;
	
	/** Arguments for calculation */
	private int[] arguments = new int[2];
	
	/** Mathematical operation for calculation */
	private MathOperation operation;
	
	/**	
	 * In first calculation always asks two arguments (int numbers) from console. 
	 * Next times previous result can be used as first argument.
	 * */
	private boolean isFirstCalculation = true;

	
	/**
	 * Set calc field in constructor
	 * @param calc
	 */
	public InteractRunner(Calculator calc) {
		this.calc = calc;
	}
	
	
	/**
	 * Set two arguments for calculation
	 */
	private void setArguments() {
		if (isUseResult()) {
			this.arguments[0] = this.calc.getResult();
		} else {
			this.arguments[0] = ConsoleWorker.askCorrectIntArgument("first");
		}
	    	
		this.arguments[1] = ConsoleWorker.askCorrectIntArgument("second");
	}
	
	
	/**
	 * Get if use result of previous calculation as first argument, depending of 
	 * calculation number (this.isFirstCalculation) and user answer.
     * @return True if use.
	 */
	private boolean isUseResult() {
		boolean reuse = false;
		if (!this.isFirstCalculation) {
			reuse = ConsoleWorker.isUsePrevResult();
		} else {
			this.isFirstCalculation = false;
		}
		return reuse;
	}
	
	
	/**
	 * Perform calculation using current arguments.
	 * Any ArithmeticException is caught and result will be cleared.
	 */
	private void calcCatchArithmeticException() {
		try {
			calc.calculate(this.arguments[0], this.operation, this.arguments[1]);
		} catch (ArithmeticException ae) {
			ConsoleWorker.showCalcException(ae.getMessage());
			calc.clearResult();
		}
	}
	
	
	/**
	 * Cyclic calculations till user exit 
	 */
	public void calculationsTillExit() {
		boolean exit = false;
		while (!exit) {
			this.operation = ConsoleWorker.askMathematicalOperation();
			this.setArguments();
			this.calcCatchArithmeticException();
			ConsoleWorker.showResult(calc);
			exit = ConsoleWorker.isExit();	
		}
	}
	
	
	
	public static void main(String[] args) {
		
		InteractRunner interactRunner = new InteractRunner(new Calculator());
		interactRunner.calculationsTillExit();

	}
	
}