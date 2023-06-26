const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [L, C] = input.shift().split(" ").map(Number);
const str = input.shift().split(" ");
const A = []; // 모음 담는 배열
const B = []; // 자음 담는 배열
const answer = [];

str.forEach((x) => {
  if (x === "a" || x === "e" || x === "i" || x === "o" || x === "u") A.push(x);
  else B.push(x);
});

for (let i = 1; i <= A.length; i++) {
  let j = L - i;
  if (j >= 2 && j <= B.length) {
    const SX = pick(A, i);
    const SY = pick(B, j);

    for (let i = 0; i < SX.length; i++) {
      for (let j = 0; j < SY.length; j++) {
        const S = [...SX[i], ...SY[j]].sort().join("");
        answer.push(S);
      }
    }
  }
}

// 배열에서 중복 없이 n개의 수를 고름 ex) ['a','i']
function pick(arr, num) {
  const result = [];
  if (num === 1) return arr.map((el) => [el]);
  arr.forEach((fixed, index, origin) => {
    const rest = origin.slice(index + 1);
    const combinations = pick(rest, num - 1);
    const attached = combinations.map((el) => [fixed, ...el]);
    result.push(...attached);
  });
  return result;
}

console.log(answer.sort().join("\n"));

// 최소 한 개의 모음, 최소 2개의 자음
// 알파벳이 증가하는 순서
