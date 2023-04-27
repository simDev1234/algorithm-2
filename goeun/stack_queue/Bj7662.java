package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Queue;

public class Bj7662 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int K = Integer.parseInt(br.readLine());
            // 이중 우선순위 큐 - 삽입/삭제 - 우선순위 높은것, 낮은것
            TreeMap<Integer, Integer> treeMap = new TreeMap<>();

            for (int j = 0; j < K; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String act = st.nextToken(); // D (삭제) 또는 I (삽입)
                int n = Integer.parseInt(st.nextToken()); // 정수 - 음수면 최솟값 삭제, 양수는 최대값 삭제

                if (act.equals("I")) {
                    treeMap.put(n, treeMap.getOrDefault(n, 0) + 1);
                } else {

                    if (treeMap.isEmpty()) continue;

                    int deleteNKey = n < 0 ? treeMap.firstKey() : treeMap.lastKey();
                    if (treeMap.put(deleteNKey, treeMap.get(deleteNKey) - 1) == 1) {
                        treeMap.remove(deleteNKey);
                    }
                }
            }

            System.out.println(treeMap.isEmpty()? "EMPTY" : treeMap.lastKey() + " " + treeMap.firstKey());
        }

    }

//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        int T = Integer.parseInt(br.readLine());
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < T; i++) {
//            int K = Integer.parseInt(br.readLine());
//
//            // 이중 우선순위 큐 - 삽입/삭제 - 우선순위 높은것, 낮은것
//            Deque<Integer> dq = new ArrayDeque<>();
//
//            for (int j = 0; j < K; j++) {
//                StringTokenizer st = new StringTokenizer(br.readLine());
//                String act = st.nextToken(); // D (삭제) 또는 I (삽입)
//                int n = Integer.parseInt(st.nextToken()); // 정수 - 음수면 최솟값 삭제, 양수는 최대값 삭제
//
//                if (act.equals("I")) dq.add(n);
//                else {
//                    if (n < 0) {
//                        if (!dq.isEmpty()) dq.removeFirst();
//                    }
//                    else {
//                        if (!dq.isEmpty()) dq.removeLast();
//                    }
//                }
//
//                Integer[] tmp = dq.toArray(new Integer[0]);
//                Arrays.sort(tmp);
//                dq.clear();
//                dq.addAll(Arrays.asList(tmp));
//            }
//
//            // Q의 최댓값과 최솟값을 출력한다.
//            if (dq.isEmpty()){
//                sb.append("EMPTY");
//            } else {
//                sb.append(dq.removeLast()).append(" ");
//                sb.append(dq.removeFirst());
//            }
//            sb.append("\n");
//        }
//
//        System.out.println(sb);
//
//    }

}
