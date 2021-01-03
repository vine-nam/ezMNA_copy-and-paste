/*
function copyFunc(e) {
    console.log("hello");
    if(document.querySelector("#myApp") !== null) { 
        document.querySelector("#myApp").remove();
    }
    var top = e.pageY - 40 + "px";
    var left = e.pageX + 10 + "px";
    var buttonTag = document.createElement("button");
    buttonTag.innerText = "copy"
    buttonTag.setAttribute("id", "myApp");
    buttonTag.style.cssText = 'z-index: 99999; position: absolute; top: ' + top + '; left: ' + left + ';';
    document.body.append(buttonTag);

    document.querySelector("#myApp").addEventListener("click", function() {
        var data=''; 
        document.body.querySelectorAll('#interfaceBaseInfoPop td > input, #interfaceBaseInfoPop .chosen-single > span').forEach(function(e) {
            data += ( e.tagName === "INPUT" ? e.value : e.innerText ) + ",";
        });
        console.log(data);

        var tempElem = document.createElement('textarea');
        tempElem.value = data;  
        document.body.appendChild(tempElem);
      
        tempElem.select();
        document.execCommand("copy");
        document.body.removeChild(tempElem);
        document.querySelector("#myApp").remove();
    });
}
*/


function copyData(element) {
    data += ( element.tagName === "INPUT" ? element.value : element.innerText ) + ",";
}

function copyToClipborad(data) {
    var tempElem = document.createElement('textarea');
    tempElem.value = data;  
    document.body.appendChild(tempElem);

    tempElem.select();
    document.execCommand("copy");
    document.body.removeChild(tempElem);
}

document.querySelector("#copy").addEventListener("click", function() {
    var data=''; 
    chrome.tabs.executeScript({
        code: "var data=''; document.body.querySelectorAll('.popup_main').forEach(function(e) {if(e.parentElement.style.display === 'block') {e.querySelectorAll('td > input, .chosen-single > span').forEach(" + copyData + ");}});(function (){return data;})();"
    }, function(result) {
        copyToClipborad(result[0]);
        document.querySelector('#textBox').innerText = 
            result[0].length !== 0 ? "copied!!" : "failed..";
    });
});


function insertData(element, index, array) {
    var list = data.split(",");
    if (element.tagName === "INPUT") {
        element.value = list[index];
    } else {
        element.innerText = list[index];
    }
}

document.querySelector("#paste").addEventListener("click", function() {
    var data = document.querySelector('#textBox').value;
    chrome.tabs.executeScript({
        code: "var data = '" + data + "'; document.body.querySelectorAll('.popup_main').forEach(function(e) {if(e.parentElement.style.display === 'block') {e.querySelectorAll('td > input, .chosen-single > span').forEach(" + insertData + ");}})"
    });
})


/*
chrome.tabs.executeScript({
    code: 'document.addEventListener("dblclick", ' + copyFunc + ');'
    //window.addEventListener("paste", ' + pasteFunc + ');
    }, function(result) { 
});
*/