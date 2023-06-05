package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj10703 {

    static class Star{
        int r;
        int c;

        public Star(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        int[] bottomStars = new int[C];
        int[] landTops = new int[C];
        Arrays.fill(bottomStars, -1);
        Arrays.fill(landTops, R);
        List<Star> stars = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = input.charAt(j);
                if (c == 'X') {
                    stars.add(new Star(i, j));
                    bottomStars[j] = Math.max(bottomStars[j], i);
                } else if (c == '#') {
                    landTops[j] = Math.min(landTops[j], i);
                }
                map[i][j] = c == 'X' ? '.' : c;
            }
        }

        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < C; i++) {
            if (bottomStars[i] != -1) {
                minDistance = Math.min(minDistance, landTops[i] - bottomStars[i] - 1);
            }
        }

        for (Star star : stars) {
            map[star.r + minDistance][star.c] = 'X';
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

}
