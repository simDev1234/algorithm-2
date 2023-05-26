const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const T = +input.shift();
let idx = 0;
while (idx < input.length) {
  const N = +input[idx];
  let score = [];
  for (let i = 0; i < N; i++) {
    score.push(input[idx + i + 1].split(" ").map(Number));
  }

  score.sort((a, b) => {
    if (a[0] === b[0]) return b[1] - a[1];
    return a[0] - b[0];
  });

  let temp = score[0][1];
  let count = 1;
  for (let i = 1; i < score.length; i++) {
    if (temp > score[i][1]) {
      temp = score[i][1];
      count++;
    }
  }

  console.log(count);

  idx = idx + N + 1;
  if (idx === input.length) break;
}
