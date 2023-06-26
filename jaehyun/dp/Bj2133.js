const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim();
const N = +input;

function solution(n) {
  if (n % 2 === 1) return 0;

  const dp = new Array(n + 1).fill(0);
  dp[0] = 1;
  dp[2] = 3;

  for (let i = 4; i <= n; i += 2) {
    dp[i] = dp[i - 2] * 3;

    for (let j = 4; j <= i; j += 2) {
      dp[i] += dp[i - j] * 2;
    }
  }

  return dp[n];
}

console.log(solution(N));
