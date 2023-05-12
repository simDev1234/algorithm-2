const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [n, k] = input[0].split(" ").map(Number);
const inner = input[1].split(" ").map(Number);

function solution(N, M, inner) {
  let nums = inner.join("");
  const target = inner
    .slice()
    .sort((a, b) => a - b)
    .join("");

  const visitied = new Set();
  const que = [[nums, 0]];
  let answer = -1;

  while (que.length !== 0) {
    const [num, cnt] = que.shift();

    if (num === target) {
      answer = cnt;
      break;
    }

    for (let i = 0; i <= N - M; i++) {
      const temp = reverse(num, i);
      if (visitied.has(temp)) continue;
      visitied.add(temp);
      que.push([temp, cnt + 1]);
    }
  }
  return answer;

  function reverse(str, index) {
    let ret = "";

    for (let i = 0; i < index; i++) {
      ret += str[i];
    }

    for (let i = index + M - 1; i >= index; i--) {
      ret += str[i];
    }

    for (let i = index + M; i < N; i++) {
      ret += str[i];
    }

    return ret;
  }
}

console.log(solution(n, k, inner));
