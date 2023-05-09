import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bg2529 {

    static int K;
    static int[] arr;
    static String max = "0";
    static String min = "9876543210";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        arr = new int[K];
        StringTokenizer st = new StringTokenizer(br.readLine());

        // < == 0
        // > == 1
        for (int i = 0; i < K; i++) {
            if (st.nextToken().equals(">")) arr[i] = 1;
        }

        boolean[] check = new boolean[10];

        for (int i = 0; i <= 9; i++) {
            check[i] = true;
            dfs(0, String.valueOf(i), check);
            check[i] = false;
        }

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int depth, String num, boolean[] check) {

        if (depth == K) {
            max = String.valueOf(Math.max(Long.parseLong(num), Long.parseLong(max)));
            min = String.valueOf(Math.min(Long.parseLong(num), Long.parseLong(min)));
            if (min.length() != K+1) {
                min = "0" + min;
            }
            return;
        }

        for (int i = 0; i < 10; i++) {

            if (check[i]) continue;

            if (arr[depth] == 1) {
                if (num.charAt(depth) - '0' > i) {
                    check[i] = true;
                    dfs(depth + 1, num + i, check);
                    check[i] = false;
                }
            } else {
                if (num.charAt(depth) - '0' < i) {
                    check[i] = true;
                    dfs(depth + 1, num + i, check);
                    check[i] = false;
                }
            }
        }
    }
}
