package test.test2.sol1;

import java.util.Arrays;

class Solution{

    public int[] solution(int[][] v) {

        int[] answer = new int[2];

        answer[0] = v[0][0] ^ v[1][0] ^ v[2][0];
        answer[1] = v[0][1] ^ v[1][1] ^ v[2][1];

        return answer;
    }

    static int getBitCount(int bits){
        int count = 0;
        String s = Integer.toBinaryString(bits);
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

    static int getPosition(int bits) {
        String binaryString = Integer.toBinaryString(bits);
        for (int i = binaryString.length() - 1; i >= 0; i--) {
            if (binaryString.charAt(i) == '1') {
                return binaryString.length() - i;
            }
        }
        return -1;
    }

}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(new int[][]{{1,1},{2,2},{2,3}})));
    }
}
