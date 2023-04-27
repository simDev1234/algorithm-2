const fs = require("fs");
const input = fs.readFileSync("example.txt").toString().trim().split("\n");
const gears = input.slice(0, 4).map((v) => v.split("").map(Number));
const commands = input.slice(5).map((v) => v.split(" ").map(Number));

const solution = (gears, commands) => {
  gears.unshift(null);

  commands.forEach(([gearIdx, rotateDirection]) => {
    rotate(gears, gearIdx, rotateDirection);
  });
  return calcScore(gears);
};

const calcScore = (gears) => {
  let score = 0;
  for (let i = 1; i <= 4; i++) {
    score += gears[i][0] === 1 ? 2 ** (i - 1) : 0;
  }
  return score;
};

const changeStatus = (rotateDirection, gears, idx, status, check) => {
  check[idx] = true;
  status[idx] = rotateDirection;

  if (idx - 1 >= 1) {
    if (!check[idx - 1] && gears[idx][6] !== gears[idx - 1][2]) {
      changeStatus(-rotateDirection, gears, idx - 1, status, check);
    }
  }

  if (idx + 1 <= 4) {
    if (!check[idx + 1] && gears[idx][2] !== gears[idx + 1][6]) {
      changeStatus(-rotateDirection, gears, idx + 1, status, check);
    }
  }
};

const rotate = (gears, gearIdx, rotateDirection) => {
  const check = new Array(5).fill(false);
  const status = new Array(5).fill(0);
  changeStatus(rotateDirection, gears, gearIdx, status, check);

  for (let i = 1; i <= 4; i++) {
    if (status[i] === 0) continue;
    else if (status[i] === 1) {
      const last = gears[i].pop();
      gears[i].unshift(last);
    } else {
      const first = gears[i].shift();
      gears[i].push(first);
    }
  }
};

console.log(solution(gears, commands));
