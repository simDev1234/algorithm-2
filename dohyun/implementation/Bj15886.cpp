#define fastio ios_base::sync_with_stdio(0);cin.tie(NULL);cout.tie(NULL)
#include <bits/stdc++.h>
using namespace std;

bool visited[1000];
string s;

void dfs(int x) {
	if (visited[x]) return;
	visited[x] = true;

	// 문자에 따라서 동쪽으로 이동할지 서쪽으로 이동할지 결정
	if (s[x] == 'E')
		dfs(x + 1);
	else
		dfs(x - 1);
}

int main() {
	fastio;

	int n;
	cin >> n;
	cin >> s;

	int ans = 0;
	// 어디에서 시작하든 (동쪽으로 쭉가든, 서쪽으로 쭉가든)
	// 결국엔 EW가 있는 곳으로 빠져 무한 루프를 돌게됨
	for (int i = 0; i < s.size(); ++i) {
		if (!visited[i] && s[i] == 'E') {
			dfs(i);
			ans++;
		}
	}
	cout << ans;
}
