const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];
const L = input[1].split(" ").map(Number);
const J = input[2].split(" ").map(Number);

const dp = Array(101).fill(0);

for (let i = 0; i < N; i++) {
  for (let j = 100; j > L[i]; j--) {
    dp[j] = Math.max(dp[j], dp[j - L[i]] + J[i]);
  }
}

console.log(dp[100]);
