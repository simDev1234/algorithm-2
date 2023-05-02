const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];
const map = input[1].replace("\r", "").split("");

// 최소 몇개의 칸에 선물을 놓아야 하는지 구하라
// E에서 W로 변하는 구간을 찾으면 되는건가
function solution(N, MAP) {
  let count = 0;
  for (let i = 0; i < MAP.length; i++) {
    if (MAP[i] === "W" && i <= MAP.length - 2 && MAP[i + 1] === "E") count++;
  }
  return count + 1;
}

console.log(solution(N, map));
