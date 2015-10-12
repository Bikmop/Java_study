import java.lang.Math;

public class Calculate {
	
	public static void main(String[] args) {
		System.out.println("Calculate...");
		long first = Long.valueOf(args[0]);
		long second = Long.valueOf(args[1]);
		System.out.println("+: " + sumOfTwoNumbers(first, second));
		System.out.println("-: " + differenceOfTwoNumbers(first, second));
		System.out.println("*: " + productOfTwoNumbers(first, second));
		System.out.println("/: " + quotientOfTwoNumbers(first, second));
		System.out.println("^: " + exponentiationOfTwoNumbers(first, second));
	}
	
	public static int sumOfTwoNumbers(int first, int second) {
		return first + second;
	}

	public static long sumOfTwoNumbers(long first, long second) {
		return first + second;
	}

	public static float sumOfTwoNumbers(float first, float second) {
		return first + second;
	}

	public static double sumOfTwoNumbers(double first, double second) {
		return first + second;
	}

	
	public static int differenceOfTwoNumbers(int first, int second) {
		return first - second;
	}

	public static long differenceOfTwoNumbers(long first, long second) {
		return first - second;
	}

	public static float differenceOfTwoNumbers(float first, float second) {
		return first - second;
	}

	public static double differenceOfTwoNumbers(double first, double second) {
		return first - second;
	}

	
	public static int productOfTwoNumbers(int first, int second) {
		return first * second;
	}

	public static long productOfTwoNumbers(long first, long second) {
		return first * second;
	}

	public static float productOfTwoNumbers(float first, float second) {
		return first * second;
	}

	public static double productOfTwoNumbers(double first, double second) {
		return first * second;
	}

	
	public static int quotientOfTwoNumbers(int first, int second) {
		return first / second;
	}

	public static long quotientOfTwoNumbers(long first, long second) {
		return first / second;
	}

	public static float quotientOfTwoNumbers(float first, float second) {
		return first / second;
	}

	public static double quotientOfTwoNumbers(double first, double second) {
		return first / second;
	}

	
	public static long exponentiationOfTwoNumbers(long first, long second) {
		return (long)Math.pow(first, second);
	}

	public static double exponentiationOfTwoNumbers(double first, double second) {
		return Math.pow(first, second);
	}


	
}