<%--************
Create the JSP  For Viewing All of The  Picture table
 Created By Jonathan Beck 10/10/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container" id="addr" addr="${appURL}" objectType="picture">
    <div class="row">
        <div class="col-12">
            <h1>All Roller Pictures</h1>
            <div class="search-container">
                <form action="all-Pictures">
                    <input type="text" placeholder="Search.." id="searchBox" name="search" value="${results.search}">
                <br/>
                    <label for="inputpictureAlbum_ID" class="form-label">Album_ID</label>
                    <div class="input-group input-group-lg">
                        <select  class="<c:if test="${not empty results.pictureAlbum_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  id="inputpictureAlbum_ID" name="album" >
                            <option value="0">**Any**   </option>

                            <c:forEach items="${Albums}" var="Album">
                                <option
                                        <c:if test="${Album.album_ID eq results.AlbumID}">
                                            selected
                                        </c:if>
                                        value="${Album.album_ID}">${Album.album_Name}   </option>
                            </c:forEach>
                        </select>
                        <c:if test="${not empty results.pictureAlbum_IDerror}">
                            <div class="invalid-feedback">${results.pictureAlbum_IDerror}</div>
                        </c:if>
                    </div>
                    <label for="inputpictureContributor_ID" class="form-label">Contributor_ID</label>
                    <div class="input-group input-group-lg">
                        <select  class="<c:if test="${not empty results.pictureContributor_IDerror}">is-invalid</c:if> form-control border-0 bg-light rounded-end ps-1"  id="inputpictureContributor_ID" name="contributor" >
                            <option value="0">**Any**   </option>
                            <c:forEach items="${Contributors}" var="Contributor">
                                <option
                                <c:if test="${Contributor.contributor_ID eq results.contributorID}">
                                    selected
                                </c:if>
                                 value="${Contributor.contributor_ID}">${Contributor.email }  </option>
                            </c:forEach>
                        </select>
                        <c:if test="${not empty results.pictureContributor_IDerror}">
                            <div class="invalid-feedback">${results.pictureContributor_IDerror}</div>
                        </c:if>
                    </div>
                    <button type="submit" class="btn btn-outline-secondary">
                        <i class="fas fa-search"></i> search
                    </button>
                </form>
            </div>
            <p>There ${numObjects eq 1 ? "is" : "are"}&nbsp;${numObjects} Picture${numObjects ne 1 ? "s" : ""}</p>
            <p> Add Picture   <a href="addPicture">Add</a> </p>
            <c:if test="${Pictures.size() > 0}">

            <div class="gallery">
                    <c:forEach items="${Pictures}" var="picture">
                        <span id="${picture.picture_ID}_card" class="card directionCard">
                            <p>${picture.album.album_Name}</p>
     <%-- <a href="${picture.web_Address}" > --%>
         <img
                 id ="${picture.picture_ID}_img"
                 crossorigin="anonymous"
                 referrerPolicy="no-referrer"
                 fullLocation = "${picture.web_Address}"
             <%--<c:if test="${!fn:contains(direction.image_Link, 'drive')}">  referrerPolicy="no-referrer" </c:if> --%>
             <%--  crossorigin="anonymous" --%>

              src="./thumbnails/${picture.picture_ID}.jpg" alt="Avatar">
         <%--   </a> --%>

    <p>${fn:escapeXml(picture.description)}</p>
                            <p><button id="${picture.picture_ID}_Approve" picID="${picture.picture_ID}" class="badge Approval_Box  Approve <c:if test = "${picture.is_Approved }"> true </c:if>">👌</button>
                                    <button id="${picture.picture_ID}_Active" picID="${picture.picture_ID}"  class="badge Activation_Box  Active <c:if test = "${picture.is_Active }"> true </c:if>">🟢</button>
                                    </p>
        <div class="row">
            <div class="col-2">
                    <a  class="delButton" href="${picture.picture_ID}">❌</a>
            </div>

            <div class="col-5"></div>
            <div class="col-2 ">
                    <a href = "editPicture?pictureid=${picture.picture_ID}&mode=edit"> ⚙️ </a>
            </div>
            <div class="col-2"><a href = "editPicture?pictureid=${picture.picture_ID}&mode=view"> 🔎 </a></div>
            <div class="col-1"></div>
        </div>
    </span>
                    </c:forEach>

                </div>
            </c:if>

        </div>
    </div>
</div>
<c:if test="${currentPage != 1}">
    <form action="all-Pictures" method="get">
        <input type="hidden" name="page" value="${currentPage-1}">
        <br/><br/>
        <input type="submit" value="Previous Page" />
        <input type = hidden name = "contributor" value = "${results.contributorID}">
        <input type = hidden name = "album" value = "${results.AlbumID}">
        <input type = hidden name = "search" value = "${results.search}">
    </form>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<form action="all-Pictures" method="get" >

    Select a page :
    <select name="page" onchange="this.form.submit()">
        <c:forEach var="i" begin="1" end="${noOfPages}">
            <option value=${i}  ${currentPage == i ? ' selected' : ''} >${i}</option>
        </c:forEach>

    </select>
    <input type = hidden name = "contributor" value = "${results.contributorID}">
    <input type = hidden name = "album" value = "${results.AlbumID}">
    <input type = hidden name = "search" value = "${results.search}">
    <br/><br/>
    <input type="submit" value="Submit" />
</form>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <form action="all-Pictures" method="get">
        <input type="hidden" name="page" value="${currentPage+1}">
        <input type = hidden name = "contributor" value = "${results.contributorID}">
        <input type = hidden name = "album" value = "${results.AlbumID}">
        <input type = hidden name = "search" value = "${results.search}">
        <br/><br/>
        <input type="submit" value="Next Page" />
    </form>
</c:if>

<!-- Lightbox -->
<div id="lightbox" class="lightbox">
    <div class="lightbox-frame">
        <span class="close">&times;</span>

        <img class="lightbox-img" id="lightboxImg">
        <div id="lightboxCounter" class="lightbox-counter"></div>
        <button class="lightbox-arrow left" id="lightboxPrev">&#10094;</button>
        <button class="lightbox-arrow right" id="lightboxNext">&#10095;</button>
        <p><button id="modal_Approve" class="badge Approval_Box  Approve ">👌</button>
            <button id="modal_Active" class="badge Activation_Box  Active ">🟢</button>
        </p>
        <a id="downloadBtn" class="download-btn" download>
            ⬇ Download
        </a>
    </div>
</div>
</main>
<%@include file="/WEB-INF/crrg/foot.jsp"%>

