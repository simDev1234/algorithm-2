#define fastio ios_base::sync_with_stdio(0);cin.tie(NULL);cout.tie(NULL)
#include <bits/stdc++.h>
using namespace std;

int main() {
    int k, n;
    cin >> k >> n;

    vector<char> st(k);
    for (int i = 0; i < k; ++i)
		st[i] = 'A' + i;

    string lt;
    cin >> lt;

    vector<string> board(n);
    for (int i = 0; i < n; ++i)
		cin >> board[i];

	// 위에서 ?를 만날 때까지 내려옴
    bool f = false;
    for (int r = 0; r < n && !f; ++r) {
        for (int c = 0; c < k - 1; ++c) {
            if (board[r][c] == '-') swap(st[c], st[c + 1]);
            else if (board[r][c] == '?') { f = true; break; }
        }
	}

	// 아래에서 ?를 만날 때까지 올라옴
    f = false;
    for (int r = n - 1; r >= 0 && !f; --r) {
        for (int c = 0; c < k - 1; ++c) {
            if (board[r][c] == '-') swap(lt[c], lt[c + 1]);
            else if (board[r][c] == '?') { f = true; break; }
        }
	}

	// 위에서 내려왔을 때와 아래에서 내려왔을 때의 문자열을 서로 비교한다.
	// 같은 위치에 있는 문자가 서로 동일하다면 그냥 내려오면 된다.
	// 만약 서로 교차하는 경우에는 가로 선을 타고 가면 된다.
    string ans = "";
    for (int i = 0; i < k - 1; ++i) {
        if (st[i] == lt[i + 1] && st[i + 1] == lt[i] && (i == 0 || ans.back() == '*')) {
            swap(st[i], st[i + 1]);
            ans += '-';
        } else if (st[i] == lt[i]) ans += '*';
    }

    if (ans.length() != k - 1) ans = string(k - 1, 'x');

    cout << ans << endl;
    return 0;
}
