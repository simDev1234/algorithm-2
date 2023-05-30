const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];
const courses = input.slice(1).map((row) => row.split(" ").map(Number));

let answer = 0;
let classroom = 0;
const obj = [];

for (let i = 0; i < N; i++) {
  obj.push({ time: courses[i][0], start: 1 });
  obj.push({ time: courses[i][1], start: -1 });
}

obj.sort((a, b) => (a.time === b.time ? a.start - b.start : a.time - b.time));
obj.forEach((schedule) => {
  if (schedule.start === -1) {
    classroom -= 1;
  } else if (schedule.start === 1) {
    classroom += 1;
  }

  answer = Math.max(classroom, answer);
});

console.log(answer);
