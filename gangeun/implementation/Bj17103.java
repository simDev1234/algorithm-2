import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bj17103 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        boolean[] arr = new boolean[1_000_000];
        arr[0] = arr[1] = true;

        for (int i = 2; i <= Math.sqrt(1_000_000); i++) {
            if (arr[i]) continue;

            for (int j = i * i; j < arr.length; j = j+i) {
                arr[j] = true;
            }
        }

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int count = 0;

            for (int j = 2; j <= N/2; j++) {
                if (!arr[j] && !arr[N - j]) {
                    count++;
                }
            }
            sb.append(count).append("\n");
        }

        System.out.println(sb);
    }
}
