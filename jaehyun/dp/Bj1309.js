const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = +fs.readFileSync(filePath).toString().trim();

//
// 2Xl 아무것도 안 고른 경우, 왼쪽만 , 오른쪽만 고르는 경우
// 2X2
// 2Xl에서 아무것도 안고를 때, 왼, 오 둘다 추가가능
// 2X1에선 왼쪽을 고를 땐, 2X2에선 아무것도X or 오른쪽
// 2X2에서도 마찬가지
// dp[i][0] = dp[i-1][0]+dp[i-1][1] + dp[i-1][2]
// dp[i][1] = dp[i-1][0] + dp[i-1][2]
// dp[i][2] = dp[i-1][0] + dp[i-1][1]

let dp = [1, 3];
for (let i = 2; i <= input; i++) {
  dp[i] = (dp[i - 1] * 2 + dp[i - 2]) % 9901;
}

console.log(dp[input]);
