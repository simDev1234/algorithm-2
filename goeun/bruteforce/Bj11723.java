package bruteforce;

// 집합
// ㄴ 비트마스크 관련 참조 : https://loosie.tistory.com/238

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj11723 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int M = Integer.parseInt(br.readLine());

        long bits = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String order = st.nextToken();

            if (order.equals("empty")) {
                bits = 0;
                continue;
            } else if (order.equals("all")) {
                bits = (1 << 21) - 1;
                continue;
            }

            int num = Integer.parseInt(st.nextToken());

            if (order.equals("add")) {
                bits |= (1 << num - 1);
            } else if (order.equals("check")) {
                sb.append((bits & (1 << num - 1)) == (1 << num - 1) ? 1 : 0).append("\n");
            } else if (order.equals("toggle")) {
                bits ^= (1 << num - 1);
            } else if (order.equals("remove")) {
                bits &= ~(1 << num - 1);
            }
        }

        System.out.println(sb);

    }

}
