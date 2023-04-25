#define fastio ios_base::sync_with_stdio(0);cin.tie(NULL);cout.tie(NULL)
#include <bits/stdc++.h>
#define all(x) (x).begin(), (x).end()
using namespace std;

struct Candidate {
	int id;
	int rec;
	int stamp;
};

int main() {
	int n, total;
	cin >> n >> total;

	vector<Candidate> cand;
	for (int i = 0; i < total; ++i) {
		int id;
		cin >> id;

		bool found = false;
		for (auto& c : cand) {
			if (c.id == id) {
				c.rec++;
				found = true;
				break;
			}
		}

		if (!found) {
			// 찾을 수 없으면 사진틀에 우선 게시함
			if (cand.size() < n) {
				cand.push_back({ id, 1, i });
			} else {
				// 추천순과 시간순으로 정렬한다.
				sort(all(cand), [](auto& a, auto& b) {
					if (a.rec == b.rec)
						return a.stamp > b.stamp;
					return a.rec > b.rec;
				});

				// 가장 오래된 학생을 신규 학생으로 대체함
				cand.back() = { id, 1, i };
			}
		}
	}

	// 오름차순으로 출력
	sort(all(cand), [](auto& a, auto& b) {
		return a.id < b.id; });

	for (auto& candidate : cand)
		cout << candidate.id << " ";

	return 0;
}
