function solution(number, k) {
  const numArr = number.split(""); // ["1", "9", "2", "4"]
  const stack = [];

  for (let idx in numArr) {
    // 스택에 있는 마지막 숫자가 스택에 넣으려는 숫자보다 작은 경우
    // 스택에 있는 마지막 숫자를 꺼내서 제거
    while (stack.length > 0 && stack[stack.length - 1] < numArr[idx]) {
      // console.log(stack[stack.length - 1], numArr[idx])
      if (k === 0) {
        // 제거할 수 있는 개수가 모두 소진됨 (k=0)
        // 더이상 숫자 제거할 수 없음
        break;
      } else {
        stack.pop();
        // 숫자 하나가 제거됐을 때
        // 제거할 수 있는 개수(k=2)에서 1씩 빼준다.
        k--;
      }
    }

    // [while 반복문 조건에 해당하지 않는 경우]
    // 스택에 아무것도 안 담겨있거나 or
    // 스택에 있는 숫자보다 스택에 넣으려는 숫자가 작거나 같은 경우
    // 그냥 스택에 담는다.
    stack.push(numArr[idx]);
  }

  // 넣으려는 숫자가 스택에 있는 숫자보다 작으면
  // 그냥 계속 담기므로 출력해야 하는 만큼 잘라서 사용
  stack.splice(stack.length - k, k);
  // 스택에 담긴 두 숫자를 합쳐 문자열로 만든다.
  // ["9", "4"] -> "94"
  let answer = stack.join("");
  return answer; // "94"
}
