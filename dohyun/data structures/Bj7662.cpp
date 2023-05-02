#define fastio ios_base::sync_with_stdio(0);cin.tie(NULL);cout.tie(NULL)
#include <bits/stdc++.h>
using namespace std;

int main() {
	fastio;

	int t;
	cin >> t;

	while (t--) {
		int k;
		cin >> k;

		multiset<int> ms;
		while (k--) {
			char c;
			int n;

			cin >> c >> n;

			if (c == 'I') {
				ms.insert(n);
			} else if (c == 'D' && !ms.empty()) {
				if (n == 1)
					ms.erase(prev(ms.end()));
				else if (n == -1)
					ms.erase(ms.begin());
			}
		}

		if (!ms.empty())
			cout << *prev(ms.end()) << ' ' << *ms.begin() << '\n';
		else
			cout << "EMPTY\n";
	}
}
