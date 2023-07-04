package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj5212_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        int[][] dir = {{0, 1},{0, -1},{-1, 0},{1, 0}};

        boolean[][] isLand = new boolean[R][C];
        int startI = R - 1, startJ = C - 1, endI = 0, endJ = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.') {
                    continue;
                }
                int cnt = 0;
                // 상하좌우
                for (int[] d : dir) {
                    int nearX = i + d[0];
                    int nearY = j + d[1];
                    if (nearX < 0 || nearY < 0 || nearX >= R || nearY >= C) continue;
                    if (map[nearX][nearY] == 'X') cnt++;
                }
                if (cnt >= 2) {
                    isLand[i][j] = true;
                    startI = Math.min(startI, i);
                    startJ = Math.min(startJ, j);
                    endI = Math.max(endI, i);
                    endJ = Math.max(endJ, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = startI; i <= endI; i++) {
            for (int j = startJ; j <= endJ; j++) {
                sb.append(isLand[i][j] == true ? 'X' : '.');
            }
            if (sb.length() > 0) sb.append("\n");
        }
        System.out.println(sb);

    }

}
