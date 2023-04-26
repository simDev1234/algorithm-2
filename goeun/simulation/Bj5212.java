package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj5212 {

    static int[][] dir = {{0, 1},{0, -1},{1, 0},{-1, 0}};
    static int R;
    static int C;

    public static void solution(String[][] map) {

        boolean[][] check = new boolean[R][C];

        int landStartR = Integer.MAX_VALUE;
        int landStartC = Integer.MAX_VALUE;
        int landEndR = -1;
        int landEndC = -1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j].equals("X") && !isSinkable(map, i, j)) {
                    check[i][j] = true;
                    landStartR = Math.min(landStartR, i);
                    landEndR = Math.max(landEndR, i);
                    landStartC = Math.min(landStartC, j);
                    landEndC = Math.max(landEndC, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();


        for (int i = landStartR; i <= landEndR; i++) {
            for (int j = landStartC; j <= landEndC; j++) {
                if (check[i][j] == true) {
                    sb.append("X");
                } else {
                    sb.append(".");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean isSinkable(String[][] map, int r, int c) {
        int seaSide = 0;
        for (int[] d : dir) {
            int sR = r + d[0];
            int sC = c + d[1];
            int currentSeaCnt = 0;
            while (sR >= 0 && sC >= 0 && sR < R && sC < C) {
                if (map[sR][sC].equals(".")) {
                    currentSeaCnt++;
                    if (currentSeaCnt >= 3) return true;
                }
                else break;
                sR += d[0];
                sC += d[1];
            }

            if (currentSeaCnt > 0) seaSide++;
        }
        return seaSide >= 3;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        String[][] map = new String[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = String.valueOf(input.charAt(j));
            }
        }

        solution(map);

    }

}
