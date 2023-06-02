const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [S, T] = input;
let deque = input[1].split("");
let dir = "left";

for (let i = 0; i < T.length - S.length; i++) {
  if (dir === "left") {
    let check = deque.pop();
    if (check === "B") {
      dir = "right";
    }
  } else {
    let check = deque.shift();
    if (check === "B") {
      dir = "left";
    }
  }
}
if (dir === "right") {
  deque.reverse();
}
if (deque.join("") === S) {
  console.log(1);
} else {
  console.log(0);
}