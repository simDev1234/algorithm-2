#define fastio ios_base::sync_with_stdio(0);cin.tie(NULL);cout.tie(NULL)
#include <bits/stdc++.h>
#define all(x) (x).begin(), (x).end()
using namespace std;
using ti = tuple<int, int, int>;

void rotateArr(vector<vector<int>>& v, int sx, int sy, int fx, int fy) {
	if (sx == fx && sy == fy) return;

	vector<int> temp(3);
	temp[0] = v[sx][fy];
	temp[1] = v[fx][fy];
	temp[2] = v[fx][sy];

	// 오른쪽으로 회전
	for (int i = fy; i > sy; i--) {
		v[sx][i] = v[sx][i - 1];
	}
	// 아래로 회전
	for (int i = fx; i > sx; i--) {
		if (i == sx + 1) v[i][fy] = temp[0];
		else v[i][fy] = v[i - 1][fy];
	}
	// 왼쪽으로 회전
	for (int i = sy; i < fy; i++) {
		if (i == fy - 1) v[fx][i] = temp[1];
		else v[fx][i] = v[fx][i + 1];
	}
	// 위로 회전
	for (int i = sx; i < fx; i++) {
		if (i == fx - 1) v[i][sy] = temp[2];
		else v[i][sy] = v[i + 1][sy];
	}
	rotateArr(v, sx + 1, sy + 1, fx - 1, fy - 1);
}

int main() {
	fastio;

	int n, m, k;
	cin >> n >> m >> k;

	vector<vector<int>> a(n, vector<int>(m));
	for (int i = 0; i < n; ++i)
		for (int j = 0; j < m; ++j)
			cin >> a[i][j];

	vector<ti> v(k);
	for (int i = 0; i < k; ++i) {
		int r, c, s;
		cin >> r >> c >> s;
		v[i] = {r - 1, c - 1, s};
	}
	sort(all(v));

	int ans = 1e9;
	do {
		vector<vector<int>> copied(a);
		for (int i = 0; i < v.size(); ++i) {
			auto [r, c, s] = v[i];
			rotateArr(copied, r - s, c - s, r + s, c + s);
		}

		int minSum = 1e9;
		for (int i = 0; i < copied.size(); ++i) {
			int rowSum = 0;
			for (int j = 0; j < copied[i].size(); ++j)
				rowSum += copied[i][j];
			minSum = min(rowSum, minSum);
		}
		ans = min(ans, minSum);
	} while (next_permutation(all(v)));
	cout << ans << '\n';
}
