const fs = require("fs");
const input = fs.readFileSync("example.txt").toString().trim().split("\n");

const N = input[0];
const arr = input[2].split(" ").map((v) => +v);
const stack = [];
const num = [];

arr.forEach((v) => {
  const index = stack.indexOf(v);
  if (index > -1) {
    num[index]++;
  } else if (stack.length < N) {
    stack.push(v);
    num.push(1);
  } else {
    const min = Math.min(...num);
    const j = num.indexOf(min);
    num.splice(j, 1);
    stack.splice(j, 1);
    num.push(1);
    stack.push(v);
  }
});

console.log(stack.sort((a, b) => a - b).join(" "));
