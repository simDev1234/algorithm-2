package test.test2.sol2;

import java.util.*;

class Solution {

    public String solution(String number, int k) {

        Stack<Character> stack = new Stack<>();

        int removeCnt = 0;
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && removeCnt < k) {
                stack.pop();
                removeCnt++;
            }
            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length() - k; i++) {
            sb.append(stack.get(i));
        }

        return sb.toString();
    }

    static String getRemovedOne(String origin, int idx){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < origin.length(); i++) {
            if (i != idx) {
                sb.append(origin.charAt(i));
            }
        }
        return sb.toString();
    }

}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("99999999999999999999999999", 3));
    }
}
