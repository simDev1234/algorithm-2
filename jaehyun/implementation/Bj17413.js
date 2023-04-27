const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "example.txt";
let input = fs.readFileSync(filePath).toString().trim();

// 문제 풀이 순서
// 문자열 S를 <> 나오는 부분은 잘라서 다른 배열에 저장하고,
// 그렇지 않은 부분은 따로 저장해서
// 단어들은 reverse를 이용하고 <>는 그대로 값을 사용한다
// 그 후 단어들을 모은 배열이랑 태그들만 담은 배열들을 for문을 통해 조합
// 왜냐하면 단어들을 모은 배열의 길이는 태그들만 담은 배열의 길이보다 1개 크기 때문이다

function reverseStr(str) {
  const arr = str.split(" ");
  let returnArr = [];
  arr.forEach((elem) => {
    returnArr.push(elem.split("").reverse("").join(""));
  });
  return returnArr.join(" ");
}

function solution(input) {
  let start = 0;
  let end = -1;
  let tagArr = [];
  let noTagArr = [];

  for (let i = 0; i < input.length; i++) {
    const str = input[i];
    if (str === "<") {
      let noTagStr = reverseStr(input.slice(end + 1, i));
      noTagArr.push(noTagStr);
      start = i;
    } else if (str === ">") {
      tagArr.push(input.slice(start, i + 1));
      end = i;
    }
  }

  let str = reverseStr(input.slice(end + 1));
  noTagArr.push(str);

  if (tagArr.length === 0) return noTagArr[0];

  let returnStr = "";

  for (let i = 0; i < tagArr.length; i++) {
    returnStr += noTagArr[i] + tagArr[i];
  }

  return returnStr + noTagArr[noTagArr.length - 1];
}

console.log(solution(input));
