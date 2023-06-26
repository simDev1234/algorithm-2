const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [R, S] = input[0].split(" ").map(Number);
const earth = input.slice(1).map((row) => row.split(""));

const max_meteor = Array(S).fill(-Infinity);
const min_ground = Array(S).fill(Infinity);

const reverseEarth = Array.from({ length: R }, () => Array(S).fill("."));
for (let x = 0; x < R; x++) {
  for (let y = 0; y < S; y++) {
    if (earth[x][y] === "X") {
      max_meteor[y] = Math.max(max_meteor[y], x);
    } else if (earth[x][y] === "#") {
      min_ground[y] = Math.min(min_ground[y], x);
    }
  }
}

let move = +Infinity;

for (let i = 0; i < S; i++) {
  move = Math.min(move, min_ground[i] - max_meteor[i]);
}

for (let x = 0; x < R; x++) {
  for (let y = 0; y < S; y++) {
    if (earth[x][y] === "X") reverseEarth[x + move - 1][y] = "X";
    if (earth[x][y] === "#") reverseEarth[x][y] = "#";
  }
}

const answer = reverseEarth.map((row) => row.join("")).join("\n");
console.log(answer);
