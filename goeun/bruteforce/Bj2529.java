package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj2529 {

    static boolean[] visited = new boolean[10];
    static int N;
    static String[] signs;
    static int[] out;
    static long min = Long.MAX_VALUE;
    static long max = Long.MIN_VALUE;

    static void solution(){

        for (int i = 0; i <= 9; i++) {
            visited[i] = true;
            out[0] = i;
            dfs(i, 0);
            visited[i] = false;
        }

        String maxString = String.valueOf(max);
        String minString = String.valueOf(min);

        System.out.println(maxString.length() < N + 1 ? String.format("0%s", maxString) : maxString);
        System.out.println(minString.length() < N + 1 ? String.format("0%s", minString) : minString);

    }

    static void dfs(int cur, int cnt){

        if (cnt == N){
            long tmp = 0;
            for (int i = 0; i <= N; i++) {
                tmp += out[i];
                if (i != N) tmp *= 10;
            }
            min = Math.min(min, tmp);
            max = Math.max(max, tmp);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (visited[i] == false && isMatchingToSign(cur, i, cnt)){
                visited[i] = true;
                out[cnt + 1] = i;
                dfs(i, cnt + 1);
                visited[i] = false;
            }
        }

    }

    static boolean isMatchingToSign(int cur, int to, int cnt){
        if (signs[cnt].equals(">")) {
            return cur > to;
        } else {
            return cur < to;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        signs = new String[N];
        out = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            signs[i] = st.nextToken();
        }

        solution();

    }

}
