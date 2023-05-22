package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Bj19583 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String start = st.nextToken();
        String end = st.nextToken();
        String streamEnd = st.nextToken();

        Map<String, Integer> startCheck = new HashMap<>();
        Map<String, Integer> endCheck = new HashMap<>();

        while (true) {
            String input = br.readLine();

            if (input == null || input.length() == 0) break;

            st = new StringTokenizer(input);
            String exitTime = st.nextToken();
            String name = st.nextToken();

            if (exitTime.compareTo(start) <= 0 ) {
                startCheck.put(name, startCheck.getOrDefault(name, 0) + 1);
            }

            if (exitTime.compareTo(end) >= 0 && exitTime.compareTo(streamEnd) <= 0) {
                endCheck.put(name, endCheck.getOrDefault(name, 0) + 1);
            }

        }

        int answer = 0;
        for (String name : startCheck.keySet()) {
            if (startCheck.getOrDefault(name, 0) > 0 && endCheck.getOrDefault(name, 0) > 0) answer++;
        }
        System.out.println(answer);


    }

}
