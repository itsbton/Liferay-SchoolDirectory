<aui:button-row>
	<aui:button name="submitButton" type="submit" value="Submit" />	
</aui:button-row>
<c:if test="${not empty record.primaryKey }">
	<portlet:actionURL var="DeleteUrl" name="/action/delete">
		<portlet:param name="tableName" value="${tab }" />
		<portlet:param name="primaryKey" value="${record.primaryKey }" />
	</portlet:actionURL>
	<a href="${DeleteUrl }" class="btn btn-danger"  onclick="return confirm('Are you sure you want to delete ${district.code} - ${district.name}?')">Delete</a>
</c:if>