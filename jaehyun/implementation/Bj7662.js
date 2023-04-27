const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const T = +input.shift();
const testData = Array.from({ length: T + 1 }, () => []);
const testDataNum = [];

let N;
let idx = 0;

while (input.length) {
  let X = input.shift();
  if (!isNaN(X)) {
    N = Number(X);
    testDataNum.push(N);
    idx++;
  } else {
    const [str, num] = X.split(" ");
    testData[idx].push([str, Number(num)]);
  }
}

class DblEndedPQ {
  constructor() {
    this.s = new Set();
  }

  control([str, num]) {
    if (str === "I") {
      this.insert(num);
    } else if (str === "D") {
      if (this.isEmpty()) return;
      num === 1 ? this.deleteMax() : this.deleteMin();
    }
  }

  size() {
    return this.s.size;
  }

  isEmpty() {
    return this.s.size === 0;
  }

  insert(x) {
    this.s.add(x);
  }

  getMin() {
    return Math.min(...Array.from(this.s.values()));
  }

  getMax() {
    return Math.max(...Array.from(this.s.values()));
  }

  deleteMin() {
    if (this.s.size == 0) return;
    this.s.delete(this.getMin());
  }

  deleteMax() {
    if (this.s.size == 0) return;
    this.s.delete(this.getMax());
  }
}

let answer = [];

for (let i = 0; i < T; i++) {
  const dblEndedQ = new DblEndedPQ();
  testData[i + 1].forEach(([str, num]) => {
    dblEndedQ.control([str, num]);
  });
  if (dblEndedQ.isEmpty()) answer.push("EMPTY");
  else {
    const min = dblEndedQ.getMin();
    const max = dblEndedQ.getMax();
    answer.push(`${max} ${min}`);
  }
}

console.log(answer.join("\n"));
