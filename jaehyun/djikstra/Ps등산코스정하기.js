class PriorityQueue {
  constructor() {
    this.values = [];
  }
  enqueue(val, priority) {
    this.values.push({ val, priority });
    this.bubbleUp();
  }
  bubbleUp() {
    let idx = this.values.length - 1;
    const element = this.values[idx];
    while (idx > 0) {
      let parentIdx = Math.floor((idx - 1) / 2);
      let parent = this.values[parentIdx];
      if (element.priority >= parent.priority) break;
      this.values[parentIdx] = element;
      this.values[idx] = parent;
      idx = parentIdx;
    }
  }
  dequeue() {
    const min = this.values[0];
    const end = this.values.pop();
    if (this.values.length > 0) {
      this.values[0] = end;
      this.sinkDown();
    }
    return min;
  }
  sinkDown() {
    let idx = 0;
    const length = this.values.length;
    const element = this.values[0];
    while (true) {
      let leftChildIdx = 2 * idx + 1;
      let rightChildIdx = 2 * idx + 2;
      let leftChild, rightChild;
      let swap = null;

      if (leftChildIdx < length) {
        leftChild = this.values[leftChildIdx];
        if (leftChild.priority < element.priority) {
          swap = leftChildIdx;
        }
      }
      if (rightChildIdx < length) {
        rightChild = this.values[rightChildIdx];
        if (
          (swap === null && rightChild.priority < element.priority) ||
          (swap !== null && rightChild.priority < leftChild.priority)
        ) {
          swap = rightChildIdx;
        }
      }
      if (swap === null) break;
      this.values[idx] = this.values[swap];
      this.values[swap] = element;
      idx = swap;
    }
  }
}

function solution(n, paths, gates, summits) {
  const MAX = Number.MAX_SAFE_INTEGER;
  const answer = [n, MAX];
  const hikingTrail = Array.from({ length: n + 1 }, () => []);
  const isSummits = Array(n + 1).fill(false);
  summits.sort((a, b) => a - b);

  summits.forEach((summit) => {
    isSummits[summit] = true;
  });

  paths.forEach(([from, to, weight]) => {
    hikingTrail[from].push([to, weight]);
    hikingTrail[to].push([from, weight]);
  });

  function dijkstra() {
    const minHeap = new PriorityQueue();
    const intensity = Array(n + 1).fill(MAX);
    gates.forEach((gate) => {
      minHeap.enqueue(gate, 0);
      intensity[gate] = 0;
    });
    while (minHeap.values.length) {
      const { val: vertex, priority: cost } = minHeap.dequeue();
      if (intensity[vertex] < cost) continue;
      if (isSummits[vertex]) continue;
      const adjList = hikingTrail[vertex];
      const adjListLen = adjList.length;

      for (let i = 0; i < adjListLen; i++) {
        const [nextVertex, nextCost] = adjList[i];
        if (intensity[nextVertex] > Math.max(nextCost, intensity[vertex])) {
          intensity[nextVertex] = Math.max(intensity[vertex], nextCost);
          minHeap.enqueue(nextVertex, intensity[nextVertex]);
        }
      }
    }
    return intensity;
  }

  const intensity = dijkstra();

  summits.forEach((summit) => {
    if (intensity[summit] < answer[1]) {
      answer[0] = summit;
      answer[1] = intensity[summit];
    }
  });

  return answer;
}

console.log(
  solution(
    6,
    [
      [1, 2, 3],
      [2, 3, 5],
      [2, 4, 2],
      [2, 5, 4],
      [3, 4, 4],
      [4, 5, 3],
      [4, 6, 1],
      [5, 6, 1],
    ],
    [1, 3],
    [5]
  )
);
