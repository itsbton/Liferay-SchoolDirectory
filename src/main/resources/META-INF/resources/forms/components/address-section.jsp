<aui:fieldset-group markupView="lexicon">
	<aui:fieldset label="Address">
		<aui:input label="Address Line 1" name="addressline1" type="text" value="${record.addressLine1 }"/>
		<aui:input label="Address Line 2" name="addressline2" type="text" value="${record.addressLine2 }"/>
		<aui:input label="State" name="state" type="text" value="${record.state }"/>
		<aui:input label="Zip" name="zip" type="text"  value="${record.zipCode }"/>
	</aui:fieldset>
</aui:fieldset-group>