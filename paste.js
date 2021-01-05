var list = data.split(",");

function insertData(element, index, array) {
    if (element.tagName === "INPUT") {
        element.value = list[index];
    } else {
        var optionElement = Object.values(element.options).find(e => e.value === list[index]);
        if (list[index] === "" || optionElement === undefined ) {
            throw new Error(index + 1 + "번 째 값(" + list[index] + ")이 올바르지 않습니다.");
        }
        element.value = list[index];
        element.nextSibling.querySelector("span").innerText = optionElement.innerText;
        element.nextElementSibling.querySelector("a").classList.remove("chosen-default"); // 스타일 제거
    } 
}

var errorMsg = "";
document.body.querySelectorAll(".popup_main").forEach(function (e) {
  if (e.parentElement.style.display === "block") {
    try {
      e.querySelectorAll("td > input, select").forEach(insertData); 
    } catch (error) {
      errorMsg = error.message;
    }
  }
});

errorMsg;