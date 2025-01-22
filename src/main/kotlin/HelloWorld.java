// For ASM Analysis
public class HelloWorld {
    private static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello: " + fibonacci(10));
    }
}
