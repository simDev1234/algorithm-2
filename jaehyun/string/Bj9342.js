const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];
for (let i = 1; i <= N; i++) {
  const str = input[i];
  const regex = /^[A-F]?A+F+C+[A-F]?$/;
  console.log(str.match(regex) ? "Infected!" : "Good");
}
