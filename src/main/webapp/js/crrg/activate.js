const lightbox = document.getElementById("lightbox");
const lightboxImg = document.getElementById("lightboxImg");
const lightBoxApprove = document.getElementById("modal_Approve");
const lightBoxActive = document.getElementById("modal_Active")
let oldApprovalButton = null;
let oldActiveButton = null;
let galleryImages = [];
let currentIndex = 0;
let touchStartX = 0;
let touchEndX = 0;
let scale = 1;
let startDistance = 0;
let isPanning = false;
let startX = 0;
let startY = 0;
let translateX = 0;
let translateY = 0;
const preloadCache = new Set();

$(document).ready(function() {

    addLightBox();
    addLightBoxEscape()
    setupActivationButtons();
    setupApprovalButtons();
    enableArrows()
    addKeyboardControls();
    addSwipeControls();
    addZoomControls();
    addTouchStart();
    addTouchMove();
    addTouchEnd();
    addMousePan();
});
    function setupApprovalButtons(){
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
                    if (oldApprovalButton !=null){
                        oldApprovalButton.classList.remove("true")
                    }
                }
                this.classList.add("pending");
                if (oldApprovalButton !=null){
                    oldApprovalButton.classList.add("pending");
                }
                let id = this.getAttribute("picID");
                var address = document.getElementById("addr").getAttribute("addr");
                var objectType = document.getElementById("addr").getAttribute("objectType");
                let y = await SendApprovalRequest(id, address, objectType, newStatus,this,oldApprovalButton);
            });
        }
    }
    function setupActivationButtons(){
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
                    if (oldActiveButton !=null){
                        oldActiveButton.classList.remove("true")
                    }
                }
                this.classList.add("pending");
                if (oldActiveButton !=null){
                    oldActiveButton.classList.add("pending");
                }
                let id = this.getAttribute("picID");
                var address = document.getElementById("addr").getAttribute("addr");
                var objectType = document.getElementById("addr").getAttribute("objectType");


                let y = await SendActivationRequest(id, address, objectType, newStatus,this,oldActiveButton);
            });
        }
    }



    function SendActivationRequest(id,url,typeOfObject,status,box, realbox) {

        console.log ("box = "+ realbox);
        if (id == null) {
            return;
        }
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                console.log(xhr.responseText);
                box.classList.remove("pending");
                if (realbox!=null) {
                    realbox.classList.remove("pending");
                }
                if (status===1) {
                    box.classList.add("true");
                    if (realbox!=null) {
                        realbox.classList.add("true");
                    }
                }
            }
        }

        xhr.open("POST", url+"/updateActivation", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        let params = "objectID=" + id + "&mode=" + status+"&object="+typeOfObject; // probably use document.getElementById(...).value
        xhr.send(params);


    }

async function SendApprovalRequest(id,url,typeOfObject,status,box,realbox) {

    console.log ("box = "+ realbox);
    if (id == null) {
        return;
    }
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log(xhr.responseText);
            box.classList.remove("pending");
            if (realbox!=null) {
                realbox.classList.remove("pending");
            }
            if (status===1) {
                box.classList.add("true");
                if (realbox!=null) {
                    realbox.classList.add("true");
                }
            }
        }
    }

    xhr.open("POST", url+"/updateApproval", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let params = "objectID=" + id + "&mode=" + status+"&object="+typeOfObject; // probably use document.getElementById(...).value
    xhr.send(params);

}

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

function addLightBox(){
    galleryImages = Array.from(document.querySelectorAll(".gallery img"));

    galleryImages.forEach((img, index) => {
        img.addEventListener("click", () => {
            openLightboxAt(index);
        });
    });
    // document.querySelectorAll(".gallery img").forEach(img => {
    //     img.addEventListener("click", () => {
    //         lightbox.style.display = "flex";
    //         lightboxImg.src = img.getAttribute("fullLocation");
    //         let imgID = img.getAttribute("id");
    //         imgID = imgID.substring(0,imgID.indexOf("_"))
    //         var oldapprovalbuttonid=  imgID+"_Approve";
    //         var oldactivebuttonid= imgID+"_Active";
    //         oldApprovalButton = document.getElementById(oldapprovalbuttonid);
    //         oldActiveButton = document.getElementById(oldactivebuttonid);
    //         //console.log(oldApprovalButton);
    //         console.log("button : "+oldActiveButton)
    //         lightBoxApprove.className = "";
    //         lightBoxActive.className = "";
    //         lightBoxApprove.classList.add(...oldApprovalButton.classList);
    //         lightBoxActive.classList.add(...oldActiveButton.classList);
    //         lightBoxApprove.setAttribute("picID",imgID)
    //         lightBoxActive.setAttribute("picID",imgID)
    //         //downloadBtn.href = img.getAttribute("fullLocation");
    //         downloadBtn.onclick  = () =>forceDownload(img.getAttribute("fullLocation"));
    //     });
    // });
}
function openLightboxAt(index) {
    scale = 1;
    translateX = 0;
    translateY = 0;
    isPanning = false;
    applyTransform();
    scale = 1;
    lightboxImg.style.transform = "";
    document.body.classList.add("no-scroll");
    if (index < 0 || index >= galleryImages.length) return;

    currentIndex = index;
    const img = galleryImages[currentIndex];

    lightbox.style.display = "flex";
    //erase this
    //lightboxImg.src = img.getAttribute("fullLocation");
    //replcae with this
    const newSrc = img.getAttribute("fullLocation");

    lightboxImg.classList.remove("fade-in");
    lightboxImg.classList.add(
        index > currentIndex ? "fade-out-left" : "fade-out-right"
    );

    setTimeout(() => {
        lightboxImg.src = newSrc;
        lightboxImg.classList.remove("fade-out-left", "fade-out-right");
        lightboxImg.classList.add("fade-in");
    }, 200);
    let imgID = img.id.substring(0, img.id.indexOf("_"));

    oldApprovalButton = document.getElementById(imgID + "_Approve");
    oldActiveButton = document.getElementById(imgID + "_Active");

    lightBoxApprove.className = "";
    lightBoxActive.className = "";

    lightBoxApprove.classList.add(...oldApprovalButton.classList);
    lightBoxActive.classList.add(...oldActiveButton.classList);

    lightBoxApprove.setAttribute("picID", imgID);
    lightBoxActive.setAttribute("picID", imgID);

    downloadBtn.onclick = () =>
        forceDownload(img.getAttribute("fullLocation"));
    document.getElementById("lightboxCounter").innerText =
        (currentIndex + 1) + " / " + galleryImages.length;
    preloadImageAt((currentIndex + 1) % galleryImages.length);
    preloadImageAt(
        (currentIndex - 1 + galleryImages.length) % galleryImages.length
    );
}
function addLightBoxEscape(){
    document.addEventListener("keydown", e => {
        if (e.key === "Escape"){
            lightbox.style.display = "none";
            document.body.classList.remove("no-scroll");
            oldApprovalButton = null;
            oldActiveButton = null;
        }

    });
    // lightbox.addEventListener("click", (e) => {
    //     if (e.target !== lightbox) {
    //         lightbox.style.display = "none";
    //     }
    /// });
}

function enableArrows(){
        try{
            document.getElementById("lightboxPrev").onclick = () => {
                openLightboxAt(
                    (currentIndex - 1 + galleryImages.length) % galleryImages.length
                );
            };

            document.getElementById("lightboxNext").onclick = () => {
                openLightboxAt(
                    (currentIndex + 1) % galleryImages.length
                );
            };
        } catch(Exception){

        }

}

function addKeyboardControls(){
    try{
        if (lightbox!=null) {
            document.addEventListener("keydown", e => {
                if (lightbox.style.display !== "flex") return;

                if (e.key === "Escape") {
                    lightbox.style.display = "none";
                    document.body.classList.remove("no-scroll");
                    oldApprovalButton = null;
                    oldActiveButton = null;
                }
                if (e.key === "ArrowLeft") {
                    document.getElementById("lightboxPrev").click();
                }
                if (e.key === "ArrowRight") {
                    document.getElementById("lightboxNext").click();
                }

            })
        }
    } catch (Exception ){

    }
}
function addSwipeControls(){
        try {
            const swipeTarget = document.getElementById("lightboxImg");

            swipeTarget.addEventListener("touchstart", e => {
                touchStartX = e.changedTouches[0].screenX;
            }, {passive: true});

            swipeTarget.addEventListener("touchend", e => {
                touchEndX = e.changedTouches[0].screenX;
                handleSwipe();
            });
        } catch (Exception ){

        }
}
function handleSwipe() {
    const swipeDistance = touchStartX - touchEndX;
    const threshold = 50; // px needed to count as swipe

    if (Math.abs(swipeDistance) < threshold) return;

    if (swipeDistance > 0) {
        // swipe left → next image
        document.getElementById("lightboxNext").click();
    } else {
        // swipe right → previous image
        document.getElementById("lightboxPrev").click();
    }
}

function addZoomControls(){
        try {
    lightboxImg.addEventListener("touchstart", e => {
        if (e.touches.length === 2) {
            startDistance = getDistance(e.touches[0], e.touches[1]);
        }
    }, { passive: false });

    lightboxImg.addEventListener("touchmove", e => {
        if (e.touches.length === 2) {
            e.preventDefault();
            const newDistance = getDistance(e.touches[0], e.touches[1]);
            scale *= newDistance / startDistance;
            scale = Math.min(Math.max(scale, 1), 4); // clamp 1x–4x
            startDistance = newDistance;
            applyTransform();
        }
    }, { passive: false });

    lightboxImg.addEventListener("touchend", e => {
        if (e.touches.length < 2 && scale === 1) {
            lightboxImg.style.transform = "";
        }
    });
        } catch (Exception ){

        }

}
function getDistance(t1, t2) {
    return Math.hypot(
        t2.clientX - t1.clientX,
        t2.clientY - t1.clientY
    );
}

function preloadImageAt(index) {
    if (index < 0 || index >= galleryImages.length) return;

    const img = galleryImages[index];
    const src = img.getAttribute("fullLocation");

    if (preloadCache.has(src)) return;

    const preImg = new Image();
    preImg.src = src;

    preloadCache.add(src);
}
function applyTransform() {
    lightboxImg.style.transform =
        `translate(${translateX}px, ${translateY}px) scale(${scale})`;
}

function addTouchStart(){
        try {
    lightboxImg.addEventListener("touchstart", e => {
        if (scale > 1 && e.touches.length === 1) {
            isPanning = true;
            startX = e.touches[0].clientX - translateX;
            startY = e.touches[0].clientY - translateY;
        }
    }, { passive: false });
        } catch (Exception ){

        }
}

function addTouchMove(){
    try{
        lightboxImg.addEventListener("touchmove", e => {
        if (isPanning && e.touches.length === 1) {
            e.preventDefault();

            translateX = e.touches[0].clientX - startX;
            translateY = e.touches[0].clientY - startY;

            applyTransform();
        }
    }, { passive: false });
    } catch (Exception ){

    }
}

function addTouchEnd(){
    try {
        lightboxImg.addEventListener("touchend", () => {
        isPanning = false;
    });
    } catch (Exception ){

    }

}

function addMousePan(){
    try{
        lightboxImg.addEventListener("mousedown", e => {
        if (scale > 1) {
            isPanning = true;
            startX = e.clientX - translateX;
            startY = e.clientY - translateY;
            lightboxImg.style.cursor = "grabbing";
        }
    });

    document.addEventListener("mousemove", e => {
        if (!isPanning) return;

        translateX = e.clientX - startX;
        translateY = e.clientY - startY;
        applyTransform();
    });

    document.addEventListener("mouseup", () => {
        isPanning = false;
        lightboxImg.style.cursor = "grab";
    });
    } catch (Exception ){

    }

}