#include <bits/stdc++.h>
using namespace std;

const int MOD = 1e9;
int d[201][201];

int main() {
	int n, k;
	cin >> n >> k;

	for (int i = 0; i <= n; i++)
		d[1][i] = 1;

	for (int i = 1; i <= k; i++)
		for (int j = 0; j <= n; j++)
			for (int l = 0; l <= j; l++)
				d[i][j] = (d[i][j] + d[i - 1][j - l]) % MOD;
	cout << d[k][n] << '\n';
}
