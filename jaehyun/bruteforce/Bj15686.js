const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

let n = +input.shift();
let arr = input.map((row) => row.split(" ").map(Number));
const visited = [];
const output = [];
let result = +Infinity;
let count = 0;

function dfs(cnt) {
  if (cnt === n) {
    let temp = 0;
    for (let i = 1; i < output.length; i++) {
      temp += arr[output[i - 1]][output[i]];
    }
    if (arr[output[output.length - 1]][output[0]] !== 0) {
      temp += arr[output[output.length - 1]][output[0]];
      console.log(temp);
    } else {
      temp = Infinity;
    }

    result = Math.min(result, temp);
    return;
  }
  for (let i = 0; i < arr.length; i++) {
    if (visited[i]) continue;
    if (output.length > 0 && arr[output[output.length - 1]][i] === 0) continue;
    visited[i] = true;
    output.push(i);
    dfs(cnt + 1);
    output.pop();
    visited[i] = false;
  }
}

dfs(count);
console.log(result);
