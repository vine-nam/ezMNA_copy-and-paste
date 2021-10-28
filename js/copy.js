var data = "";

function copyData(element) {
    data += element.value.replaceAll(',', '\\\\') + ",";
}

document.body.querySelectorAll(".popup_main").forEach(function (e) {
    if (e.parentElement.style.display === "block") {
        e.querySelectorAll("td > input, select").forEach(copyData);
    }
});

data;
