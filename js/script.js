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

document.querySelector("#alert-flag").addEventListener("change", function() {
    chrome.storage.sync.set({
        alertFlag:  document.querySelector('#alert-flag').checked
    });
});

document.querySelector("#alert-mail").addEventListener("change", function() {
    chrome.storage.sync.set({
        alertMail:  document.querySelector('#alert-mail').checked
    });
});

document.querySelector("#alert-sms").addEventListener("change", function() {
    chrome.storage.sync.set({
        alertSms:  document.querySelector('#alert-sms').checked
    });
});

document.querySelector("#clear").addEventListener("click", function() {
    messageDivInit();
    chrome.storage.sync.clear(function() {
        document.querySelector("#textBox").value = '';
        var error = chrome.runtime.lastError;
        if (error) {
            console.error(error);
        }
    });
});

document.querySelector("#copy").addEventListener("click", function() {
    messageDivInit();
    chrome.tabs.executeScript({
        file: 'js/copy.js'
    }, function(result) {
        if (result !== undefined && result.length > 0 && result[0].length > 0) {
            // copyToClipborad(result[0]);
            document.querySelector('#message').innerText = "copied!!";
            document.querySelector('#message').classList.add("successMsg");
            document.querySelector('#textBox').value = result[0];
            
            // 크롬 스토리지에 복사한 데이터 저장
            chrome.storage.sync.set({
                copiedText: result[0]
            });
        } else {
            document.querySelector('#message').innerText = "failed..";
            document.querySelector('#message').classList.add("errorMsg");
        }
    });
});

document.querySelector("#paste").addEventListener("click", function() {
    var data = document.querySelector('#textBox').value;
    var alertData = {
        flag: document.querySelector('#alert-flag').checked,
        mail: document.querySelector('#alert-mail').checked,
        sms: document.querySelector('#alert-sms').checked
    };
    messageDivInit();
    chrome.tabs.executeScript({
        code: 'var data = "' + data + '"; var alertData = ' + JSON.stringify(alertData) + ';'
    }, function() {
        chrome.tabs.executeScript({
            file: 'js/paste.js'
        }, function(error) {
            if (error !== "") {
                document.querySelector('#message').innerText = error;
                document.querySelector('#message').classList.add("errorMsg");
            }
        });
    });
})

// 복사한 데이터를 크롬 스토리지에 저장했다가 (popup을 실행할 때) 불러온다.
chrome.storage.sync.get(function (data) {
    document.querySelector("#alert-flag").checked = !!data.alertFlag;
    document.querySelector("#alert-mail").checked = !!data.alertMail;
    document.querySelector("#alert-sms").checked = !!data.alertSms;
    if(data.copiedText) {
        document.querySelector("#textBox").value = data.copiedText;
    }
});

/* 
// 클립보드에 데이터가 있으면 popup을 실행할 때 그 값을 textBox에 붙여 넣는다.
document.addEventListener("DOMContentLoaded", function() {
    document.querySelector("#textBox").focus();
    document.execCommand('paste');
});
*/

/*
chrome.tabs.executeScript({
    code: 'document.addEventListener("dblclick", ' + copyFunc + ');'
    //window.addEventListener("paste", ' + pasteFunc + ');
    }, function(result) { 
});
*/