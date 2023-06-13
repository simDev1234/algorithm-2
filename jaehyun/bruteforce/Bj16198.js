const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];
const energies = input[1].split(" ").map(Number);
const visited = Array(N).fill(false);

let max = -Infinity;

function dfs(amount, value) {
  if (amount === 2) {
    max = Math.max(max, value);
    return;
  }
  for (let i = 0; i < N; i++) {
    if (visited[i] || i === 0 || i === N - 1) continue;
    visited[i] = true;
    let left;
    for (let j = i - 1; j >= 0; j--) {
      if (!visited[j]) {
        left = energies[j];
        break;
      }
    }
    if (!left) {
      visited[i] = false;
      continue;
    }
    let right;
    for (let j = i + 1; j < N; j++) {
      if (!visited[j]) {
        right = energies[j];
        break;
      }
    }
    if (!right) {
      visited[i] = false;
      continue;
    }
    const energy = left * right;
    dfs(amount - 1, value + energy);
    visited[i] = false;
  }
}

dfs(N, 0);

console.log(max);
