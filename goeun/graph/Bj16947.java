package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Bj16947 {

    static int N;
    static List<List<Integer>> lines;
    static int[] answer;
    static int minDistance;
    static boolean[] visited;

    static int[] solution(int n, List<List<Integer>> edges) {

        N = n;
        lines = edges;
        int[] answer = new int[n];

        Arrays.fill(answer, -1);

        for (int i = 1; i <= n; i++) {
            minDistance = Integer.MAX_VALUE;
            visited = new boolean[n + 1];

        }

        return answer;
    }



    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> lines = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            lines.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            lines.get(s).add(t);
            lines.get(t).add(s);
        }

        int[] answer = solution(n, lines);

        for (int i = 1; i <= n; i++) {
            System.out.print(answer[i]);
            if (i != answer.length - 1) {
                System.out.print(" ");
            }
        }

    }
}
