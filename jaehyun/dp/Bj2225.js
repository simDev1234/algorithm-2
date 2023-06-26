const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim();

const [N, K] = input.split(" ").map(Number);

// dp[k][n] = dp[k-1][0] + ...+ dp[k-1][n]
// 예를 들어 합이 4가 되는 경우 맨 뒷자리에 들어올 숫자를 고정시키고, 들어올 수 있는 경우의 수의 합
// 맨 뒤가 0이면 합이 4가 되기 위해 4만 들어올 수 있음

const dp = [];

for (let i = 1; i <= K; i++) {
  dp[i] = new Array(N + 1).fill(i === 1 ? 1 : 0);
  dp[i][0] = 1;
}

for (let i = 2; i <= K; i++) {
  for (let j = 1; j <= N; j++) {
    dp[i][j] =
      dp[i - 1].slice(0, j + 1).reduce((acc, curr) => acc + curr, 0) %
      1000000000;
  }
}

console.log(dp[K][N]);
