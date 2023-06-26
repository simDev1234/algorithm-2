const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "./example.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");

const [K, N] = input.shift().split(" ").map(Number);
const lines = input.map(Number).sort((a, b) => a - b);

let start = 0;
let end = lines[K - 1];

let max = 0;

while (start <= end) {
  let mid = Math.floor((start + end) / 2);
  let count = 0;
  lines.forEach((x) => {
    count += Math.floor(x / mid);
  });

  if (count >= N) {
    max = Math.max(max, mid);
    start = mid + 1;
  } else {
    end = mid - 1;
  }
}

console.log(max);
