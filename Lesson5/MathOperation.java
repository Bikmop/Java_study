/**
 * Allowable mathematical operations
 */
public enum MathOperation {
	ADD,
	SUBTRACT,
	MULTIPLY,
	DIVIDE,
	EXPONENTIATION;
	
	/**
	 * Return mathematical operation by char symbol
	 * @param symbol Char symbol of operation
	 * @return Corresponding mathematical operation
	 */
    public static MathOperation getOperationBySymbol(char symbol) {
        switch (symbol) {
            case '+': return ADD;
            case '-': return SUBTRACT;
            case '*': return MULTIPLY;
            case '/': return DIVIDE;
            case '^': return EXPONENTIATION;
            default: throw new UnsupportedOperationException(String.valueOf(symbol));
        }
    }
}