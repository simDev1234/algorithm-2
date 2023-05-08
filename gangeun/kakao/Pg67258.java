import java.util.*;
import java.util.stream.Collectors;

class Pg67258 {
    public int[] solution(String[] gems) {
        Set<String> gemSet = Arrays.stream(gems).collect(Collectors.toSet());
        Map<String, Integer> gemCount = new HashMap<>();
        int start = 0;
        int end = 0;
        int minStart = 0;
        int minLength = Integer.MAX_VALUE;

        while (end < gems.length) {
            gemCount.put(gems[end], gemCount.getOrDefault(gems[end], 0) + 1);
            end++;

            while (gemCount.size() == gemSet.size()) {
                if (end - start < minLength) {
                    minLength = end - start;
                    minStart = start;
                }

                gemCount.put(gems[start], gemCount.get(gems[start]) - 1);
                if (gemCount.get(gems[start]) == 0) {
                    gemCount.remove(gems[start]);
                }
                start++;
            }
        }

        return new int[] { minStart + 1, minStart + minLength };
    }
}
