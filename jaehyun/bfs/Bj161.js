const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [M, N] = input[0].split(" ").map(Number);
const maze = input.slice(1).map((row) => row.split("").map(Number));
const ds = [
  [0, 1],
  [0, -1],
  [1, 0],
  [-1, 0],
];

const bfs = () => {
  const visited = Array.from({ length: N }, () => Array(M).fill(false));
  const q = [[0, 0, 0]];

  while (q.length) {
    const [x, y, count] = q.shift();
    if (x === M - 1 && y === N - 1) return count;
    for (let i = 0; i < 4; i++) {
      const dx = x + ds[i][0];
      const dy = y + ds[i][1];
      if (dx >= 0 && dy >= 0 && dx < M && dy < N && !visited[dy][dx]) {
        if (maze[dy][dx] === 0) {
          visited[dy][dx] = true;
          q.unshift([dx, dy, count]);
        } else {
          visited[dy][dx] = true;
          maze[dy][dx] = 1;
          q.push([dx, dy, count + 1]);
        }
      }
    }
  }
};

const answer = bfs();
console.log(answer);
