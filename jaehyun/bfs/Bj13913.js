const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim();

const sol = (input) => {
  const [N, K] = input.split(" ").map(Number);
  const path = Array.from({ length: 100100 }, () => 0);
  const visit = Array.from({ length: 100100 }, () => 0);

  function bfs(N) {
    const queue = [];
    queue.push([N, 0]);
    visit[N] = 1;
    while (queue.length) {
      const [cur, time] = queue.shift();
      if (cur === K) return time;
      for (next of [cur - 1, cur + 1, cur * 2]) {
        if (!visit[next] && next >= 0 && next <= 100000) {
          path[next] = cur;
          visit[next] = 1;
          queue.push([next, time + 1]);
        }
      }
    }
  }

  const time = bfs(N);
  const order = [];
  order.push(K);
  let prev = path[K];
  for (let i = 0; i < time; i++) {
    order.push(prev);
    prev = path[prev];
  }
  const result = `${time}\n${order.reverse().join(" ")}`;
  return result;
};

console.log(sol(input));
