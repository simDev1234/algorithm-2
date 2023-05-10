const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
let input = [];
let s = new Array(21).fill(false);
let result = [];
rl.on("line", function (line) {
  input.push(line);
}).on("close", async function () {
  // 답안 작성
  const n = input.shift() * 1;
  input.reduce((acc, cur) => {
    let line = cur.split(" ");
    bit_set(line[0], line[1] * 1);
  }, "");
  console.log(result.join("\n"));
  process.exit();
});

let bit_set = function (cmd, n) {
  switch (cmd) {
    case "add":
      s[n] = true;
      break;
    case "remove":
      s[n] = false;
      break;
    case "check":
      if (s[n]) result.push(1);
      else result.push(0);
      break;
    case "toggle":
      if (s[n]) s[n] = false;
      else s[n] = true;
      break;
    case "all":
      s = new Array(21).fill(true);
      break;
    case "empty":
      s = new Array(21).fill(false);
      break;
  }
};
