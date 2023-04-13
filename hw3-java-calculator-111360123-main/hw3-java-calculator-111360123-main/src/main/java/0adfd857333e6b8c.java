public class CalculatorForm {
  private double accumulator = 0;
  private String buffer = "";

  private enum Operator {
    ADD, SUBTRACT, MULTIPLY, DIVIDE
  }

  private Operator operator = null;

  private double calculate(double opcode, double operand, Operator operator) {
    switch (operator) {
      case ADD:
        return opcode + operand;
      case SUBTRACT:
        return opcode - operand;
      case MULTIPLY:
        return opcode * operand;
      case DIVIDE:
        return opcode / operand;
    }
    throw new IllegalArgumentException("Operator must not be null.");
  }

  public void testClick(String s) {
    // Possible values of s:
    // "CLEAR", 0-9, ., =, +, -, *, /

    // Input is CLEAR
    if (s == "CLEAR") {
      this.accumulator = 0;
      this.buffer = "";
      this.operator = null;
      return;
    }

    // Input is a digit
    if (s.matches("[0-9.]")) {
      this.buffer = this.buffer.concat(s);
      return;
    }

    // Input is a operator
    if (s.matches("[=+\\-*\\/]")) {
      double input = Double.parseDouble(this.buffer);
      this.buffer = "";

      if (operator == null) {
        // Just started or CLEARed
        this.accumulator = input;
      } else {
        this.accumulator = calculate(this.accumulator, input, operator);
      }

      // Set the new operator
      switch (s) {
        case "+":
          this.operator = Operator.ADD;
          break;
        case "-":
          this.operator = Operator.SUBTRACT;
          break;
        case "*":
          this.operator = Operator.MULTIPLY;
          break;
        case "/":
          this.operator = Operator.DIVIDE;
          break;
      }
      return;
    }
  }

  public double getResult() {
    return this.accumulator;
  }
}
