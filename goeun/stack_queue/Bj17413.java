package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Bj17413 {

    static String solution(String input){


        StringBuilder result = new StringBuilder();
        Stack<Character> word = new Stack<>();
        boolean isTagOpen = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '<') {
                while (!word.isEmpty()) {
                    result.append(word.pop());
                }
                isTagOpen = true;
                result.append(c);
            } else if (c == '>') {
                isTagOpen = false;
                result.append(c);
            } else {
                if (isTagOpen) {
                    result.append(c);
                } else {
                    if (c == ' ') {
                        while (!word.isEmpty()) {
                            result.append(word.pop());
                        }
                        result.append(" ");
                        continue;
                    }
                    word.push(c);
                }
            }
        }

        while (!word.isEmpty()) {
            result.append(word.pop());
        }

        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(solution(br.readLine()));

    }

}
