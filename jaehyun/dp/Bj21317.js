const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input.shift();
const K = +input.pop();
const cost = input.map((row) => row.split(" ").map(Number));
const smallJumpCost = [];
const bigJumpCost = [];

cost.forEach(([small, big]) => {
  smallJumpCost.push(small);
  bigJumpCost.push(big);
});
smallJumpCost.unshift(0);
bigJumpCost.unshift(0);

let dp = [];
dp[0] = 0;
dp[1] = 0;
dp[2] = smallJumpCost[1];
dp[3] = Math.min(dp[1] + bigJumpCost[1], dp[2] + smallJumpCost[2]);

let useVeryBigJumpDP = [
  Number.MAX_SAFE_INTEGER,
  Number.MAX_SAFE_INTEGER,
  Number.MAX_SAFE_INTEGER,
  Number.MAX_SAFE_INTEGER,
];

for (let i = 4; i <= N; i++) {
  dp[i] = Math.min(
    dp[i - 2] + bigJumpCost[i - 2],
    dp[i - 1] + smallJumpCost[i - 1]
  );

  useVeryBigJumpDP[i] = Math.min(
    useVeryBigJumpDP[i - 2] + bigJumpCost[i - 2],
    useVeryBigJumpDP[i - 1] + smallJumpCost[i - 1],
    dp[i - 3] + K
  );
}

console.log(Math.min(dp[N], useVeryBigJumpDP[N]));
