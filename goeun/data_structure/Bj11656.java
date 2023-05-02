package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class Bj11656 {

    static Set<String> solution(String s){

        Set<String> answer = new TreeSet<>();

        for (int i = 0; i < s.length(); i++) {
            answer.add(s.substring(i, s.length()));
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        Set<String> answer = solution(input);

        for (String s : answer) {
            System.out.println(s);
        }

    }

}
