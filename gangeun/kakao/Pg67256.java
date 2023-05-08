//TODO
// 조건 :
// 1.  1,4,7 은 왼손 사용
// 2.  3,6,9 는 오른손 사용
// 3.  시작 위치는  왼손은 *, 엄지는 #
// 4.  2,5,8,0 은 가까운 손이누름
// 5.  거리가 같을 시 오른손,왼손잡이 구분해서 클릭 ~!

class Pg67256 {
    public String solution(int[] numbers, String hand) {

        String[] indexing = new String[]
                {"31", "00", "01", "02", "10", "11", "12", "20", "21", "22", "30", "32"};

        StringBuilder sb = new StringBuilder();

        int leftPoint = 10;
        int rightPoint = 11;

        for (int number : numbers) {

            if (number == 1 || number == 4 || number == 7) {
                leftPoint = number;
                sb.append("L");
                continue;
            }

            if (number == 3 || number == 6 || number == 9) {
                rightPoint = number;
                sb.append("R");
                continue;
            }

            int leftDistance = Math.abs(indexing[number].charAt(0) - indexing[leftPoint].charAt(0)) + Math.abs(indexing[number].charAt(1) - indexing[leftPoint].charAt(1));
            int rightDistance = Math.abs(indexing[number].charAt(0) - indexing[rightPoint].charAt(0)) + Math.abs(indexing[number].charAt(1) - indexing[rightPoint].charAt(1));



            if (leftDistance < rightDistance) {
                leftPoint = number;
                sb.append("L");
                continue;
            }

            if (rightDistance < leftDistance) {
                rightPoint = number;
                sb.append("R");
                continue;
            }

            if (hand.equals("left")) {
                leftPoint = number;
                sb.append("L");
            } else {
                rightPoint = number;
                sb.append("R");
            }
        }
        return sb.toString();
    }


}
