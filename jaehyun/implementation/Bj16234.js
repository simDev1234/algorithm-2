const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [N, L, R] = input[0].split(" ").map(Number);
const arr = input.slice(1).map((row) => row.split(" ").map(Number));
const ds = [
  [-1, 0],
  [1, 0],
  [0, 1],
  [0, -1],
];

let days = 0;

while (true) {
  let isDone = [];
  const visited = Array.from({ length: N }, () => Array(N).fill(false));
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (!visited[i][j]) {
        const isChanged = bfs(j, i, visited);
        isDone.push(isChanged);
      }
    }
  }
  if (isDone.some((elem) => elem)) days++;
  else break;
}

function bfs(x, y, visited) {
  const peopleMap = [[x, y]];
  const q = [[x, y]];
  visited[y][x] = true;
  const isValue = (X, Y) => X >= 0 && Y >= 0 && X < N && Y < N;
  let sum = arr[y][x];

  while (q.length) {
    const [X, Y] = q.shift();
    const P = arr[Y][X];
    for (let i = 0; i < 4; i++) {
      const dx = X + ds[i][0];
      const dy = Y + ds[i][1];
      if (isValue(dx, dy) && !visited[dy][dx]) {
        const nearP = arr[dy][dx];
        const diff = Math.abs(nearP - P);
        if (diff >= L && diff <= R) {
          visited[dy][dx] = true;
          q.push([dx, dy]);
          peopleMap.push([dx, dy]);
          sum += nearP;
        }
      }
    }
  }

  if (peopleMap.length > 1) {
    const equal = Math.floor(sum / peopleMap.length);
    peopleMap.forEach(([X, Y]) => {
      arr[Y][X] = equal;
    });
  }

  return peopleMap.length !== 1;
}

console.log(days);
