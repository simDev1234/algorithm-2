package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj13305 {
    
    static long solution(int N, int[] direction, int[] price) {

        long answer = 0;
        long minCost = price[0]; // 이전까지의 주유소 중 가장 저렴했던 비용

        for (int i = 0; i < N - 1; i++) {
            if (price[i] < minCost) {
                minCost = price[i];
            }
            answer += (minCost * direction[i + 1]);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] direction = new int[N];
        for (int i = 1; i < N; i++) {
            direction[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] price = new int[N];
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(N, direction, price));
        
    }

}
