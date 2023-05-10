import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Bg1182 {

    private static int N;
    private static int S;

    private static int count;

    private static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int sum = 0;

        for (int i = 1; i < (1 << N); i++) {
            sum = 0;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    sum += arr[j];
                }
            }
            if(sum == S) count++;
        }

//        dfs(0, 0);
        System.out.println(count);
    }

    private static void dfs(int i, int sum) {

        if (i == N) {
            return;
        }

        if (arr[i] + sum == S) {
            count++;
        }

        dfs(i + 1, sum);
        dfs(i + 1, sum + arr[i]);

    }

}