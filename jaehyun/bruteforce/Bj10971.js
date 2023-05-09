const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

let [N, M] = input.shift().split(" ").map(Number);
let city = input.map((row) => row.split(" ").map(Number));
const house = [];
const chicken = [];
const check = Array(chicken.length).fill(false);
let answer = Infinity;

for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (city[i][j] === 2) chicken.push([i, j]);
    if (city[i][j] === 1) house.push([i, j]);
  }
}

const getMinDistance = () => {
  let sum = 0;
  house.forEach(([hx, hy]) => {
    let min = Infinity;
    chicken.forEach(([cx, cy], index) => {
      if (check[index]) {
        min = Math.min(min, Math.abs(hx - cx) + Math.abs(hy - cy));
      }
    });
    sum += min;
  });
  return sum;
};

const dfs = (idx, cnt) => {
  if (cnt === M) {
    answer = Math.min(answer, getMinDistance());
    return;
  } else {
    for (let i = idx; i < chicken.length; i++) {
      if (check[i]) continue;
      check[i] = true;
      dfs(i, cnt + 1);
      check[i] = false;
    }
  }
};

dfs(0, 0);
console.log(answer);
