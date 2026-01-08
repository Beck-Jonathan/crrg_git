$(document).ready(function() {
    const lightbox = document.getElementById("lightbox");
    const lightboxImg = document.getElementById("lightboxImg");

    document.querySelectorAll(".gallery img").forEach(img => {
        img.addEventListener("click", () => {
            lightbox.style.display = "flex";
            lightboxImg.src = img.getAttribute("fullLocation");

            //downloadBtn.href = img.getAttribute("fullLocation");
            downloadBtn.onclick  = () =>forceDownload(img.getAttribute("fullLocation"));
        });
    });

    lightbox.addEventListener("click", (e) => {
        if (e.target !== lightboxImg) {
            lightbox.style.display = "none";
        }
    });

    document.addEventListener("keydown", e => {
        if (e.key === "Escape") lightbox.style.display = "none";
    });
    var allbuttons = document.getElementsByClassName("Activation_Box");
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
    function takevalues(id,url,typeOfObject,status,box) {

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
        xhr.open("POST", url+"/updateActivation", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        let params = "objectID=" + id + "&mode=" + status+"&object="+typeOfObject; // probably use document.getElementById(...).value
        xhr.send(params);


    }})

function forceDownload(url) {
    console.log("try")
    fetch(url)
        .then(res => res.blob())
        .then(blob => {
            const blobUrl = window.URL.createObjectURL(blob);
            const a = document.createElement("a");
            a.href = blobUrl;
            a.download = url.split("/").pop();
            document.body.appendChild(a);
            a.click();
            a.remove();
            window.URL.revokeObjectURL(blobUrl);
        })
        .catch(() => alert("Download failed"));
}
