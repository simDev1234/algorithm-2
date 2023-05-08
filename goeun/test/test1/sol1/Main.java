package test.test1.sol1;

class Solution{

    public String solution(int[] numbers, String hand) {

        int[] left = new int[]{4, 1};
        int[] right = new int[]{4, 3};
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numbers.length; i++) {

            int cur = numbers[i];

            if (cur == 1 || cur == 4 || cur == 7) {
                left = new int[]{cur / 3 + 1, 1};
                sb.append("L");
            } else if (cur == 3 || cur == 6 || cur == 9) {
                right = new int[]{cur / 3, 3};
                sb.append("R");
            } else {
                // 더 가까운 손 (만약 둘 다 거리가 같다면 x손 잡이)
                int[] destination = new int[]{getColOfMiddle(cur), 2};
                int leftDistance = getDistance(left, destination);
                int rightDistance = getDistance(right, destination);
                if (leftDistance < rightDistance) {
                    left = destination;
                    sb.append("L");
                } else if (leftDistance == rightDistance) {
                    if (hand.equals("right")) {
                        right = destination;
                        sb.append("R");
                    } else {
                        left = destination;
                        sb.append("L");
                    }
                } else {
                    right = destination;
                    sb.append("R");
                }
            }
        }

        return sb.toString();
    }

    private int getColOfMiddle(int cur) {
        if (cur == 2) return 1;
        else if (cur == 5) return 2;
        else if (cur == 8) return 3;
        else return 4;
    }

    private int getDistance(int[] cur, int[] destination){
        return Math.abs(destination[0] - cur[0]) + Math.abs(destination[1] - cur[1]);
    }

}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left"));
    }
}
