const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [N, M] = input[0].split(" ").map(Number);
const maze = input.slice(1).map((row) => row.split("").map(Number));
const visited = Array.from({ length: N }, () => Array(M).fill(false));
const ds = [
  [-1, 0],
  [1, 0],
  [0, 1],
  [0, -1],
];

function bfs() {
  const q = [[1, 1, 1]];
  const isValue = (x, y) => x >= 0 && y >= 0 && x < N && y < M;
  visited[0][0] = true;
  while (q.length) {
    const [x, y, count] = q.shift();
    for (let i = 0; i < ds.length; i++) {
      const X = x - 1 + ds[i][0];
      const Y = y - 1 + ds[i][1];
      if (isValue(X, Y) && !visited[X][Y] && maze[X][Y] === 1) {
        if (X === N - 1 && Y === M - 1) return count + 1;
        visited[X][Y] = true;
        q.push([X + 1, Y + 1, count + 1]);
      }
    }
  }
}

const answer = bfs();
console.log(answer);
