const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [S, E, Q] = input.shift().split(" ");

function compare(a, b) {
  const [preH, preM] = a.split(":").map(Number);
  const [afterH, afterM] = b.split(":").map(Number);

  const valueA = preH * 60 + preM;
  const valueB = afterH * 60 + afterM;
  if (valueA > valueB) return 1;
  else if (valueA === valueB) return 0;
  else return -1;
}

const m = new Map();
let count = 0;

for (let i = 0; i < input.length; i++) {
  const [time, student] = input[i].split(" ");
  const compareValue1 = compare(S, time);
  const compareValue2 = compare(time, E);
  const compareValue3 = compare(Q, time);

  if (compareValue3 < 0) break;
  if (compareValue1 >= 0) {
    if (!m.get(student)) {
      m.set(student, time);
    }
    continue;
  }
  if (compareValue2 >= 0 && compareValue3 >= 0) {
    if (m.get(student)) {
      m.delete(student);
      count++;
    }
  }
}

console.log(count);
