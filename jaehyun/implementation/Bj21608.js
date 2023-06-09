const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input.shift();
const M = new Map();
const students = [];
const board = Array.from({ length: N }, () => Array(N).fill(0));

input.forEach((str) => {
  const [num, ...restArr] = str.split(" ").map(Number);
  M.set(num, restArr);
  students.push(num);
});
const dist = [
  [-1, 0],
  [1, 0],
  [0, 1],
  [0, -1],
];
const isInMap = (x, y) => x >= 0 && y >= 0 && y < N && x < N;

// 각 좌표마다 우선순위 칸의 수, 빈 칸의 수를 구해 배열로 저장하고
// 이 배열을 key로 좌표를 value로 하여 객체에 모아놓는다 (JSOn.stringify)

// board배열 만들기
for (let student of students) {
  let candidates = {};
  for (let row = 0; row < N; row++) {
    for (let col = 0; col < N; col++) {
      if (board[row][col] !== 0) continue;
      let count = [0, 0]; // 우선 순위 수, 비어있는 칸 수
      for (let i = 0; i < 4; i++) {
        const [nRow, nCol] = [row + dist[i][0], col + dist[i][1]];
        if (!isInMap(nRow, nCol)) continue;
        if (M.get(student).includes(board[nRow][nCol])) count[0]++; // 좋아하는 학생의 수 카운트
        if (!board[nRow][nCol]) count[1]++; // 비어있는 칸의 수 카운팅
      }
      const key = JSON.stringify(count);
      if (!candidates[key]) candidates[key] = [[row, col]];
      else candidates[key].push([row, col]); // 1,2 만족한다면 push를 이용하면 row, col이
    }
  }
  const sorted = Object.keys(candidates).sort((a, b) => {
    const [a0, a1] = JSON.parse(a);
    const [b0, b1] = JSON.parse(b);
    if (a0 !== b0) return b0 - a0;
    return b1 - a1;
  }); // 1번 좋아하는 칸 많은 순, 2번  비어있는 칸 순으로 정렬
  const [newRow, newCol] = candidates[sorted[0]][0];
  board[newRow][newCol] = student;
}

// 계산하는 함수
function calSum(arr) {
  let sum = 0;
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      let count = 0;
      for (let k = 0; k < 4; k++) {
        const X = i + dist[k][0];
        const Y = j + dist[k][1];
        if (isInMap(X, Y)) {
          const num = arr[i][j];
          const closeNum = arr[X][Y];
          if (M.get(num).includes(closeNum)) count++;
        }
      }
      sum += count !== 0 ? Math.pow(10, count - 1) : 0;
    }
  }
  return sum;
}

const result = calSum(board);
console.log(result);

