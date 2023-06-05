const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const n = +input.shift();
const glasses = input.map(Number);

const dp = Array.from({ length: n + 1 }, () => Array(2).fill(0));
dp[1] = [glasses[0], 0];
dp[2] = [glasses[0] + glasses[1], glasses[0]];

for (let i = 3; i <= n; i++) {
  dp[i] = [
    glasses[i - 1] +
      Math.max(glasses[i - 2] + Math.max(...dp[i - 3]), Math.max(...dp[i - 2])),
    Math.max(...dp[i - 1]),
  ];
}

console.log(Math.max(...dp[n]));
