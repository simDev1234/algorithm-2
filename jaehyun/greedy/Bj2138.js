const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];
const before = input[1].split("");
const after = input[2].split("");
let cnt;
let ans = +Infinity;

function lightOn(idx, temp) {
  if (idx > 0) temp[idx - 1] = temp[idx - 1] == "0" ? "1" : "0";
  temp[idx] = temp[idx] == "0" ? "1" : "0";
  if (idx < N - 1) temp[idx + 1] = temp[idx + 1] == "0" ? "1" : "0";
}

function solve(startIdx) {
  const temp = [...before];
  cnt = 0;
  if (startIdx === 0) {
    temp[0] = temp[0] == "0" ? "1" : "0";
    temp[1] = temp[1] == "0" ? "1" : "0";
    cnt++;
  }
  for (let i = 1; i < N; i++) {
    if (temp[i - 1] !== after[i - 1]) {
      lightOn(i, temp);
      cnt++;
    }
  }

  if (temp.join("") === after.join("")) ans = Math.min(ans, cnt);
}

solve(0);
solve(1);
console.log(ans === Infinity ? -1 : ans);
