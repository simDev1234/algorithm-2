const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input.shift();
const K = +input.pop();
const energyList = input.map((row) => row.split(" ").map(Number));
energyList.unshift(null);

const bfs = () => {
  const q = [[1, 0, true]];
  const answer = [];
  while (q.length) {
    const [pos, energy, chance] = q.shift();
    if (pos > N) continue;
    if (pos === N) {
      answer.push(energy);
      continue;
    }
    if (pos + 1 <= N) {
      q.push([pos + 1, energy + energyList[pos][0], chance]);
    }
    if (pos + 2 <= N) {
      q.push([pos + 2, energy + energyList[pos][1], chance]);
    }
    if (chance && pos + 3 <= N) {
      q.push([pos + 3, energy + K, !chance]);
    }
  }
  return answer;
};
const list = bfs();
console.log(Math.min(...list));
