package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Bj12764 {

    static class Room implements Comparable<Room>{
        int no;
        int endTime;

        public Room(int no, int endTime) {
            this.no = no;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Room o) {
            return Integer.compare(this.endTime, o.endTime);
        }
    }

    static int[] room = new int[100001];
    static int cnt = 0;

    static void solution(int N, int[][] arr) {

        // 시작 시간을 기준으로 오름차순 정렬하고, 시작 시간이 같으면 끝 시간을 기준으로 오름차순 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] x, int[] y) {
                if (x[0] == y[0]) {
                    return Integer.compare(x[1], y[1]);
                }
                return Integer.compare(x[0], y[0]);
            }
        });

        PriorityQueue<Room> activeRooms = new PriorityQueue<>(); // 끝나는 시간 기준 오름차순
        PriorityQueue<Integer> nonactiveRooms = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {

            // 새로운 사람의 시작 시간이 먼저 번의 사람의 끝나는 시간 보다 크면 빼준다.
            while (!activeRooms.isEmpty() && activeRooms.peek().endTime < arr[i][0]) {
                nonactiveRooms.add(activeRooms.poll().no);
            }

            if (!nonactiveRooms.isEmpty()) {
                Integer no = nonactiveRooms.peek();
                room[no] += 1;
                activeRooms.add(new Room(no, arr[i][1]));
            } else {
                cnt++;
                room[cnt] += 1;
                activeRooms.add(new Room(cnt, arr[i][1]));
            }

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        solution(N, arr);
        System.out.println(cnt);
        for (int i = 1; i <= cnt; i++) {
            System.out.print(room[i] + " ");
        }

    }

}
