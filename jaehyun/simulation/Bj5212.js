const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [R, C] = input.shift().split(" ").map(Number);
// 50년이 지나면 인접한 세 칸 또는 네 칸에 바다가 있는 땅은 모두 잠겨 버린다.
const visited = Array.from({ length: R }, () => Array(C).fill(false));
let MAP = input.map((row) => row.replace("\r", "").split(""));

for (let i = 0; i < R; i++) {
  for (let j = 0; j < C; j++) {
    if (MAP[i][j] === "X") {
      if (check(j, i, MAP)) {
        MAP[i][j] = ".";
      }
    }
  }
}

const X = [];
const Y = [];

for (let i = 0; i < R; i++) {
  for (let j = 0; j < C; j++) {
    if (MAP[i][j] === "X") {
      X.push(j);
      Y.push(i);
    }
  }
}

const minX = Math.min(...X);
const maxX = Math.max(...X);
const minY = Math.min(...Y);
const maxY = Math.max(...Y);

const returnArr = [];

MAP.forEach((elem, i) => {
  if (i >= minY && i <= maxY) returnArr.push(MAP[i].slice(minX, maxX + 1));
});

console.log(returnArr.map((elem) => elem.join("")).join("\n"));

function check(x, y, MAP) {
  visited[y][x] = true;
  const isValue = (x, y) => x >= 0 && x < C && y >= 0 && y < R;
  const ds = [
    [-1, 0],
    [1, 0],
    [0, 1],
    [0, -1],
  ];
  let count = 0;
  for (let i = 0; i < 4; i++) {
    const dx = x + ds[i][0];
    const dy = y + ds[i][1];
    if (!isValue(dx, dy)) count++;
    if (isValue(dx, dy) && !visited[dy][dx]) {
      if (MAP[dy][dx] === ".") count++;
    }
  }
  return count >= 3;
}
