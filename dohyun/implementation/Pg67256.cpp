#include <bits/stdc++.h>
using namespace std;
using pi = pair<int, int>;

string solution(vector<int> numbers, string hand) {
	unordered_map<int, pi> numPad;
	for (int i = 1; i <= 9; ++i)
		numPad[i] = {(i - 1) / 3, (i - 1) % 3};
	numPad.insert({{-1, {3, 0}}, {0, {3, 1}}, {-2, {3, 2}}});

	string answer;
	pi leftHand = numPad[-1], rightHand = numPad[-2];
	for (auto& n : numbers) {
		if (n % 3 == 2 || n == 0) {
			int distL = abs(leftHand.first - numPad[n].first) + abs(leftHand.second - numPad[n].second);
			int distR = abs(rightHand.first - numPad[n].first) + abs(rightHand.second - numPad[n].second);

			if (distL > distR || (distL == distR && hand == "right")) {
				answer += "R";
				rightHand = numPad[n];
			} else {
				answer += "L";
				leftHand = numPad[n];
			}
		} else {
			answer += (n % 3 == 1) ? "L" : "R";
			(n % 3 == 1) ? leftHand = numPad[n] : rightHand = numPad[n];
		}
	}
	return answer;
}
