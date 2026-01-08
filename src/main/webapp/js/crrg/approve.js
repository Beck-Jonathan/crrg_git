$(document).ready(function() {
    var allbuttons = document.getElementsByClassName("Approval_Box");
    for (var i = 0; i < allbuttons.length; i++) {
        allbuttons[i].addEventListener('click', async function () {

            classList = this.classList;
            let active = false;
            console.log(classList.length);
            for (var i = 0; i < classList.length; i++) {
                if (classList[i] === "true") {
                    active = true;
                    break;
                }
            }
            var newStatus = 1;
            if (active) {
                newStatus = 0;
                this.classList.remove("true")
            }
            this.classList.add("pending");
            let id = this.getAttribute("id");
            var address = document.getElementById("addr").getAttribute("addr");
            var objectType = document.getElementById("addr").getAttribute("objectType");
            let y = await takevalues(id, address, objectType, newStatus,this);
        });
    }
    async function takevalues(id,url,typeOfObject,status,box) {

        if (id == null) {
            return;
        }
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                console.log(xhr.responseText);
                box.classList.remove("pending");
                if (status===1) {
                    box.classList.add("true");
                }
            }
        }
        var stoppoint=id.indexOf("_");
        id=id.substring(0,stoppoint)
        xhr.open("POST", url+"/updateApproval", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        let params = "objectID=" + id + "&mode=" + status+"&object="+typeOfObject; // probably use document.getElementById(...).value
        xhr.send(params);

    }})
