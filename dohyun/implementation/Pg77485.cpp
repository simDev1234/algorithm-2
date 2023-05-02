#include <bits/stdc++.h>
using namespace std;

vector<int> solution(int rows, int columns, vector<vector<int>> queries)
{
	vector<vector<int>> nums(rows, vector<int>(columns));

	// 행렬을 순서대로 초기화함
	for (int i = 0, num = 1; i < rows; i++)
		for (int j = 0; j < columns; j++)
			nums[i][j] = num++;

	vector<int> answers;
	for (int i = 0; i < queries.size(); i++) {
		// 0-base라 인덱스에서 -1을 빼줌
		int r1 = queries[i][0] - 1, c1 = queries[i][1] - 1;
		int r2 = queries[i][2] - 1, c2 = queries[i][3] - 1;
		int temp = nums[r1][c1];
		int minimum = temp;

		// 회전을 시키면서 위치가 변경된 수에 대해 최솟값을 구함
		for (int r = r1 + 1; r <= r2; r++) { // 좌측
			minimum = min(minimum, nums[r][c1]);
			nums[r-1][c1] = nums[r][c1];
		}
		for (int c = c1 + 1; c <= c2; c++) { // 하측
			minimum = min(minimum, nums[r2][c]);
			nums[r2][c-1] = nums[r2][c];
		}
		for (int r = r2 - 1; r >= r1; r--) { // 우측
			minimum = min(minimum, nums[r][c2]);
			nums[r+1][c2] = nums[r][c2];
		}
		for (int c = c2 - 1; c > c1; c--) { // 상측
			minimum = min(minimum, nums[r1][c]);
			nums[r1][c+1] = nums[r1][c];
		}
		// 회전으로 인해 지워진 수 복구
		nums[r1][c1+1] = temp;
		// 쿼리에 대한 최솟값은 answers에 저장됨
		answers.push_back(minimum);
	}

	return answers;
}
