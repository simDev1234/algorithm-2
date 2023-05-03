#include <algorithm>
#define fastio ios_base::sync_with_stdio(0);cin.tie(NULL);cout.tie(NULL)
#include <bits/stdc++.h>
using namespace std;

vector<bool> eratos(int n) {
	vector<bool> isPrime(n + 1, true);
	isPrime[0] = isPrime[1] = false;

	vector<int> primes;
	for (int i = 2; i * i <= n; ++i)
		if (isPrime[i])
			for (int j = i * i; j <= n; j += i)
				isPrime[j] = false;
	return isPrime;
}

int main() {
	fastio;

	int t;
	cin >> t;

	while (t--) {
		int n;
		cin >> n;

		// 에라토스테네스의 체를 통해 n까지의 소수 구하기
		auto era = eratos(n);

		int ans = 0;
		// 그룹을 절반으로 나누어 중복으로 계산되는 일이 없도록 한다.
		for (int i = 0; i <= n / 2; ++i) {
			// 결국엔 합해서 n이 되어야 하므로 (i, n-i)로 나눈다.
			int a = i, b = n - i;
			// 두 개다 모두 소수라면 정답에 추가한다.
			if (era[a] && era[b])
				ans++;
		}
		cout << ans << '\n';
	}
}
