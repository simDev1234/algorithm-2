#define fastio ios_base::sync_with_stdio(0);cin.tie(NULL);cout.tie(NULL)
#include <bits/stdc++.h>
using namespace std;

vector<string> cogwheels(5);
int isSpinning[5];
bool checked[5];

int calculateScore() {
    int totalScore = 0;
    for (int i = 1; i <= 4; ++i)
        if (cogwheels[i][0] == '1')
            totalScore += pow(2, i - 1);
    return totalScore;
}

void determineWheelDirection(int wheelNo, int direction) {
    isSpinning[wheelNo] = direction;
    checked[wheelNo] = true;

    if (wheelNo > 1 && !checked[wheelNo - 1]) {
        if (direction != 0 && cogwheels[wheelNo - 1][2] != cogwheels[wheelNo][6])
            determineWheelDirection(wheelNo - 1, -direction);
        else
            determineWheelDirection(wheelNo - 1, 0);
    }
    if (wheelNo < 4 && !checked[wheelNo + 1]) {
        if (direction != 0 && cogwheels[wheelNo][2] != cogwheels[wheelNo + 1][6])
            determineWheelDirection(wheelNo + 1, -direction);
        else
            determineWheelDirection(wheelNo + 1, 0);
    }
}

void rotateWheels() {
    for (int i = 1; i <= 4; ++i) {
        if (isSpinning[i] == 1)
            rotate(cogwheels[i].rbegin(), cogwheels[i].rbegin() + 1, cogwheels[i].rend());
        else if (isSpinning[i] == -1)
            rotate(cogwheels[i].begin(), cogwheels[i].begin() + 1, cogwheels[i].end());
    }
}

int main() {
    fastio;

    for (int i = 1; i <= 4; ++i)
        cin >> cogwheels[i];

    int numberOfRotations, wheelNo, rotationDir;
    cin >> numberOfRotations;
    for (int i = 0; i < numberOfRotations; ++i) {
        cin >> wheelNo >> rotationDir;
		memset(checked, false, sizeof checked);
		memset(isSpinning, false, sizeof isSpinning);
        determineWheelDirection(wheelNo, rotationDir);
        rotateWheels();
    }
    cout << calculateScore() << '\n';

    return 0;
}
