package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Bj14725 {

    static class Food {
        Map<String, Food> children = new HashMap<>();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Food root = new Food();

        // 2 B A
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            Food cur = root;

            for (int j = 0; j < K; j++) {
                String s = st.nextToken();

                if (!cur.children.containsKey(s)) {
                    cur.children.put(s, new Food());
                }
                cur = cur.children.get(s);
            }
        }

        print(root, "");

    }

    static void print(Food root, String bar) {
        Object[] key = root.children.keySet().toArray();
        Arrays.sort(key);

        for (Object s : key) {
            System.out.println(bar+s);
            print(root.children.get(s), bar+"--");
        }
    }

}
