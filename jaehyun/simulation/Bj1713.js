const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input.shift();
const RC = +input.shift();
const R_students = input.shift().split(" ").map(Number);

function solution(frame, n, arr) {
  let stack = [];
  let cnt = 0; // 인덱스
  let cursor = []; //인덱스를 담는 체크배열
  let count = parseInt(n / frame);

  for (let j = 0; j < count; j++) {
    for (let i = 0; i < n; i++) {
      let peo = Number(arr[i]);
      if (stack.length < 3) {
        stack.push(peo);
        cursor.push(cnt++); //액자 개수만큼 돌았다
      } else {
        //똑같은게 있으면
        if (stack.some((item) => item === peo)) {
          stack[stack.indexOf(peo)] = peo; //해당 아이템있는 자리에 덮어씌움
          cursor[stack.indexOf(peo)] = cnt++; //cnt도 해당아이템이 있는 인덱스에 덮어씌움
        } else {
          //만약 똑같은게 없으면
          let min = Math.min(...cursor); //cnt가 젤 작은 곳
          stack[cursor.indexOf(min)] = peo; //cnt가 젤 작은 곳에 추가함
          cursor[cursor.indexOf(min)] = cnt++; //cnt도 같이 덮어씌움
        }
      }
    }
  }
  const returnArr = stack.sort((a, b) => a - b);
  return returnArr.join(" ");
}

console.log(solution(N, RC, R_students));
