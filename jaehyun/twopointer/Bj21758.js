const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];
const honey = input[1].split(" ").map(Number);

let max = -Infinity;
const totalSum = honey.reduce((a, c) => a + c, 0);

for (let i = 0; i < N; i++) {
  if (i === N - 1) {
    let sum = totalSum - honey[0];
    let rightSum = totalSum - honey[0];

    for (let j = 1; j < N - 1; j++) {
      let leftSum = sum - honey[j];
      rightSum -= honey[j];
      max = Math.max(leftSum + rightSum, max);
    }
  } else if (i === 0) {
    let sum = totalSum - honey[N - 1];
    let leftSum = totalSum - honey[N - 1];

    for (let j = N - 2; j >= 1; j--) {
      let rightSum = sum - honey[j];
      leftSum -= honey[j];
      max = Math.max(leftSum + rightSum, max);
    }
  } else {
    let sum = totalSum - honey[0] - honey[N - 1] + honey[i];
    max = Math.max(sum, max);
  }
}

console.log(max);
