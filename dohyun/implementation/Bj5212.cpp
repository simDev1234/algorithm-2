#define fastio ios_base::sync_with_stdio(0);cin.tie(NULL);cout.tie(NULL)
#include <bits/stdc++.h>
using namespace std;
using pi = pair<int, int>;

bool visited[10][10];

int r, c;
vector<string> board;
int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};

int calc(int cx, int cy) {
	int cnt = 0;
	for (int i = 0; i < 4; ++i) {
		int nx = cx + dx[i];
		int ny = cy + dy[i];

		if (!(nx >= 0 && nx < r && ny >= 0 && ny < c)) cnt++;
		else if (board[nx][ny] == '.') cnt++;
	}
	return cnt;
}

int main() {
	fastio;

	cin >> r >> c;

	string line;
	for (int i = 0; i < r; ++i) {
		cin >> line;
		board.push_back(line);
	}

	vector<pi> cand;
	for (int i = 0; i < r; ++i)
		for (int j = 0; j < c; ++j)
			if (board[i][j] == 'X' && calc(i, j) >= 3)
				cand.push_back({i, j});
	for (auto [cx, cy] : cand)
		board[cx][cy] = '.';

	int sx = 1e9, sy = 1e9, fx = 0, fy = 0;
	for (int i = 0; i < r; ++i) {
		for (int j = 0; j < c; ++j) {
			if (board[i][j] == 'X') {
				sx = min(sx, i);
				sy = min(sy, j);
				fx = max(fx, i);
				fy = max(fy, j);
			}
		}
	}

	for (int i = sx; i <= fx; ++i) {
		for (int j = sy; j <= fy; ++j)
			cout << board[i][j];
		cout << '\n';
	}
}
