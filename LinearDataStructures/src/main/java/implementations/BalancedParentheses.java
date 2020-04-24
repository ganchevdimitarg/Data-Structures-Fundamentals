package implementations;

import interfaces.Solvable;

public class BalancedParentheses implements Solvable {
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {
        boolean isTrue = false;
        int max = 0;
        String[] arr = this.parentheses.split("");
        int middle = arr.length / 2;

        if (this.parentheses.isEmpty() || this.parentheses.length() % 2 != 0) {
            return false;
        } else {
            for (int i = 0; i < middle; i++) {
                if (arr[i].equals("(") && arr[arr.length - 1 - i].equals(")")){
                    max++;
                }
                if (arr[i].equals("[") && arr[arr.length - 1 - i].equals("]")){
                    max++;
                }
                if (arr[i].equals("{") && arr[arr.length - 1 - i].equals("}")){
                    max++;
                }
            }
        }
        if (max == middle){
            isTrue = true;
        }
        return isTrue;
    }
}
