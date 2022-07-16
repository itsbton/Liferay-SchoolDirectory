<%@ include file="/init.jsp" %>
<liferay-ui:error key="error" message="Something went wrong, please try again" />
<liferay-ui:error key="size" message="CSV is too big" />
<liferay-ui:error key="csv" message="The file must be in csv format and semicolon delimited" />

<portlet:actionURL var="parseURL" name="/action/parse"></portlet:actionURL>
<portlet:actionURL var="commitURL" name="/action/commit"></portlet:actionURL>

<style>
.errorTd {
	border: solid red!important;
}

.hiddenForm {
	display:none;
}
</style>

<aui:container>
	<p>Rules:</p>
	<ul>
		<li>CSV MUST have ${columnAmount } columns in this order
			<ul>
			 	<li>studentId/username/email</li>
			 	<li>visit reason</li>
			 	<li>submitter username</li>
			 	<li>meeting date</li>
			 	<li>location</li>
			 	<li>required by professor</li>
			 	<li>CRN</li>
			</ul>
		</li>
		<li>Max of ${hardLimitCsv } entries at a time (if CSV contains more than ${hardLimitCsv }, then first ${hardLimitCsv } will be imported)</li>
		<li>Student Id column can be either a username, email or a studentId. It will be converted to a studentId number upon upload.</li>
		<li>If it cannot find a studentId with the value, then it will default to a blank column</li>
		<li>Meeting Date must be in format YYYY/MM/DD hh:mm:ss</li>
		<li>Required column must be in format false/true. F and T are not accepted</li>
	</ul>
	<aui:form action="${parseURL}" name="parseCsvForm" enctype="multipart/form-data">
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset label="Please select CSV file to upload">
				<input type="file" accept=".csv" name="csvFile"></input>
			</aui:fieldset>
			<aui:fieldset label="">
				<aui:input type="checkbox" label="First row headers?" name="firstRowHeaders" />
			</aui:fieldset>
			<aui:row>
				<aui:col width="100">
					<button type="submit" class="btn btn-default">Submit the CSV file</button>
				</aui:col>
			</aui:row>
		</aui:fieldset-group>
	</aui:form>
	
		<table class="table">
			<thead>
				<tr>
					<th>Student Id</th>
					<th>Visit Reason</th>
					<th>Submitter</th>
					<th>Meeting Date</th>
					<th>Location</th>
					<th>Required (Must be false or true)</th>
					<th>CRN</th>
				</tr>
			</thead>
		<c:if test="${visits.size() > 0 }">
			<tbody>
				<c:forEach items="${visits }" var="visit">
					<tr class="importRecord">
						<td class="recordStudentId" contenteditable="true">${visit.studentId }</td>
						<td class="recordVisitReason" contenteditable="true">${visit.visitReason }</td>
						<td class="recordSubmitterId" contenteditable="true">${visit.submitterId }</td>
						<td class="recordMeetingDate" contenteditable="true">${visit.meetingDate }</td>
						<td class="recordLocation" contenteditable="true">${visit.location }</td>
						<td class="recordRequired" contenteditable="true">${visit.required }</td>
						<td class="recordCourseNumber" contenteditable="true">${visit.courseNumber }</td>
						<td>
							<button class="btn btn-default deleteButton">Delete</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</c:if>
		
		</table>
		
		<c:if test="${visits.size() > 0 }">
			<%--Button to commit changes to the table --%>
			<button class="btn btn-warning" id="<portlet:namespace />reviewDataButton" type="button">Review Data</button>
			<button class="btn btn-success" id="<portlet:namespace />commitDataButton" type="button" disabled>Commit Changes<span class="glyphicon glyphicon-chevron-right"></span></button>
			
			<%--Hidden form that actually submits records to the backend --%>
			<aui:form action="${commitURL}" name="recordsInsertForm" enctype="multipart/form-data" cssClass="hiddenForm">
				<input name="<portlet:namespace />insertRecordsJson" type="text" />
			</aui:form>
			
			<aui:row>
				<aui:col width="100">
					<aui:fieldset label="Errors will be shown below. Cells with errors will be highlighted">
						<div id="reviewErrorsDiv">
							<!-- Left blank intentionally -->
						</div>
					</aui:fieldset>
				</aui:col>
			</aui:row>	
		</c:if>
	
	<c:if test="${errorCount > 0 }">
		<div class="alert alert-danger">Could not process ${errorCount } rows. Please check that your csv has ${columnAmount } columns.</div>
	</c:if>
</aui:container>
<script>
	$(document).ready(function(){
		
		//delete this row
		$('.deleteButton').on("click", function(){
			$(this).closest('tr').remove();
		});
		
		//iterate on each row and commit them to the database
		//On Save Changes button click
		$('#<portlet:namespace />reviewDataButton').on('click', function(){
			var validationPassed = true;
			
			//clear error list
			$('#reviewErrorsDiv').empty();
			$('td').removeClass('errorTd');
			
			//Check each import row record for validation!
			$('.importRecord').each(function(index, element){
				var validLengths1 = validateLengthLimit('.recordStudentId', element);
				var validLengths2 = validateLengthLimit('.recordVisitReason', element);
				var validLengths4 = validateLengthLimit('.recordSubmitterId', element);
				var validLengths5 = validateLengthLimit('.recordMeetingDate', element);
				var validLengths6 = validateLengthLimit('.recordLocation', element);
				var validLengths7 = validateLengthLimit('.recordRequired', element);
				var validLengths3 = validateLengthLimit('.recordCourseNumber', element);
				

				//All the length limits have passed
				if(validLengths1 && validLengths2 && validLengths3 && validLengths4 && validLengths5 && validLengths6 && validLengths7)
				{
					console.log("row #" + index + "is valid");
				} else {
					//once a single row does not pass validation, then set validation passed to false
					console.log("row #" + index + "is not valid");
					validationPassed = false;
				}
			});
			
			//If validationPassed, then you can submit to back end
			if(validationPassed){
				//no error, show success message
				var node = document.createElement('div');
				node.classList = "alert alert-success";
				var textNode = document.createTextNode("No errors found. Click Commit Changes in order to push the changes to the database");
				node.appendChild(textNode);
				
				var errorDiv = document.getElementById('reviewErrorsDiv');
				errorDiv.appendChild(node);
				
				//$('#<portlet:namespace />UsersToEdit').val(JSON.stringify(users));
				
				$('button[id$=commitDataButton]').prop('disabled', false);
			} else {
				$('button[id$=commitDataButton]').prop('disabled', true);
			}
		});
		
		//Commit records to the database
		$('#<portlet:namespace />commitDataButton').on('click', function(){
			//put rows into json
			var records = [];
    	
	    	//convert each row to a javascript object. The substring is needed to make sure it fits within the sql
	    	$('.importRecord').each(function(){
	    		var record = {};
				
	    		record.studentId = $(this).find('.recordStudentId').text() == undefined ? "" : $(this).find('.recordStudentId').text();
	    		record.submitterId = $(this).find('.recordSubmitterId').text() == undefined ? "" : $(this).find('.recordSubmitterId').text();
	    		record.meetingDate = $(this).find('.recordMeetingDate').text() == undefined ? "" : $(this).find('.recordMeetingDate').text();
	    		record.required = $(this).find('.recordRequired').text() == undefined ? "" : $(this).find('.recordRequired').text();
	    		record.courseNumber = $(this).find('.recordCourseNumber').text() == undefined ? "" : $(this).find('.recordCourseNumber').text();
	    		record.location = $(this).find('.recordLocation').text() == undefined ? "" : $(this).find('.recordLocation').text();
	    		record.visitReason = $(this).find('.recordVisitReason').text() == undefined ? "" : $(this).find('.recordVisitReason').text();
	    		records.push(record);
	    	});
	    	console.log("Records Found:");
	    	console.log(records);
	    	$('input[name$=insertRecordsJson]').val(JSON.stringify(records));
	    	$('form[name$=recordsInsertForm]').submit();
			//submit the json to the commit action command
		});
		
		//If someone edits a td, the commit button must be disabled
		$("td").on( "keydown",function(event) {
		       $('button[id$=commitDataButton]').prop('disabled', true);
		})
		
		
		//////////////////////////
		//Helper functions
		//////////////////////////
		
		//Appends to the reviewErrorsDiv
		function appendError(error){
			if(error.length>0){
				var node = document.createElement('div');
				node.classList = "alert alert-danger";
				var textNode = document.createTextNode(error);
				node.appendChild(textNode);
				
				var errorDiv = document.getElementById('reviewErrorsDiv');
				errorDiv.appendChild(node);
			}
		}
		
		//Return true if length limit is valid, otherwise return false
		//Adds error styling and error messsaging
		function validateLengthLimit(classToCheck, rowElem){
			//if we can pass in a blank value then don't do any of the other checks
			var canBeEmpty = false;
			
			//Length limits
			var STUDENT_ID_LENGTH = 9;
			var VISIT_REASON_LENGTH = 20;
			var CRN_LENGTH = 5;
			var SUBMITTER_LENGTH = 20;
			var MEETING_DATE_LENGTH = 40;
			var LOCATION_LENGTH = 20;
			
			//regex checks
			var STUDENT_ID_REG_EX = /^\d+$/; //numbers only
			var DATE_REG_EX = /^\d{4}[\/](0[1-9]|1[012])[\/](0[1-9]|[12][0-9]|3[01]) ([0-2][0-9]):([0-5][0-9]):([0-5][0-9])$/; // mm/dd/yyyy hh:mm (24 hour format)
			var SUBMITTER_REG_EX = /^[a-zA-Z0-9]*$/; //alphanumeric only
			var CRN_REG_EX = /^\d+$/; //numbers and digits only
			
			//only certain valid strings
			var REQUIRED_VALID_STRINGS = ["false", "true"];
			var LOCATION_VALID_STRINGS = ["Zoom", "BC 163", "Digital", "Shiley"];
			
			var tdText = $(rowElem).find(classToCheck).text();
			
			//Error wording
			var lengthError =  tdText + " is too long. Limit is ";
			var regExError = tdText + " is not in the correct format";
			var stringError = tdText + " is not valid. You can only enter these: ";
			
			var lengthToCheck = -1;
			var regExCheck = "";
			var stringsToCheck = [];
				
			switch(classToCheck){
				case '.recordStudentId':
					lengthToCheck = STUDENT_ID_LENGTH;
					regExCheck = STUDENT_ID_REG_EX;
					break;
				case '.recordVisitReason':
					lengthToCheck = VISIT_REASON_LENGTH;
					break;
				case '.recordSubmitterId':
					lengthToCheck = SUBMITTER_LENGTH;
					break;
				case '.recordMeetingDate':
					lengthToCheck = MEETING_DATE_LENGTH;
					regExCheck = DATE_REG_EX;
					break;
				case '.recordLocation':
					lengthToCheck = LOCATION_LENGTH;
					stringsToCheck = LOCATION_VALID_STRINGS;
					break;
				case '.recordRequired':
					stringsToCheck = REQUIRED_VALID_STRINGS;
					break;
				case '.recordCourseNumber':
					lengthToCheck = CRN_LENGTH;
					regExCheck = CRN_REG_EX;
					canBeEmpty = true;
					break;
				default:
					console.log("Class '"+ classToCheck + "' not found, therefore no validation");
					break;
			}
			
			//this is the value we're returning, set to true if is valid, set to false if not-valid
			var errorCount = 0;
			
			if(canBeEmpty && (tdText.length == 0 || tdText.length == undefined)){
				console.log("Not validating on " + classToCheck + " because it's empty");
			} else {
				//LENGTH CHECK
				//If lengthToCheck is -1, then don't do the validation
				if((tdText.length > lengthToCheck) && (lengthToCheck > -1)){
					lengthError = lengthError + lengthToCheck;
					appendError(lengthError);
					errorCount++;
				}
				
				//REGEX VALIDATION
				if(regExCheck.toString().length > 0){
					if(!(regExCheck.test(tdText))){
						appendError(regExError);
						errorCount++;
					} 
				}
				
				//SPECIFIC STRING CHECK
				//If stringsToCheck is empty, then we don't need to do validation
				if(stringsToCheck.length > 0){
					var validStringBool = false;
					
					//Check all strings in the array and see if our text value equals one in the array
					//If no matches found then add error
					for(var i=0;i<stringsToCheck.length;i++){
						var checkThis = stringsToCheck[i];
						if(tdText == checkThis){
							validStringBool = true;
						} 
					}
					
					//If not valid string, then add errors
					if(!validStringBool){
						stringError = stringError + stringsToCheck;
						appendError(stringError);
						errorCount++;
					}
				}	
			}
			
			var isValid = true;
			if(errorCount > 0){
				console.log("Adding error to:");
				console.log($(rowElem).find(classToCheck));
				$(rowElem).find(classToCheck).addClass("errorTd");
				isValid = false;
			}
			
			//TODO:delete me
			//console.log("-------");
			
			return isValid;
		}
		
		
	});
</script>