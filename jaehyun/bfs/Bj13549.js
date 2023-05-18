const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim();

const [n, k] = input.split(" ").map(Number);

function bfs(start, end) {
  const q = [[start, 0]];
  let visited = Array(100001).fill(false);
  visited[start] = true;
  while (q.length) {
    const [now, time] = q.shift();
    if (now === end) return time;
    if (now < 0 || now > 100001) continue;

    if (!visited[2 * now]) {
      visited[2 * now] = true;
      q.unshift([2 * now, time]);
    }

    for (let X of [now - 1, now + 1]) {
      if (!visited[X]) {
        visited[X] = true;
        q.push([X, time + 1]);
      }
    }
  }
}

const answer = bfs(n, k);
console.log(answer);
