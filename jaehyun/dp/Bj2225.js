const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim();

const [N, K] = input.split(" ").map(Number);

const returnArr = Array.from({ length: N + 1 }, () => Array(K).fill(1));

for (let i = 1; i <= N; i++) {
  for (let j = 1; j <= K; j++) {
    returnArr[i][j] = (returnArr[i - 1][j] + returnArr[i][j - 1]) % 1000000000;
  }
}

console.log(returnArr[N][K - 1]);
