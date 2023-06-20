const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "./example.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");

const [N, M] = input[0].split(" ").map(Number);
const baskets = [];

const ds = [
  undefined,
  [-1, 0],
  [-1, -1],
  [0, -1],
  [1, -1],
  [1, 0],
  [1, 1],
  [0, 1],
  [-1, 1],
];

const diagonal_ds = [
  [-1, -1],
  [-1, 1],
  [1, -1],
  [1, 1],
];

for (let i = 1; i <= N; i++) {
  baskets.push(input[i].split(" ").map(Number));
}

let cloud;

function move(curr, d, s) {
  const X = (curr[0] + ds[d][0] * s + N * 50) % N;
  const Y = (curr[1] + ds[d][1] * s + N * 50) % N;
  return [X, Y];
}

for (let i = N + 1; i < N + M + 1; i++) {
  const [d, s] = input[i].split(" ").map(Number);
  const cloud_visited = Array.from({ length: N }, () => Array(N).fill(false));
  if (i === N + 1) {
    cloud = [
      [0, N - 1],
      [1, N - 1],
      [0, N - 2],
      [1, N - 2],
    ];
  }
  const moved_cloud = cloud.map((pos) => move(pos, d, s));
  moved_cloud.forEach(([X, Y]) => {
    baskets[Y][X]++;
    cloud_visited[Y][X] = true;
  });
  moved_cloud.forEach(([X, Y]) => {
    for (let j = 0; j < 4; j++) {
      const dx = X + diagonal_ds[j][0];
      const dy = Y + diagonal_ds[j][1];
      if (dx >= 0 && dy >= 0 && dx < N && dy < N && baskets[dy][dx] > 0) {
        baskets[Y][X]++;
      }
    }
  });

  cloud = [];

  for (let Y = 0; Y < N; Y++) {
    for (let X = 0; X < N; X++) {
      if (!cloud_visited[Y][X] && baskets[Y][X] >= 2) {
        cloud.push([X, Y]);
        baskets[Y][X]--;
        baskets[Y][X]--;
      }
    }
  }
}

let sum = 0;

for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    sum += baskets[j][i];
  }
}

console.log(sum);
