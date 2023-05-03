const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [N, S] = input[0].split(" ").map(Number);
const bro = input[1].split(" ").map(Number);

let GCD = (num1, num2) => {
  let gcd = 1;

  for (let i = 2; i <= Math.min(num1, num2); i++) {
    if (num1 % i === 0 && num2 % i === 0) {
      gcd = i;
    }
  }

  return gcd;
};

const posDiff = bro.map((x) => Math.abs(x - S));

let d = posDiff[0];

for (let i = 0; i < posDiff.length; i++) {
  if (posDiff[i] % d === 0) continue;
  const a = GCD(posDiff[i], d);
  d = Math.min(a, d);
}

console.log(d);
