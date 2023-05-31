const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [N, M] = input[0].split(" ").map(Number);
const cakeLengthArr = input[1]
  .split(" ")
  .map(Number)
  .sort((a, b) => {
    if (a % 10 === 0 && b % 10 === 0) {
      if (a < b) return -1;
      else if (a > b) return 1;
      return 0;
    } else if (a % 10 === 0 && b % 10 !== 0) {
      return -1;
    } else if (a % 10 !== 0 && b % 10 === 0) {
      return 1;
    } else if (a % 10 !== 0 && b % 10 !== 0) {
      if (a < b) return -1;
      else if (a > b) return 1;
      return 0;
    }
  });

let restCount = M;
let count = 0;

for (let i = 0; i < N; i++) {
  const tempL = cakeLengthArr[i];
  const tempMock = Math.ceil(tempL / 10 - 1);
  const tempRest = tempL % 10;
  if (restCount < tempMock) {
    count += restCount;
    break;
  }
  restCount -= tempMock;
  count += tempRest === 0 ? tempMock + 1 : tempMock;
}

console.log(count);
