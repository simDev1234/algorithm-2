const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

let I = 0;
let answer = [];

while (I < input.length - 1) {
  const [w, h] = input[I].split(" ").map(Number);
  let global = input
    .slice(I + 1, I + h + 1)
    .map((row) => row.split(" ").map(Number));
  let visited = Array.from({ length: h }, () => Array(w).fill(false));
  const ds = [
    [-1, 0],
    [1, 0],
    [0, 1],
    [0, -1],
    [1, 1],
    [1, -1],
    [-1, 1],
    [-1, -1],
  ];
  let count = 0;

  for (let i = 0; i < h; i++) {
    for (let j = 0; j < w; j++) {
      if (global[i][j] === 1 && !visited[i][j]) {
        bfs(j, i);
        count++;
      }
    }
  }

  function bfs(startX, startY) {
    const q = [[startX, startY]];
    let count = 1;

    while (q.length) {
      const [x, y] = q.shift();
      for (let i = 0; i < ds.length; i++) {
        const X = x + ds[i][0];
        const Y = y + ds[i][1];
        if (
          X >= 0 &&
          Y >= 0 &&
          X < w &&
          Y < h &&
          !visited[Y][X] &&
          global[Y][X] === 1
        ) {
          visited[Y][X] = true;
          count++;
          q.push([X, Y]);
        }
      }
    }

    return count;
  }

  answer.push(count);
  I = I + h + 1;
}

console.log(answer.join("\n"));
