const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");
const [N, M] = input.shift().split(" ").map(Number);

const edges = input.map((v) => v.split(" ").map(Number));
const graph = Array.from({ length: N + 1 }, () => []);
edges.forEach(([from, to]) => {
  graph[from].push(to);
  graph[to].push(from);
});
const visited = Array(N + 1).fill(false);

const dfs = (start) => {
  const stack = [start];
  const order = [];
  while (stack.length) {
    const node = stack.pop();
    if (!visited[node]) {
      visited[node] = true;
      stack.push(...graph[node]);
      order.push(node);
    }
  }
  return order.sort((a, b) => a - b).join(" ");
};

let count = 0;
for (let i = 1; i <= N; i++) {
  const str = dfs(i);
  if (str.length) count++;
}

console.log(count);
