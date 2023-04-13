import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorFormTest {
    static CalculatorForm calc = new CalculatorForm();

    private double evalInput(String input) {
        double result = Double.NaN;
        char [] in_array = input.toCharArray();
        try {
            for (char c : in_array) {
                if (c == ' ') // Skip space
                    continue;

                calc.testClick(Character.toString(c));
            }
            result = calc.getResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private void clearAll() {
        try {
            calc.testClick("CLEAR");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("Adding and subtracting numbers")
    public void testAddSub() {
        clearAll();
        int n1 = ThreadLocalRandom.current().nextInt(0, 100000);
        int n2 = ThreadLocalRandom.current().nextInt(0, 100000);
        int n3 = ThreadLocalRandom.current().nextInt(0, 100000);
        int ans = n1 + n2 - n3;
        String evalStr = String.format("%d+%d-%d=", n1, n2, n3);
        System.out.println("Testing " + evalStr);
        double result = evalInput(evalStr);
        assertEquals(ans, result, 0);
    }

    @ Test
    @DisplayName("Multiplication")
    public void testMultiply() {
        clearAll();
        int n1 = ThreadLocalRandom.current().nextInt(0, 1000);
        int n2 = ThreadLocalRandom.current().nextInt(0, 1000);
        int n3 = ThreadLocalRandom.current().nextInt(0, 1000);
        int ans = n1 * n2 * n3;
        String evalStr = String.format("%d*%d*%d=", n1, n2, n3);
        System.out.println("Testing " + evalStr);

        double result = evalInput(evalStr);
        assertEquals(ans, result, 0);
    }

    @ Test
    @DisplayName("Division")
    public void testDivision() {
        clearAll();
        double result = evalInput("100/2/5=");
        assertEquals(10, result, 0);
    }

    @ Test
    @DisplayName("Floating points")
    public void testFloatingPoint() {
        double result = 0;
        clearAll();
        result = evalInput("0.6*0.7*0.8*0.9/2=");
        assertEquals(0.1512, result, 0);
    }

}