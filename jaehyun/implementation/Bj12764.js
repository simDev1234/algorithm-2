const fs = require("fs");
const input = fs.readFileSync("example.txt").toString().split("\n");

class Heap {
  constructor(e) {
    this.heap = [-1, ...(e || [])];
  }

  push(e) {
    let heap = this.heap;
    heap.push(e);
    let idx = heap.length - 1;
    while (parseInt(idx / 2) > 0) {
      const p = parseInt(idx / 2);
      if (heap[idx] < heap[p]) [heap[idx], heap[p]] = [heap[p], heap[idx]];
      else break;
      idx = p;
    }
  }

  pop() {
    let heap = this.heap;
    if (heap.length > 1) {
      [heap[1], heap[heap.length - 1]] = [heap[heap.length - 1], heap[1]];
      const e = heap.pop();
      let idx = 1;
      while (idx * 2 < heap.length) {
        let child = idx;
        let left = idx * 2;
        let right = idx * 2 + 1;
        if (heap[left] < heap[idx]) child = left;
        if (right < heap.length && heap[right] < heap[child]) child = right;
        if (child === idx) break;
        [heap[idx], heap[child]] = [heap[child], heap[idx]];
        idx = child;
      }
      return e;
    }
    return -1;
  }

  peek() {
    let heap = this.heap;
    if (heap.length > 1) return heap[1];
    return -1;
  }

  length() {
    return this.heap.length - 1;
  }
}

function solution() {
  const N = Number(input[0]);
  let times = [];
  for (let i = 1; i <= N; i++) times.push(input[i].split(" ").map(Number));
  times.sort((a, b) => a[0] - b[0]); // 입실시간이 짧은 순서대로 정렬
  let ends = new Heap();
  let used = [0];
  let available = new Heap([0]);
  let idxMap = new Map();
  let cnt = 1;
  for (const [P, Q] of times) {
    while (ends.length() && ends.peek() <= P) {
      // 퇴실시간 넣은 것들의 heap에서 제일 퇴실시간 작은 게 P 이전에 끝난다면
      const t = ends.pop();
      available.push(idxMap.get(t)); // 사용 가능한 컴퓨터 인덱스를 available heap에 대입
    }
    if (available.length()) {
      // 사용 가능한 컴퓨터 자리가 존재한다면
      const idx = available.pop();
      ends.push(Q);
      idxMap.set(Q, idx);
      used[idx] += 1; // 해당 idx에 값 추가하기
    } else {
      // 사용가능한 컴퓨터 자리가 존재 X
      ends.push(Q);
      idxMap.set(Q, cnt);
      used.push(1);
      cnt += 1; // 사용가능한 컴퓨터 자리가 없으므로 cnt 추가
    }
  }
  console.log(cnt);
  console.log(used.join(" "));
}

solution();
