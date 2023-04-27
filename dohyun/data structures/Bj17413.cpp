#include <bits/stdc++.h>
#define all(x) (x).begin(), (x).end()
using namespace std;

int main() {
    string s;
	getline(cin, s);

	regex r("(<[^>]*>| )");
    for (sregex_token_iterator i(all(s), r, {-1, 0}), e; i != e; ++i) {
        string t = *i;
		if (t[0] != '<')
			reverse(all(t));
		cout << t;
    }
}
