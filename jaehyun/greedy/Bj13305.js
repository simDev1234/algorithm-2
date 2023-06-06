const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = +input[0];
const distArr = input[1].split(" ").map(BigInt);
const price = input[2].split(" ").map(BigInt);

let curPrice = price[0];
let cost = 0n;

for (let i = 0; i < N - 1; i++) {
  cost += curPrice * distArr[i];
  if (curPrice > price[i + 1]) curPrice = price[i + 1];
}
// 가장 최저가인 곳에서 기름을 구매해서 제일 오른쪽 도시까지 가면 된다
// 매 도시를 방문할 때마다 최저가를 갱신하면 된다
// 최저가인 지점까지 도착하기 전까지는 그 전에 있던 도시에서 기름을 구매

console.log(String(cost));
