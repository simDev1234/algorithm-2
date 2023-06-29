package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bj19598 {

    static class Meeting implements Comparable<Meeting>{
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if (this.start == o.start) return Integer.compare(this.end, o.end);
            return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 기존 회의 시간 -> 시작 시간을 기준, 같으면 종료 시간을 기준을 정렬
        // * 끝나는 시간 순으로 하게 될 경우, (1, 2), (1, 4), (4, 5), (2, 6) 과 같이 순서대로 이어지는 회의를 잡을 수 없다.
        PriorityQueue<Meeting> meetings = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.add(new Meeting(start, end));
        }

        PriorityQueue<Integer> schedules = new PriorityQueue<>();
        int maxSize = 0;
        while (!meetings.isEmpty()) {

            // 현재 회의에 대해서
            Meeting curMeeting = meetings.poll();

            while (!schedules.isEmpty()) {
                // 각 회의실의 종료 시간 후에 시작된다면 이어서 한다.
                if (schedules.peek() <= curMeeting.start) {
                    schedules.poll();
                    schedules.add(curMeeting.end);
                    break;
                }
                // 아니면 회의를 새로 추가한다.
                schedules.add(curMeeting.end);
            }
            maxSize = Math.max(maxSize, schedules.size());
        }
        System.out.println(maxSize);

    }

}
