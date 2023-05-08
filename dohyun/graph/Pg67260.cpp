#include <bits/stdc++.h>
using namespace std;

vector<int> adj[200'000];
bool visited[200'000];
unordered_map<int, int> pre, waiting;

void solve(int cur) {
	if (visited[cur]) return;

	if (!visited[pre[cur]]) {
		waiting[cur] = pre[cur];
		return;
	}

	visited[cur] = true;
	solve(waiting[cur]);

	for (int next : adj[cur])
		solve(next);
}

bool solution(int n, vector<vector<int>> path, vector<vector<int>> order) {
	for (int i = 0; i < path.size(); ++i) {
		adj[path[i][0]].push_back(path[i][1]);
		adj[path[i][1]].push_back(path[i][0]);
	}

	for (int i = 0; i < order.size(); ++i) // B에 들리기 전에 A를 먼저 가야함
		pre[order[i][1]] = order[i][0];

	if (pre[0])
		return false;

	visited[0] = true;
	for (int conn : adj[0])
		solve(conn);

	for (int i = 0; i < n; ++i)
		if (!visited[i])
			return false;

	return true;
}
