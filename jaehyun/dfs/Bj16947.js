const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

// 사이클에 해당하는 노드들을 구함 => dfs
// 모든 노들을 조회하면서 사이클까지의 최단거리를 구함 => bfs

const N = +input[0];
const noseon = input.slice(1).map((row) => row.split(" ").map(Number));
const graph = Array.from({ length: N + 1 }, () => []);
noseon.forEach(([from, to]) => {
  graph[from].push(to);
  graph[to].push(from);
});

const check = new Array(N + 1).fill(0);
let flag = 0;
let cycle;
let start;

function dfs(L, idx) {
  if (flag) return;
  for (let x of graph[idx]) {
    if (!check[x]) {
      check[x] = 1;
      dfs(L + 1, x);
      check[x] = 0;
    } else if (L >= 3 && x === start) {
      flag = 1;
      cycle = [...check];
      return;
    }
  }
}

for (let i = 1; i <= N; i++) {
  start = i;
  check[i] = 1;
  dfs(1, i);
  check[i] = 0;
  if (flag) break;
}

function bfs(i) {
  const q = [];
  q.push(i);
  let dist = 0;
  const check2 = new Array(N + 1).fill(0);
  check2[i] = 1;

  while (true) {
    dist++;
    const iterator = q.length;
    for (let i = 0; i < iterator; i++) {
      const from = q.shift();
      for (let to of graph[from]) {
        if (cycle[to]) return dist;
        if (!check2[to]) {
          check2[to] = 1;
          q.push(to);
        }
      }
    }
  }
}

let result = [];
for (let i = 1; i <= N; i++) {
  if (cycle[i]) result.push(0);
  else result.push(bfs(i));
}

console.log(result.join(" "));
