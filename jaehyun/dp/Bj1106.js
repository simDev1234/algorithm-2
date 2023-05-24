const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [C, N] = input[0].split(" ").map(Number);
const costAndPersons = input.slice(1).map((row) => row.split(" ").map(Number));

function solution(C, N, cases) {
  const sortedCases = cases.sort((a, b) => a[0] - b[0]);
  const dp = Array(C + 1).fill(Infinity);
  dp[0] = 0;

  for (let [cost, customN] of sortedCases) {
    if (dp[customN] > cost) dp[customN] = cost;
    for (let i = 1; i <= C; i++) {
      dp[i] =
        i < customN
          ? Math.min(dp[i], cost)
          : Math.min(dp[i], dp[customN] + dp[i - customN]);
    }
  }

  console.log(dp[C]);
}

solution(C, N, costAndPersons);
