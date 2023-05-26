const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];
const qualities = input[1]
  .split(" ")
  .map(Number)
  .sort((a, b) => a - b);

let ans = 0;

if (N % 2 === 0) {
  for (let i = 0; i < N / 2; i++) {
    ans += 2 * qualities[N - 1 - i];
  }
} else {
  for (let i = 0; i < (N - 1) / 2; i++) {
    ans += 2 * qualities[N - 1 - i];
  }
  ans += qualities[(N - 1) / 2];
}
console.log(ans);
