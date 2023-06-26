const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [N, K] = input[0].split(" ").map(Number);
const rocks = input[1].split(" ").map(Number);

function energyCalc(a, b) {
  return Math.abs(a - b) * (1 + Math.abs(rocks[a] - rocks[b]));
}

const dp = Array(N).fill(false);
dp[0] = true;

for (let i = 1; i < N; i++) {
  for (let j = 0; j < i; j++) {
    if (dp[j]) {
      const energy = energyCalc(j, i);
      if (energy <= K) {
        dp[i] = true;
        break;
      }
    }
  }
  if (dp[i]) continue;
}

console.log(dp[N - 1] ? "YES" : "NO");
