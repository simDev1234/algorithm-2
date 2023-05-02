#include <bits/stdc++.h>
using namespace std;

int main() {
	string s;
	vector<string> v;

	cin >> s;
	for (int i = 0; i < s.size(); i++)
		v.push_back(s.substr(i));
	sort(v.begin(), v.end());

	for (string t : v)
		cout << t << '\n';
}
