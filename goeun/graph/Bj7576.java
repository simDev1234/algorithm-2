package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj7576 {

    static int [] dx= {-1,1,0,0}, dy= {0,0,1,-1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int [][] pan = new int[n][m];
        Queue<int[]> q = new LinkedList();

        for(int i=0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                // 1 익은 토마토, 0 익지 않은 토마토, -1 토마토 없음
                pan[i][j] = Integer.parseInt(st.nextToken());
                if (pan[i][j] == 1) {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {

            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];

            for(int i = 0;i < 4; i++) {

                int nX = x + dx[i];
                int nY = y + dy[i];

                if(nX < 0 || nY < 0 || nX >= n || nY >= m) continue;

                if(pan[x+dx[i]][y+dy[i]] == 0) {
                    pan[nX][nY] = pan[x][y] + 1;
                    q.add(new int[] {nX, nY});
                }
            }

        }

        int max = 0;
        if (hasZero(pan)) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    max = Math.max(max, pan[i][j]);
                }
            }
            System.out.println(max - 1);
        }
    }

    private static boolean hasZero(int[][] pan) {
        for (int i = 0; i < pan.length; i++) {
            for (int j = 0; j < pan[i].length; j++) {
                if (pan[i][j] == 0) return true;
            }
        }
        return false;
    }

}
