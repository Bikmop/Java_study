import java.util.Scanner;


/**
 * Class for run calculator. Support input of parameters by user.
 */
public class InteractRunner {

	public static void main(String[] args) {
		
		try (Scanner reader = new Scanner(System.in)) {
			Calculator calc = new Calculator();
			String exit = "no";
			boolean reuse = false;
			boolean firstTime = true;
			
			while (!"yes".equals(exit.toLowerCase()) && !"y".equals(exit.toLowerCase())) {
					
				// Choose mathematical operation
				String operation = "";
				while (!"+".equals(operation) && !"-".equals(operation) && !"*".equals(operation) && 
						!"/".equals(operation) && !"^".equals(operation)) {
					System.out.print("Choose mathematical operation (+) (-) (*) (/) (^): ");
					operation = reader.next();
				}
					
				// Get arguments
				int first = 0;
				int second = 0;
				if (!firstTime) {	// Previous result using can be only after the first calculation
					System.out.print("Use previous result as first number? (Y/N): ");
					String clearResult = reader.next();
					if (!"n".equals(clearResult.toLowerCase())) {
						reuse = true;
					} else {
						calc.clearResult();
						reuse = false;
					}
				} else {
					firstTime = false;
				}
				
				boolean correctNumbers = false;
				while (!correctNumbers) {
					try {
						if (!reuse) {
							System.out.print("Input first integer number: ");
							first = Integer.valueOf(reader.next());
						} else {
							first = calc.getResult();
						}
						System.out.print("Input second integer number: ");
						second = Integer.valueOf(reader.next());
						correctNumbers = true;
					} catch (Exception e) {
						System.out.println("Please, input correct integer number!");
						System.out.println();
					};
				}	
					
				// Calculation
				switch (operation) {
					case "+":	calc.add(first, second);
								break;
					case "-":	calc.subtract(first, second);
								break;
					case "*":	calc.multiply(first, second);
								break;
					case "/":	try {
									calc.divide(first, second);
								} catch (ArithmeticException ae) {
									System.out.println("Problem in dividing! Result did not change.");
								}	
								break;
					case "^":	calc.exponentiation(first, second);
								break;
				}
	
				System.out.println("Result: " + calc.getResult());
				System.out.println();
				
				System.out.print("Exit? (Yes/No): ");
				exit = reader.next();
				System.out.println();

			}
		}
	}
	
}
