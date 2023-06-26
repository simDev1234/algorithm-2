const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const [N, K] = input[0].split(" ").map(Number);
const bag = input.slice(1).map((row) => row.split(" ").map(Number));
// [W, V] => W값들을 다 더해서 k 이하로 맞춰야 하고, 그 경우의 수 중에서 V값의 최대를 구해야 한
// 물건을 배낭에 담거나 담지 않는 경우의 수는 2^N이지만 N이 클 때는 시간 내에 확인할 수 없다
// maxVSum[n][k] => n번째 물건들 중 최적으로 물건들을 골라 (무게 함 k이하이며 합이 최대인)
// 집합 A가 n번 물건을 포함한다면 A는 n-1번까지의 물건들 중에서 최적으로 고른 부분집합에 n번 물건을 포함한 것과 같다 =>  maxVSum[n-1][k-weight] + value)
// 집합 A가 n번 물건을 포함하지 않는다면 A는 n-1번까지의 물건들 중에서 최적으로 고른 부분집합과 같다 => maxVSum[n-1][k]

bag.unshift(undefined);
const maxVSum = [];
for (let i = 0; i <= N; i++) {
  maxVSum.push(Array(K + 1).fill(0));
}

for (let n = 1; n <= N; n++) {
  const [weight, value] = bag[n];
  for (let k = 0; k <= K; k++) {
    if (k < weight) {
      maxVSum[n][k] = maxVSum[n - 1][k];
    } else {
      maxVSum[n][k] = Math.max(
        maxVSum[n - 1][k],
        maxVSum[n - 1][k - weight] + value
      );
    }
  }
}

console.log(maxVSum[N][K]);
