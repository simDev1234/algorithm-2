package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bj13549 {

    static int N, K;
    static int[] time = new int[100001];

    static void bfs(){

        Arrays.fill(time, Integer.MAX_VALUE);

        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        time[N] = 0;

        while (!q.isEmpty()) {

            int cur = q.poll();

            if (cur == K) return;

            for (int i = 0; i < 3; i++) {

                int next;

                if (i == 0) next = cur + 1;
                else if (i == 1) next = cur - 1;
                else next = cur * 2;

                if (next < 0 || next > 100000) continue;

                if (time[next] > time[cur] + 1) {
                    q.add(next);
                    time[next] = next == cur * 2 ? time[cur] : time[cur] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
        } else {
            bfs();
            System.out.println(time[K]);
        }

    }

}
