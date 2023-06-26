const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let [N, M] = fs.readFileSync(filePath).toString().trim().split(" ").map(Number);
const map = Array.from({ length: N + 1 }, () => Array(M + 1).fill(0));

let answer = 0;

function dfs(count) {
  if (count === N * M) {
    answer++;
    return;
  }
  let y = Math.floor(count / M) + 1;
  let x = (count % M) + 1;

  if (map[y - 1][x] === 1 && map[y][x - 1] === 1 && map[y - 1][x - 1] === 1) {
    dfs(count + 1);
  } else {
    dfs(count + 1);
    map[y][x] = 1;
    dfs(count + 1);
    map[y][x] = 0;
  }
}

dfs(0);

console.log(answer);
