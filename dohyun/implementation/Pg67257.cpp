#include <bits/stdc++.h>
using namespace std;
using ll = long long;

#define all(x) (x).begin(), (x).end()

vector<string> resplit(const string& s, const string& pattern) {
	regex rx(pattern);
	sregex_token_iterator iter(all(s), rx, {-1, 0}), end;
	return {iter, end};
}

inline ll calc(ll a, ll b, string op) {
	if (op == "+") return a + b;
	if (op == "-") return a - b;
	return a * b;
}

ll solution(string expression) {
	auto splitted = resplit(expression, R"([\*\-\+])");
	vector<string> op = {"*", "+", "-"};
	ll ans = 0;

	do {
		list<string> origin(all(splitted));
		for (auto p : op) {
			for (auto iter = origin.begin(); iter != origin.end();) {
				if (*iter == p) {
					ll a = stoll(*(prev(iter)));
					ll b = stoll(*(next(iter)));
					ll c = calc(a, b, *iter);
					iter = origin.erase(prev(iter), next(iter, 2));
					origin.insert(iter, to_string(c));
				} else ++iter;
			}
		}
		ans = max(ans, abs(stoll(origin.front())));
	} while (next_permutation(all(op)));
	return ans;
}
