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

function messageDivInit() {
    document.querySelector('#message').innerText = "";  
    document.querySelector('#message').className = "";
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
    messageDivInit();
    chrome.tabs.executeScript({
        file: 'copy.js'
    }, function(result) {
        if (result !== undefined && result.length > 0 && result[0].length > 0) {
            copyToClipborad(result[0]);
            document.querySelector('#message').innerText = "copied!!";
            document.querySelector('#message').classList.add("successMsg");
            document.querySelector('#textBox').value = result[0];
        } else {
            document.querySelector('#message').innerText = "failed..";
            document.querySelector('#message').classList.add("errorMsg");
        }
    });
});

document.querySelector("#paste").addEventListener("click", function() {
    var data = document.querySelector('#textBox').value;
    messageDivInit();
    chrome.tabs.executeScript({
        code: 'var data = "' + data + '";'
    }, function() {
        chrome.tabs.executeScript({
            file: 'paste.js'
        }, function(error) {
            if (error !== "") {
                document.querySelector('#message').innerText = error;
                document.querySelector('#message').classList.add("errorMsg");
            }
        });
    });
})


/*
chrome.tabs.executeScript({
    code: 'document.addEventListener("dblclick", ' + copyFunc + ');'
    //window.addEventListener("paste", ' + pasteFunc + ');
    }, function(result) { 
});
*/