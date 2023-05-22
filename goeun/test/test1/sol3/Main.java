package test.test1.sol3;

import java.util.*;

class Solution{

    public int[] solution(String[] gems) {

        Set<String> allKinds = new HashSet<>(Arrays.asList(gems));
        Map<String, Integer> gemCounts = new HashMap<>();
        Queue<String> selectedGems = new LinkedList<>();

        int start = 0;
        int end = Integer.MAX_VALUE;
        int startPoint = 0;

        for (String gem : gems) {

            selectedGems.add(gem);
            gemCounts.put(gem, gemCounts.getOrDefault(gem, 0) + 1);

            while (true) {

                String frontGem = selectedGems.peek();

                if (gemCounts.get(frontGem) <= 1) break;

                selectedGems.poll();
                start++;
                gemCounts.put(frontGem, gemCounts.get(frontGem) - 1);

            }

            if (gemCounts.size() == allKinds.size() && end > selectedGems.size()) {
                startPoint = start;
                end = selectedGems.size();
            }

        }

        return new int[]{startPoint + 1, startPoint + end};
    }

}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }
}
