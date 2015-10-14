/**
 * Class for run calculator. Set input parameters by running arguments.
 * Needs 3 parameters - first integer number, mathematical operation and second integer number.
 */
public class ArgRunner {

	public static void main(String[] args) {
		System.out.println("Calculate...");
		
		if (args.length != 3) {
			System.out.println("Please, use three parameters. Example: 5 * -7");
		} else if (!"+".equals(args[1]) && !"-".equals(args[1]) && !"x".equals(args[1]) && 
					!"/".equals(args[1]) && !"e".equals(args[1])) {
			System.out.println("Please, use correct mathematical operation: (+) (-) (x) (/) (e - exponentiation)");
		} else {
			try {
				int first = Integer.valueOf(args[0]);
				int second = Integer.valueOf(args[2]);
				
				Calculator calc = new Calculator();
				// Calculation
				switch (args[1]) {
					case "+":	calc.add(first, second);
								break;
					case "-":	calc.subtract(first, second);
								break;
					case "x":	calc.multiply(first, second);
								break;
					case "/":	try {
									calc.divide(first, second);
								} catch (ArithmeticException ae) {
									System.out.println("Problem in dividing! Result is default.");
								}	
								break;
					case "e":	calc.exponentiation(first, second);
								break;
				}
	
				System.out.println("Result: " + calc.getResult());
				System.out.println();
			} catch (Exception e) {
				System.out.println("Please, use correct integer numbers.");
			};
		}
	}
	
}
