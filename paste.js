var list = data.split(",");

function insertData(element, index, array) {
    if (element.tagName === "INPUT") {
        element.value = list[index];
    } else {
        element.value = list[index];
        element.nextSibling.querySelector("span").innerText = 
            Object.values(element.options).find(e => e.value === list[index]).innerText;
        element.nextElementSibling.querySelector("a").classList.remove("chosen-default"); // 스타일 제거
        
    }
}

document.body.querySelectorAll(".popup_main").forEach(function (e) {
  if (e.parentElement.style.display === "block") {
    e.querySelectorAll("td > input, select").forEach(insertData);
  }
});