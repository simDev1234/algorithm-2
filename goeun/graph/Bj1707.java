package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj1707 {

    static String solution(int v, int e, List<List<Integer>> edges) {

        if (e == 1) return "NO";

        int[] check = new int[v + 1];

        // 정점의 이웃들을 다른 색으로 색칠하기 > black - 1 또는 white - 2로 표시
        Queue<int[]> q = new LinkedList<>();

        for (int i = 1; i <= v; i++) {
            if (edges.get(i).size() >= 1) {
                q.add(new int[]{i, 1});
                check[i] = 1;
                break;
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < edges.get(cur[0]).size(); i++) {
                int next = edges.get(cur[0]).get(i);
                if (check[next] == 0) {
                    int color = cur[1] % 2 + 1;
                    check[next] = color;
                    q.add(new int[]{next, color});
                } else {
                    if (check[next] == check[cur[0]]) return "NO";
                }
            }
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        List<String> answers = new ArrayList<>();
        while (k --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i <= v; i++) {
                edges.add(new ArrayList<>());
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges.get(s).add(t);
            }

            answers.add(solution(v, e, edges));
        }

        for (String s : answers) {
            System.out.println(s);
        }
    }

}
