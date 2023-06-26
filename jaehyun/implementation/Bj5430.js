const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const tc = +input.shift();
for (let i = 0; i < tc; i++) {
  const func = input.shift();
  const arrNum = +input.shift();
  let arr;

  if (arrNum) {
    arr = input.shift().trim().split("");
    arr.pop();
    arr.shift();
    arr = arr.join("").split(",").map(Number);
  } else {
    input.shift();
    arr = [];
  }

  let curReverse = 0;
  let isError = false;

  for (let j = 0; j < func.length; j++) {
    if (func[j] === "R") {
      curReverse += 1;
      continue;
    }

    if (func[j] === "D") {
      if (!arr.length) {
        console.log("error");
        isError = true;
        break;
      }
      if (curReverse % 2) {
        arr.pop();
      } else {
        arr.shift();
      }
    }
  }

  if (!isError) {
    if (curReverse % 2) {
      console.log(`[${arr.reverse().join(",")}]`);
    } else {
      console.log(`[${arr.join(",")}]`);
    }
  }
}
