const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = input.length;

const treeMap = new Map();
input.forEach((tree) => {
  treeMap.set(tree, treeMap.get(tree) + 1 || 1);
});

const arr = Array.from(treeMap.keys()).sort();
arr.forEach((elem) => {
  const val = ((treeMap.get(elem) / N) * 100).toFixed(4);
  console.log(elem, val);
});
