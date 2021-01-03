var data = "";

function copyData(element) {
    data += ( element.tagName === "INPUT" ? element.value : element.innerText ) + ",";
}

document.body.querySelectorAll(".popup_main").forEach(function (e) {
  if (e.parentElement.style.display !== "none") {
    e.querySelectorAll("td > input, .chosen-single > span").forEach(copyData);
  }
});

data;
