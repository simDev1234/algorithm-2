package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj10971 {

    static boolean[] visited;
    static int minDistance = Integer.MAX_VALUE;
    static int[][] costs;
    static int n;

    static int solution() {

        visited = new boolean[n];

        // 시작점을 잡는다.
        for (int i = 0; i < n; i++) {
            visited[i] = true;
            dfs(i, i, 1, 0);
            visited[i] = false;
        }

        return minDistance;
    }

    static void dfs(int start, int cur, int visitCnt, int sum){

        if (visitCnt == n) {
            if (costs[cur][start] != 0) {
                minDistance = Math.min(minDistance, sum + costs[cur][start]);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && costs[cur][i] != 0) {
                visited[i] = true;
                dfs(start, i, visitCnt + 1, sum + costs[cur][i]);
                visited[i] = false;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        costs = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());

    }

//    static int[] parents;
//    static PriorityQueue<int[]> edges;

//    // 최소신장트리 - 유니온 파인드
//    // 1. 간선 배열을 정렬한다.
//    // 2. 가중치가 낮은 간선부터 연결하면서, union & find을 반복한다.
//    static int solution2(int n, int[][] costs){
//
//        int answer = 0;
//
//        edges = new PriorityQueue<>((x, y) -> Integer.compare(x[2], y[2]));
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (costs[i][j] == 0) continue;
//                edges.add(new int[]{i, j, costs[i][j]});
//            }
//        }
//
//        parents = new int[n];
//        for (int i = 0; i < n; i++) {
//            parents[i] = i;
//        }
//
//        for (int[] edge : edges) {
//            if (find(edge[0]) != find(edge[1])) {
//                parents[edge[0]] = edge[1];
//                answer += edge[2];
//            }
//        }
//
//        return answer;
//    }
//
//    static int find(int x){
//        if (x == parents[x]) {
//            return x;
//        }
//        return parents[x] = find(parents[x]);
//    }
//
//    static void union(int x, int y) {
//        int xParent = find(x);
//        int yParent = find(y);
//
//        if (xParent != yParent) {
//            parents[xParent] = yParent;
//        }
//    }

}
