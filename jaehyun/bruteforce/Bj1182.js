const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [n, s] = input[0].split(" ").map(Number);
const numArr = input[1].split(" ").map(Number);

function btPowerSetRecursive(
  originalSet,
  allSubsets = [[]],
  currentSubSet = [],
  startAt = 0
) {
  for (let position = startAt; position < originalSet.length; position++) {
    currentSubSet.push(originalSet[position]);
    allSubsets.push([...currentSubSet]);
    btPowerSetRecursive(originalSet, allSubsets, currentSubSet, position + 1);
    currentSubSet.pop();
  }
  return allSubsets;
}

function sum(arr) {
  return arr.reduce((acc, curr) => acc + curr, 0);
}

const answer = btPowerSetRecursive(numArr).filter(
  (elem) => sum(elem) === s && elem.length >= 1
).length;
console.log(answer);
