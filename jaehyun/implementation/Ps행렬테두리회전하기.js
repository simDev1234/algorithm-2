function solution(rows, columns, queries) {
  var answer = [];
  let arr = Array.from({ length: rows }, () => Array(columns).fill(0));
  for (let i = 0; i < rows; i++) {
    for (let j = 1; j <= columns; j++) {
      const num = i * rows + j;
      arr[i][j - 1] = num;
    }
  }

  queries.forEach(([a, b, c, d]) => {
    answer.push(rotate(a, b, c, d));
  });
  function rotate(a, b, c, d) {
    let min = Number.MIN_SAFE_INTEGER;
    return min;
  }
  return arr;
}

console.log(
  solution(6, 6, [
    [2, 2, 5, 4],
    [3, 3, 6, 6],
    [5, 1, 6, 3],
  ])
);
