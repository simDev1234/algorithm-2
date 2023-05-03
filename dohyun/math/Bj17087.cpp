#define fastio ios_base::sync_with_stdio(0);cin.tie(NULL);cout.tie(NULL)
#include <bits/stdc++.h>
using namespace std;

int main() {
	fastio;

	int n, s;
	cin >> n >> s;

	vector<int> d(n);
	int l = s;
	for (int i = 0; i < n; ++i) {
		cin >> d[i];
		// 현재 수빈이와 각 동생간의 거리를 미리 계산해둔다.
		d[i] = abs(d[i] - s);
	}

	// 모든 거리의 최대공약수가 수빈이가 1초에 한 번에 이동할 수 있는 D의 최댓값이다.
	int maxD = d[0];
	for (int i = 1; i < n; ++i)
		maxD = gcd(maxD, d[i]);
	cout << maxD << '\n';
}
