import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj2178 {

    private static int[][] map;
    private static boolean[][] visited;
    private static int N;
    private static int M;

    private static int[] moveX = {-1, 1, 0, 0};
    private static int[] moveY = {0, 0, -1, 1};

    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

       bfs();
        System.out.println(min);
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int count = node.count;

            if (node.x == N-1 && node.y == M-1) {
                min = Math.min(min, node.count+1);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int mx = moveX[i] + node.x;
                int my = moveY[i] + node.y;

                if (mx < 0 || my < 0 || mx >= N || my >= M || map[mx][my] == 0 || visited[mx][my]) {
                    continue;
                }

                queue.add(new Node(mx, my, count + 1));
                visited[mx][my] = true;
            }

        }
    }

    static class Node {
        public int x;
        public int y;

        public int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

}
