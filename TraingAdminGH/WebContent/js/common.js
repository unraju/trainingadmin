<SCRIPT language="JavaScript">
var grouparray = new Array();
var selectedColumns = new Array();

function fillAvailableColumns() {

	var selectedgroup = document.getElementById('groupselect').value;

	var selectedgroupobject = grouparray[selectedgroup];

	if(selectedgroupobject && selectedgroupobject.columns != null){
		var selectedgroupcolumns = selectedgroupobject.columns;

		document.getElementById('availablecolumns').length = 0;
		for (var i in selectedgroupcolumns) {
			var displaycolumnobject = selectedgroupcolumns[i];
			if(!checkIfAlreadySelected(displaycolumnobject.uid)) {
				addCloumnOption(displaycolumnobject);
			}
		}
	} else {// setting available columns length to '0' in case of selected group
			// is ' Select Group'
		document.getElementById('availablecolumns').length = 0;
	}

}

</SCRIPT>