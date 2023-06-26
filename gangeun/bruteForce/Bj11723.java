import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bj11723 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int bitset = 0;
        StringBuilder sb = new StringBuilder();

        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String type = st.nextToken();

            int num = 0;
            if (st.hasMoreTokens()) {
                num = Integer.parseInt(st.nextToken());
            }

            switch (type) {
                // 기존 bitset과 or연산을 통해 num번째 원소를 추가해준다.
                case "add":
                    bitset |= (1 << (num));
                    break;

                // not연산을 통해 반전시킨값을 and연산을 통해 없애준다.
                case "remove":
                    bitset = bitset & ~(1 << num);
                    break;

                // 해당 인덱스의 값이 0이면 1이면 존재 0 이면 존재 x
                case "check":
                    sb.append((bitset & 1 << num) != 0 ? "1" : "0").append("\n");
                    break;

                // xor연산을 통해 반전시커줌
                case "toggle":
                    bitset ^= (1 << num);
                    break;

                // 전부 1로 바꿔줌
                case "all":
                    bitset |= (~0);
                    break;

                // 전부 0으로 바꿔줌;
                case "empty":
                    bitset &= 0;
                    break;
            }
        }

        System.out.println(sb);
    }
}
