package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bj11724 {

    static boolean[] visited;

    static int solution(int v, int e, List<List<Integer>> edges) {

        int count = 0;
        visited = new boolean[v + 1];

        for (int i = 1; i <= v; i++) {
            if (visited[i] == false) {
                dfs(i, edges);
                count++;
            }
        }

        return count;
    }

    static void dfs(int cur, List<List<Integer>> edges){

        visited[cur] = true;

        for (int i = 0; i < edges.get(cur).size(); i++) {
            int next = edges.get(cur).get(i);
            if (visited[next] == false){
                dfs(next, edges);
            }
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < v + 1; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            edges.get(s).add(t);
            edges.get(t).add(s);
        }

        System.out.println(solution(v, e, edges));

    }
}
