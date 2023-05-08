package test.test1.sol2;

import java.util.ArrayList;
import java.util.List;

class Solution{

    // 연산 수식 + - *
    // 미션 : 수식에 포함된 연산자의 우선순위를 재정의해 가장 큰 수를 제출하라
    // 단, 연산자의 우선순위를 새로 정의할 때, 같은 순위의 연산자는 없어야 한다.
    // 만약, 결과가 음수라면 절댓값으로 변환해 제출한다.
    static String[] priority = new String[3];
    static boolean[] visited = new boolean[3];
    static long answer = 0;

    public long solution(String expression) {
        // 1. 조합으로 연산자의 우선순위를 찝어준다. (총 6가지) - 0 = +, 1 = -, 2 = *
        // 2. 우선순위에 따라 연산을 해준다.
        String[] opt = new String[]{"+", "-", "*"};
        permutation(0, opt, expression);
        return answer;
    }

    private void permutation(int depth, String[] opt, String expression) {

        if (depth == 3) {
            calculate(expression);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (!visited[i]){
                visited[i] = true;
                priority[depth] = opt[i];
                permutation(depth + 1, opt, expression);
                visited[i] = false;
            }
        }

    }

    private void calculate(String expression) {

        List<Long> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                // 연산자
                operators.add(String.valueOf(c));
            } else {
                // 숫자
                tmp.append(c);
                if (i == expression.length() - 1) {
                    numbers.add(Long.parseLong(tmp.toString()));
                    tmp = new StringBuilder();
                } else {
                    char next = expression.charAt(i + 1);
                    if (!(next >= '0' && next <= '9')) {
                        numbers.add(Long.parseLong(tmp.toString()));
                        tmp = new StringBuilder();
                    }
                }
            }
        }

        for (int j = 0; j < 3; j++) {
            while (!numbers.isEmpty()) {
                int idx = operators.indexOf(priority[j]);
                if (idx == -1) break;
                else {
                    switch(priority[j]) {
                        case "+" : numbers.add(idx, numbers.get(idx) + numbers.get(idx + 1)); break;
                        case "-" : numbers.add(idx, numbers.get(idx) - numbers.get(idx + 1)); break;
                        case "*" : numbers.add(idx, numbers.get(idx) * numbers.get(idx + 1)); break;
                    }
                    numbers.remove(idx + 1);
                    numbers.remove(idx + 1);
                    operators.remove(idx);
                }
                answer = Math.max(answer, Math.abs(numbers.get(0)));
            }
        }
    }

}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution("100-200*300-500+20");
    }
}
