<%--************
Create the JSP  For Viewing All of The  Sponsor table
 Created By Jonathan Beck 11/14/2024
**********--%>
<%@include file="/WEB-INF/crrg/head.jsp"%>
<div class = "container" id="addr" addr="${appURL}" objectType="sponsor">
<div class="row">
<div class="col-12">
<h1>All Roller Sponsors</h1>
<p>There ${Sponsors.size() eq 1 ? "is" : "are"}&nbsp;${Sponsors.size()} Sponsor${Sponsors.size() ne 1 ? "s" : ""}</p>
Add Sponsor   <a href="addSponsor">Add</a>
<c:if test="${Sponsors.size() > 0}">
<div class="table-responsive"><table class="table table-bordered">
<thead>
<tr>

<th scope="col"><a href="${appURL}/all-Sponsors?Order_By=Sponsor">Sponsor Name</a></th>
<th scope="col"><a href="${appURL}/all-Sponsors?Order_By=Tier">Tier</a></th>
<th scope="col">website</th>
<th scope="col">Description</th>
<th scope="col">is_active</th>
<th scope="col">Edit</th>

</tr>
</thead>
<tbody>
<c:forEach items="${Sponsors}" var="sponsor">
<tr>
<td><a href = "editsponsor?sponsorid=${sponsor.sponsor_ID}&mode=view">${fn:escapeXml(sponsor.sponsor_ID)}</a></td><td>${fn:escapeXml(sponsor.tier_ID)}</td>
<td>${fn:escapeXml(sponsor.website)}</td>
<td>${fn:escapeXml(sponsor.description)}</td>
	<td><input type="checkbox" id="${sponsor.sponsor_ID}" class="Activation_Box" <c:if test="${sponsor.is_Active}">checked</c:if>> <p  id="${sponsor.sponsor_ID}_status"></p></td>
<td><a href = "editSponsor?sponsorid=${sponsor.sponsor_ID}&mode=edit" > Edit </a></td>
</tr>
</c:forEach>
</tbody>
</table>
</div>
</c:if>
</div>
</div>
</div>
</main>
<%@include file="/WEB-INF/crrg/foot.jsp"%>

