function pasteData(data, alertData) {
    let list = data.split(",");
    let errorMsg = "";

    function alertFlag(index) {
        let isTrue;
        switch (index) {
            case 26:
                isTrue = alertData.flag;
                break;
            case 27:
                isTrue = alertData.mail;
                break;
                case 28:
                    isTrue = alertData.sms;
                    break;
                }
                return isTrue ? 'Y' : 'N';
            }
            
    function insertData(element, index) {
        let data = list[index].replaceAll('\\', ',');
        if (element.tagName === "INPUT") {
            element.value = data;
        } else if (element.tagName === "SELECT")  {  
            if(index >= 26) {
                data = alertFlag(index);
            }
            let optionElement = Object.values(element.options).find(e => e.value === data);
            if (data === "" || optionElement === undefined ) {
                throw new Error(index + 1 + "번째 값(" + data + ")이 올바르지 않습니다.");
            }
            element.value = data;
            element.nextSibling.querySelector("span").innerText = optionElement.innerText;
            element.nextElementSibling.querySelector("a").classList.remove("chosen-default"); // 스타일 제거
        } 
    }

    document.body.querySelectorAll(".popup_main").forEach(function (e) {
        if (e.parentElement.style.display === "block") {
            try {
                e.querySelectorAll("td > input, select").forEach(insertData); 
            } catch (error) {
                errorMsg = error.message;
            }
        }
    });
    
    return errorMsg;
}