var list = data.split(",");

function insertData(element, index, array) {
    if (element.tagName === "INPUT") {
        element.value = list[index];
    } else {
        element.innerText = list[index];
    }
}

document.body.querySelectorAll(".popup_main").forEach(function (e) {
  if (e.parentElement.style.display !== "none") {
    e.querySelectorAll("td > input, .chosen-single > span").forEach(insertData);
  }
});
