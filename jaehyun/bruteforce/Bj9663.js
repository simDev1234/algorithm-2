const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim();

function solution(n) {
  let answer = 0;

  const dfs = (board, row) => {
    if (row === n) answer++;
    else {
      for (let i = 1; i <= n; i++) {
        board[row + 1] = i; // row+1 행 i 열에 퀸을 위치했을 때
        if (isValid(board, row + 1)) dfs(board, row + 1);
      }
    }
  };

  const isValid = (board, row) => {
    for (let i = 1; i < row; i++) {
      if (board[i] === board[row]) return false; // 같은 열에 위치할 때
      //
      if (Math.abs(board[i] - board[row]) === Math.abs(i - row)) return false; // 대각선에 겹칠 때
    }
    return true;
  };

  for (let i = 1; i <= n; i++) {
    const board = new Array(n + 1).fill(0);
    board[1] = i;
    dfs(board, 1);
  }

  return answer;
}

console.log(solution(+input));
