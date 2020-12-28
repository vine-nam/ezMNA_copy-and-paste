// 컨텐트 페이지의 #user 입력된 값이 변경 되었을 때 
document.querySelector('#user').addEventListener('change', function() {
    // 컨텐트 페이지에 몇개의 단어가 등장하는지 계상해 주세요,
    var user = document.querySelector('#user').value;

    // 컨텐트 페이지를 대상으로 코드를 실행해주세요.
    chrome.tabs.executeScript({
        code:'document.querySelector("body").innerText'
    }, function(result) { // 위의 코드가 실행된 후에 이 함수를 호출해 주세요.
        var bodyText = result[0];
        var bodyNum = bodyText.split(" ").length;
        var myNum = bodyText.match(new RegExp('\\b('+user+')\\b', 'gi')).length;
    
        var per = myNum/bodyNum*100;
        per = per.toFixed(1);
        document.querySelector('#result').innerText = myNum + '/' + bodyNum + ' (' + (per) + '%)';
    });
})



function hello(e) {
    console.log("hello");
    if(document.querySelector("#myApp") !== null) { 
        document.querySelector("#myApp").remove();
    }
    var top = e.pageY - 40 + "px";
    var left = e.pageX + 10 + "px";
    var buttonTag = '<button id="myApp" style="position: absolute; top: ' + top + '; left: ' + left + ';">^^</button>';
    document.body.innerHTML += buttonTag;

    document.querySelector("#myApp").addEventListener("click", function() {
        console.log(document.querySelectorAll("input")[0].value);
    });
}

chrome.tabs.executeScript({
    code: 'document.body.addEventListener("dblclick", ' + hello + ')'
}, function(result) { 
    console.log(result);
    
});
