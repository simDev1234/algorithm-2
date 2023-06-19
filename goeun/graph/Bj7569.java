package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj7569 {

    // 보관 하루가 지나면, 익은 토마토의 인접한 곳의 익지 않은 토마토가 익는다.
    // 현재 상자의 상하좌우 또는 위아래 상자

    static class Tomato{
        int h;
        int n;
        int m;

        public Tomato(int h, int n, int m) {
            this.h = h;
            this.n = n;
            this.m = m;
        }
    }

    static int M, N, H;
    static Queue<Tomato> tomatoes;
    static int[][][] rottenDays;
    static int[][] d = {{0, -1, 0},{0, 1, 0},{0, 0, -1},{0, 0, 1},{-1, 0, 0},{1, 0, 0}};

    static int bfs(){

        while (!tomatoes.isEmpty()) {
            Tomato cur = tomatoes.poll();

            for (int[] next : d) {
                int nH = cur.h + next[0];
                int nN = cur.n + next[1];
                int nM = cur.m + next[2];
                if (nH < 0 || nM < 0 || nN < 0 || nH >= H || nM >= M || nN >= N) continue;
                if (rottenDays[nH][nN][nM] == 0) {
                    rottenDays[nH][nN][nM] = rottenDays[cur.h][cur.n][cur.m] + 1; // 전의 토마토에서 익은 날짜 + 1
                    tomatoes.add(new Tomato(nH,nN,nM));
                }
            }
        }

        int result = Integer.MIN_VALUE;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (rottenDays[i][j][k] == 0) return -1;
                    result = Math.max(result, rottenDays[i][j][k]);
                }
            }
        }

        return result == 1 ? 0 : result -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로칸
        N = Integer.parseInt(st.nextToken()); // 세로칸
        H = Integer.parseInt(st.nextToken()); // 상자수
        tomatoes = new LinkedList<>();
        rottenDays = new int[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    rottenDays[i][j][k] = Integer.parseInt(st.nextToken()); // 0, -1, 1
                    if (rottenDays[i][j][k] == 1) {
                        tomatoes.add(new Tomato(i, j, k));
                    }
                }
            }
        }

        System.out.println(bfs());

    }

}
