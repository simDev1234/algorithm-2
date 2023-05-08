#include <bits/stdc++.h>
#define all(x) (x).begin(), (x).end()
using namespace std;

vector<int> solution(vector<string> gems) {
	unordered_map<string, int> cnt;
	set<string> types(all(gems));

	int left = 0, right = 0;
	int ansLeft, ansRight, minLen = 1e9;
	while (right < gems.size()) {
		cnt[gems[right]]++;
		right++;

		while (cnt.size() == types.size()) {
			if (right - left < minLen) {
				ansLeft = left, ansRight = right;
				minLen = right - left;
			}
			cnt[gems[left]]--;
			if (cnt[gems[left]] == 0) {
				cnt.erase(gems[left]);
			}
			left++;
		}
	}

	return {ansLeft + 1, ansRight};
}
