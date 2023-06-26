package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj1325 {

    static int N, M, count;
    static int max = Integer.MIN_VALUE;
    static List<Integer>[] adjList;
    static boolean[] visited;
    static int[] hackingCnt;

    static void dfs(int start, int cur){

        for (int next : adjList[cur]) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(start, next);
                hackingCnt[start]++;
            }
        }

    }

    static void bfs(int cur){
        Queue<Integer> q = new LinkedList<>();
        q.add(cur);
        visited[cur] = true;

        while (!q.isEmpty()) {
            int curIdx = q.poll();
            for (int next : adjList[curIdx]) {
                if (!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                    count++;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N + 1];
        hackingCnt = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
        }

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            count = 0;
            bfs(i);
            hackingCnt[i] = count;
            max = Math.max(count, max);
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
