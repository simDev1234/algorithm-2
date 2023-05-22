import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj1261 {

    private static int N;
    private static int M;
    private static int[][] map;
    private static boolean[][] visited;
    private static int min = Integer.MAX_VALUE;

    private static int[] moveX = {-1, 1, 0, 0};
    private static int[] moveY = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == '1') map[i][j] = 1;
            }
        }


        dfs();
        System.out.println(min);

    }

    static void dfs() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, 0, 0));
        visited[0][0] = true;
        while (!q.isEmpty()) {
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            int count = node.count;

            if (x == N - 1 && y == M - 1) {
                min = count;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int mx = x + moveX[i];
                int my = y + moveY[i];

                if(mx < 0 || my < 0 || mx >= N || my >= M || visited[mx][my]) continue;

                if (map[mx][my] == 0) {
                    visited[mx][my] = true;
                    q.add(new Node(mx, my, count));
                } else {
                    visited[mx][my] = true;
                    q.add(new Node(mx, my, count + 1));
                }
            }
        }

    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int count;

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return count - o.count;
        }
    }

}
