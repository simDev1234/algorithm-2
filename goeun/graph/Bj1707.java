package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj1707 {

    static String solution(int v, int e, int[][] arr) {

        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            edges.get(arr[i][0]).add(arr[i][1]);
            edges.get(arr[i][1]).add(arr[i][0]);
        }

        int[] check = new int[v + 1];

        // 정점의 이웃들을 다른 색으로 색칠하기 > black - 1 또는 white - 2로 표시
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{arr[0][0], 1});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < edges.get(cur[0]).size(); i++) {
                int next = edges.get(cur[0]).get(i);
                if (check[next] == check[cur[0]]) return "NO";
                if (check[next] == 0) {
                    int color = cur[1] % 2 + 1;
                    check[next] = color;
                    q.add(new int[]{next, color});
                }
            }
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (k --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int[][] edges = new int[e][2];

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                edges[i] = new int[]{s, t};
                sb.append(solution(v, e, edges));
            }
        }

        System.out.println(sb.toString());
    }

}
