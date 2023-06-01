package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Bj4358 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> kinds = new TreeMap<String, Integer>();
        long count = 0;

        while (true) {

            String input = br.readLine();

            if (input == null || input.length() == 0) break;

            kinds.put(input, kinds.getOrDefault(input, 0) + 1);
            count++;

        }

        StringBuilder sb = new StringBuilder();
        for (String kind : kinds.keySet()) {
            double percent = (double)kinds.get(kind) / count * 100;
            sb.append(kind).append(" ").append(String.format("%.4f", percent)).append("\n");
        }
        System.out.println(sb);

    }

}
