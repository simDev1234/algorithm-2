const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

let T = +input.shift();
let i = 0;

while (T--) {
  const N = input[i];
  const coins = input[i + 1].split(" ").map(Number);
  const money = +input[i + 2];
  const dp = Array(money + 1).fill(0);

  dp[0] = 1;
  for (let i = 0; i < N; i++) {
    for (let j = coins[i]; j <= money; j++) {
      dp[j] += dp[j - coins[i]];
    }
  }

  console.log(dp[money]);
  i += 3;
}
