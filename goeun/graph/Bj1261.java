package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj1261 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] miro = new int[M + 1][N + 1];
        for (int i = 1; i <= M; i++) {
            String input = br.readLine();
            for (int j = 1; j <= N; j++) {
                miro[i][j] = input.charAt(j - 1) - '0';
            }
        }

        PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> x[2] - y[2]);
        q.add(new int[]{1, 1, 0});
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[M + 1][N + 1];
        visited[1][1] = true;

        int answer = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == M && cur[1] == N) {
                answer = cur[2];
                break;
            }

            for (int[] d : dir) {
                int nX = cur[0] + d[0];
                int nY = cur[1] + d[1];

                if (nX < 1 || nY < 1 || nX > M || nY > N) continue;

                if (visited[nX][nY] == false && miro[nX][nY] == 0) {
                    visited[nX][nY] = true;
                    q.add(new int[]{nX, nY, cur[2]});
                }

                if (visited[nX][nY] == false && miro[nX][nY] == 1) {
                    visited[nX][nY] = true;
                    q.add(new int[]{nX, nY, cur[2] + 1});
                }
            }
        }

        System.out.println(answer);
    }

}
