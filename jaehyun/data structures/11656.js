const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim();

let q = [];
for (let i = 0; i < input.length; i++) {
  q.push(input.slice(i));
}

console.log(q.sort().join("\n"));
