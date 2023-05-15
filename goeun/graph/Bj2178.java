package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj2178 {

    static int[][] dir = new int[][]{{0, 1},{1, 0},{0, -1},{-1, 0}};

    static int solution(int n, int m, int[][] arr){

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 1, 1});
        arr[1][1] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == n && cur[1] == m) return cur[2];

            for (int i = 0; i < dir.length; i++) {
                int nX = cur[0] + dir[i][0];
                int nY = cur[1] + dir[i][1];
                if (nX < 0 || nY < 0 || nX > n || nY > m) continue;
                if (arr[nX][nY] == 1) {
                    arr[nX][nY] = 0;
                    q.add(new int[]{nX, nY, cur[2] + 1});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            for (int j = 1; j <= m; j++) {
                arr[i][j] = s.charAt(j - 1) - '0';
            }
        }

        System.out.println(solution(n, m, arr));

    }
}
