package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj1327 {

    static class Node {
        String s;
        int cnt;

        public Node(String s, int cnt) {
            this.s = s;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        String origin = arrayToString(arr); // 54321
        String ordered = arrayToString(Arrays.stream(arr).sorted().toArray()); //12345

        Queue<Node> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.add(new Node(origin, 0));

        int answer = Integer.MAX_VALUE;

        while (!q.isEmpty()) {

            Node cur = q.poll();

            if (cur.s.equals(ordered)) {
                answer = Math.min(answer, cur.cnt);
                break;
            }

            for (int i = 0; i <= N - K; i++) {
                String fliped = flip(cur.s, i, K);
                if (visited.add(fliped)) {
                    q.add(new Node(fliped, cur.cnt + 1));
                }
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }

    static String flip(String s, int start, int k) {

        StringBuilder sb = new StringBuilder();
        for (int i = start + k - 1; i >= start; i--) {
            sb.append(s.charAt(i));
        }
        String sub = sb.toString();

        sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i < start || i >= start + k) {
                sb.append(s.charAt(i));
            }
            if (i == start) {
                sb.append(sub);
            }
        }

        return sb.toString();
    }

    static String arrayToString(int[] arr){
        StringBuilder sb = new StringBuilder();
        for (int n : arr) {
            sb.append(n);
        }
        return sb.toString();
    }

}
