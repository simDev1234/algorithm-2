const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim();

const S = +input;

function bfs(end) {
  const q = [[1, 0, 0]];
  let visited = Array.from({ length: 1001 }, () => new Array(1001).fill(false));
  visited[1][0] = true;
  while (q.length) {
    const [screen, clipboard, time] = q.shift();
    if (screen === end) return time;
    if (screen <= 0 || screen > 1000) continue;

    if (!visited[screen][screen]) {
      visited[screen][screen] = true;
      q.push([screen, screen, time + 1]);
    }

    if (
      clipboard > 0 &&
      screen + clipboard <= 1000 &&
      !visited[screen + clipboard][clipboard]
    ) {
      visited[screen + clipboard][clipboard] = true;
      q.push([screen + clipboard, clipboard, time + 1]);
    }
    if (!visited[screen - 1][clipboard]) {
      visited[screen - 1][clipboard] = true;
      q.push([screen - 1, clipboard, time + 1]);
    }
  }
}

const answer = bfs(S);
console.log(answer);
