import java.lang.Math;

/**
 * Class implements calculator
 */  
public class Calculator {
	/** Calculation result */
	private int result;	
	
	
	/**
	 * Get the result.
	 * @return calculation result.
	 */
	public int getResult() {
		return this.result;
	}
	
	
	/**
	 * Clear calculation result.
	 */
	public void clearResult() {
		this.result = 0;
	}

	
	/**
	 * Adding of the arguments.
	 * @first, @second - Arguments for adding.
	 */
	public void add(int first, int second) {
		this.result = first + second;
	}

	
	/**
	 * Subtracting of the arguments.
	 * @first, @second - Arguments for subtracting.
	 */
	public void subtract(int first, int second) {
		this.result = first - second;
	}
	
	
	/**
	 * Multiplication of the arguments.
	 * @first, @second - Arguments for multiplication.
	 */
	public void multiply(int first, int second) {
		this.result = first * second;
	}
	
	
	/**
	 * Division of the arguments.
	 * @first, @second - Arguments for division.
	 */
	public void divide(int first, int second) {
		this.result = first / second;
	}
	
	
	/**
	 * Exponentiation of the arguments.
	 * @first, @second - x to the power y.
	 */
	public void exponentiation(int first, int second) {
		this.result = (int)Math.pow(first, second);
	}
	
	
}