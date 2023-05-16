package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj4963 {

    static int islandCnt;
    static int[][] dir = new int[][]{{1, 0},{0, 1}, {-1, 0}, {0, -1},{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};

    static int solution(int w, int h, int[][] map) {
        islandCnt = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 1){
                    dfs(new int[]{i, j}, w, h, map);
                    islandCnt++;
                }
            }
        }

        return islandCnt;
    }

    static void dfs(int[] cur, int w, int h, int[][] map) {

        map[cur[0]][cur[1]] = 0;

        for (int[] d : dir){
            int nH = cur[0] + d[0];
            int nW = cur[1] + d[1];
            if (nH < 0 || nW < 0 || nH >= h || nW >= w) continue;
            if (map[nH][nW] == 1) {
                map[nH][nW] = 0;
                dfs(new int[]{nH, nW}, w, h, map);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(solution(w, h, map)).append("\n");
        }

        System.out.println(sb.toString());

    }
}
