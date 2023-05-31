const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];
const K = +input[1];
const arr = input[2].split(" ").map(Number);

function solution(n, k, sensor) {
  if (k >= n) return 0;
  const diff = [];
  let answer = 0;

  sensor.sort((a, b) => a - b);

  for (let i = 0; i < n - 1; i++) {
    diff.push(sensor[i + 1] - sensor[i]);
  }

  diff.sort((a, b) => b - a);

  for (let i = k - 1; i < n - 1; i++) {
    answer += diff[i];
  }

  return answer;
}

console.log(solution(N, K, arr));
