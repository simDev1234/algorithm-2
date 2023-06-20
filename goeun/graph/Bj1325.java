package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Bj1325 {

    static int N, M;
    static ArrayList<ArrayList<Integer>> adjList;
    static boolean[] visited;
    static int[] hackingCnt; // 해당 컴퓨터로 올 수 있는 해킹 경로

    static void dfs(int cur){

        visited[cur] = true;

        for (int next : adjList.get(cur)) {
            if (!visited[next]) {
                hackingCnt[next]++;
                dfs(next);
            }
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList<>();
        hackingCnt = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList.get(from).add(to);
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            dfs(i);
        }

        int maxCnt = Arrays.stream(hackingCnt).max().getAsInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            if (hackingCnt[i] == maxCnt) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);

    }

}
