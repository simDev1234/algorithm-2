const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [[N, K], dolls] = input.map((row) => row.split(" ").map(Number));

let i = 0;
let j = 0;
let min = Infinity;
let count = 0;

while (j < N) {
  if (dolls[j] === 1) count++; // 끝 인덱스일 때마다 count값 증가
  while (count === K) {
    // count가 K일 때, 가장 가까운 라이언 인형의 인덱스까지 증가 시킨 후,
    // min 값 갱신
    if (dolls[i] === 1) count--;
    min = Math.min(min, j - i + 1);
    i++;
  }
  j++;
}

console.log(min === Infinity ? -1 : min);
