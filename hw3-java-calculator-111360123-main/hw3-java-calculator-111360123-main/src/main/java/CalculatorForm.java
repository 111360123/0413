import java.util.ArrayList;

class CalculatorForm {
  private ArrayList<Character> buffer = new ArrayList<>();
  private double accumulator = 0;

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
    throw new IllegalArgumentException("Unsupported operator: " + operator);
  }

  // Supported operators: +, -, *, /, =, [0-9.], "CLEAR"
  public void testClick(String s) {
    char c = s.charAt(0);

    if (s.matches("[0-9.]")) {
      // Input is a number or decimal point
      this.buffer.add(c);
      return;
    }

    if (s.matches("[+\\-*/=]")) {
      // Input is an operator

      double input = 0; // Input number from buffer
      if (this.buffer.size() > 0) {
        // Convert buffer to number
        StringBuilder bufferString = new StringBuilder();
        for (char ch : this.buffer) {
          bufferString.append(ch);
        }
        input = Double.parseDouble(bufferString.toString());
        this.buffer.clear(); // Clear buffer
      }

      if (this.operator == null) {
        // First input, set accumulator to input
        this.accumulator = input;
      } else {
        // Calculate result with previous operator
        this.accumulator = calculate(this.accumulator, input, this.operator);
      }

      // Set the new operator
      switch (c) {
        case '+':
          this.operator = Operator.ADD;
          break;
        case '-':
          this.operator = Operator.SUBTRACT;
          break;
        case '*':
          this.operator = Operator.MULTIPLY;
          break;
        case '/':
          this.operator = Operator.DIVIDE;
          break;
      }
      return;
    }

    if (s == "CLEAR") {
      // Input is CLEAR
      this.accumulator = 0;
      this.operator = null;
      return;
    }

    // Input is not supported
    throw new IllegalArgumentException("Unsupported character: " + s);
  }

  public double getResult() {
    return this.accumulator;
  }

}