#define fastio ios_base::sync_with_stdio(0);cin.tie(NULL);cout.tie(NULL)
#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> p1(150, vector<int>(150, 0));
vector<vector<vector<int>>> p2;

vector<vector<int>> rotate(vector<vector<int>>& mat) {
	int rows = mat.size();
	int cols = mat[0].size();
	vector<vector<int>> rotated(cols, vector<int>(rows));

	for (int r = 0; r < cols; ++r)
		for (int c = 0; c < rows; ++c)
			rotated[r][c] = mat[rows - c - 1][r];
	return rotated;
}

int simulate(int i, int j, int n1, int m1, vector<vector<int>>& p2) {
	int n2 = p2.size(), m2 = p2[0].size();

	// 퍼즐 1과 퍼즐 2가 겹치는지 확인함
	for (int r = 0; r < n2; ++r)
		for (int c = 0; c < m2; ++c)
			// 겹치는 부분이 있으면 불가능
			if (p1[i + r][j + c] && p2[r][c])
				 return INT_MAX;

	// 겹치지 않는 경우, 액자의 최소 넓이 계산함
	int minRow = min(i, 50), maxRow = max(50 + n1, i + n2); // 행의 최소 위치와 최대 위치
	int minCol = min(j, 50), maxCol = max(50 + m1, j + m2); // 열의 최소 위치와 최대 위치

	// 액자의 넓이 계산 = 가격
	return (maxRow - minRow) * (maxCol - minCol);
}

int main() {
	int n1, m1, n2, m2;
	cin >> n1 >> m1;
	// 한 퍼즐을 중앙에 고정시킨다.
	for (int i = 0; i < n1; ++i) {
		string line;
		cin >> line;
		for (int j = 0; j < m1; ++j)
			p1[50 + i][50 + j] = line[j] - '0';
	}

	cin >> n2 >> m2;
	vector<vector<int>> temp(n2, vector<int>(m2));
	for (int i = 0; i < n2; ++i) {
		string line;
		cin >> line;
		for (int j = 0; j < m2; ++j)
			temp[i][j] = line[j] - '0';
	}

	// 미리 4방향으로 회전한 퍼즐을 미리 구해둔다.
	p2.resize(4);
	p2[0] = temp;
	for (int k = 1; k < 4; ++k)
		p2[k] = rotate(p2[k - 1]);

	// 돌려가며 시뮬레이션을 돌려본다.
	int answer = INT_MAX;
	for (int r = 0; r <= 100; ++r)
		for (int c = 0; c <= 100; ++c)
			for (int i = 0; i < 4; ++i)
				answer = min(answer, simulate(r, c, n1, m1, p2[i]));
	cout << answer;
}

