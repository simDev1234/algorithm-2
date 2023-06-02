const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [S, T] = input;

let answer = 0;

function bfs() {
  const q = [[S, false]];
  while (q.length) {
    let [str, hasTurned] = q.shift();
    if (str.length === T.length) {
      if (hasTurned) str = str.split("").reverse("").join("");
      if (str === T) {
        answer = 1;
        break;
      }
      continue;
    }
    if (str.length > T.length) continue;
    if (!hasTurned) {
      q.push([str + "A", hasTurned]);
      q.push(["B" + str, !hasTurned]);
    } else {
      q.push(["A" + str, hasTurned]);
      q.push([str + "B", !hasTurned]);
    }
  }
}

bfs();
console.log(answer);
