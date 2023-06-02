const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];
const str = input[1].split(" ");

const dp = Array.from({ length: N + 1 }, () => Array(N + 1).fill(0));
for (let i = 1; i <= N; i++) {
  for (let j = 1; j <= N; j++) {
    if (str[N - i] === str[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
    else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
  }
}

console.log(N - dp[N][N]);
