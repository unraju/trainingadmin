function doCheckStartOver(){
	quiescePrinter();
	window.location='checkAuthorizationDisplay.do?action=display';
}

function doManageOTBStartOver(){
	window.location='manageOtbAccountDisplay.do?action=display&checkTransactionTO.newTransaction=true';
}

function doStartCheckAuthorization(){
	window.location='checkAuthorizationDisplay.do?action=display';
}

function doCAStartOver(){
	window.location='creditCardDataDisplay.do?cardTransactionTO.newTransaction=true';
}

function doCAVoidStartOver(){
	window.location='voidTransactionCardDataDisplay.do';
}

function doCARePrintStartOver(){
	window.location='rePrintTransactionCardDataDisplay.do';
}

function doChReprintReceiptStartOver(){
	window.location='chReprintReceiptIDCardDataDisplay.do';
}

function doBillpayStartOver(){
	window.location='invoiceDataDisplay.do?action=display';
}

function quiescePrinter(){		
	if (top.top_epsonFrame.document.applets["PrinterApplet"]) {
		top.top_epsonFrame.document.applets["PrinterApplet"].doClosePrinter();
	}
}

function openWin(the_url,width,height) {
	var the_x = (width) ? width : 640;
	var the_y = (height) ? height : 480;
	the_x -= 0;
	the_y -= 0;
	var how_wide = screen.availWidth;
	var how_high = screen.availHeight;
	var the_toolbar = "no";
	var the_addressbar = "no";
	var the_directories = "no";
	var the_statusbar = "no";
	var the_menubar = "no";
	var the_scrollbars = "no";
	var the_do_resize = "no";
	var the_copy_history = "no";
	top_pos = (how_high / 2) - (the_y / 2);
	left_pos = (how_wide / 2) - (the_x / 2);
	if (window.outerWidth) {
		var option =
			"toolbar="
				+ the_toolbar
				+ ",location="
				+ the_addressbar
				+ ",directories="
				+ the_directories
				+ ",status="
				+ the_statusbar
				+ ",menubar="
				+ the_menubar
				+ ",scrollbars="
				+ the_scrollbars
				+ ",resizable="
				+ the_do_resize
				+ ",outerWidth="
				+ the_x
				+ ",outerHeight="
				+ the_y
				+ ",copyhistory="
				+ the_copy_history
				+ ",left="
				+ left_pos
				+ ",top="
				+ top_pos;
		site = open(the_url, "DisplayWindow", option);
		var Opera = (navigator.userAgent.indexOf('Opera') != -1);
		if (Opera) {
			site.resizeTo(the_x, the_y);
			site.moveTo(0, 0);
		}
	} else {
		var option =
			"toolbar="
				+ the_toolbar
				+ ",location="
				+ the_addressbar
				+ ",directories="
				+ the_directories
				+ ",status="
				+ the_statusbar
				+ ",menubar="
				+ the_menubar
				+ ",scrollbars="
				+ the_scrollbars
				+ ",resizable="
				+ the_do_resize
				+ ",Width="
				+ the_x
				+ ",Height="
				+ the_y
				+ ",copyhistory="
				+ the_copy_history
				+ ",left="
				+ left_pos
				+ ",top="
				+ top_pos;
		site = open('', "DisplayWindow", option);
		site.location = the_url;
		if (site.open) {
			site.focus();
			return false;
		}
		site.resizeTo(the_x, the_y);
	}
}

/** 
	Function to display a progress message using a succession of 1 - 10 dots repeating.
	This functions assumes that there is an element whose id is 'progressMsg' on the document.
*/
var progressTimer = -1;
var progressLimit = 10;
var dots = '';
var saveMsg = '';
function progressMsg(message) {
	if (message != saveMsg) {
		stopProgressMsg();
		dots = '';
		saveMsg = message;
	}
	dots += '.';
	if (dots.length > 10) {
		dots = '';
	}   
	progressTimer = setTimeout('progressMsg("' + message + '")',500);
	var msgField = document.getElementById('progressMsg');
	if (msgField) {
		msgField.innerHTML = '<strong>' + message + dots + '</strong>';
	} else {
		return;
	}
}

function stopProgressMsg() {
   clearTimeout(progressTimer);
}





function maskPhone(phone) {
	if(checkPhoneDigitFormat(phone.value)){
		formatTele(phone);
	}	
}

function checkPhoneFormat(phoneValue){
	var phoneformat = /^\d{3}-\d{3}-\d{4}$/;
	return phoneformat.test(phoneValue);
}

function checkPhoneDigitFormat(digitValue){
	var digitformat = /^\d{10}$/;
	return digitformat.test(digitValue);
}

function formatTele(teleField) {
	var tele = teleField.value;
	if (tele.indexOf("-") == -1) {
		if (tele.length == 10) {
			tele = tele.substr(0,3)
				+ '-'
				+ tele.substr(3,3)
				+ '-'
				+ tele.substr(6,4);
			teleField.value = tele;						
		}
	}
}


function maskDate(datefield) {
	if(checkDateDigitFormat(datefield.value)){
		formatDate(datefield);
	}
}

function checkDateFormat(dateValue){
	var dateformat = /^\d{2}\/\d{2}\/\d{4}$/;
	return dateformat.test(dateValue);
}

function checkDateDigitFormat(digitValue){
	var digitformat = /^\d{8}$/;
	return digitformat.test(digitValue);
}
function reformatDate(input) {
	var dateValue = input.value;
	var dateformat = /^\d{1}\/\d{1}\/\d{4}$/;
	var dateformat1 = /^\d{1}\/\d{2}\/\d{4}$/;
	var dateformat2 = /^\d{2}\/\d{1}\/\d{4}$/;
	
	if(dateValue.length==0 || !(dateformat.test(dateValue) || dateformat1.test(dateValue) || dateformat2.test(dateValue))) {	
		return;
	}
	var val;
	var arr = dateValue.split("/");
	
	if(!isNaN(arr[0]) && arr[0].length ==1){
		val = "0" + arr[0];		
	} else {
		val = arr[0];
	}
	if(!isNaN(arr[1]) && arr[1].length ==1){
		val = val + "/0" + arr[1];
	} else {
		val = val + "/" + arr[1];
	}
	val = val + "/" + arr[2];
	input.value=val;
		
}

function formatDate(dateField) {
	var dateValue = dateField.value;
	if (dateValue.indexOf("/") == -1) {
		if (dateValue.length == 8) {
			dateValue = dateValue.substr(0,2)
				+ '/'
				+ dateValue.substr(2,2)
				+ '/'
				+ dateValue.substr(4,4);
			dateField.value = dateValue;						
		}
	}
}


function maskZip(zip) {
	if(checkZipDigitFormat(zip.value)){
		formatZip(zip);
	}	
}

function checkZipDigitFormat(digitValue){
	var digitformat = /^\d{9}$/;
	return digitformat.test(digitValue);
}

function formatZip(zipField) {
	var zipcode = zipField.value;
	if (zipcode.indexOf("-") == -1) {
		if (zipcode.length == 9) {
			zipcode = zipcode.substr(0,5)
				+ '-'
				+ zipcode.substr(5,4);
			zipField.value = zipcode;						
		}
	}
}
function formatCurrency(fld, e) {
var	milSep = ','
var decSep='.'
  var sep = 0;
  var key = '';
  var i = j = 0;
  var len = len2 = 0;
  var strCheck = '0123456789';
  var aux = aux2 = '';
  var whichCode = (window.Event) ? e.which : e.keyCode;

  if (whichCode == 13) return true;  // Enter
  if (whichCode == 8) return true;  // Delete
  key = String.fromCharCode(whichCode);  // Get key value from key code
  if (strCheck.indexOf(key) == -1) return false;  // Not a valid key
  len = fld.value.length;
  for(i = 0; i < len; i++)
  if ((fld.value.charAt(i) != '0') && (fld.value.charAt(i) != decSep)) break;
  aux = '';
  for(; i < len; i++)
  if (strCheck.indexOf(fld.value.charAt(i))!=-1) aux += fld.value.charAt(i);
  aux += key;
  len = aux.length;
  if (len == 0) fld.value = '';
  if (len == 1) fld.value = '0'+ decSep + '0' + aux;
  if (len == 2) fld.value = '0'+ decSep + aux;
  if (len > 2) {
    aux2 = '';
    for (j = 0, i = len - 3; i >= 0; i--) {
      if (j == 3) {
        aux2 += milSep;
        j = 0;
      }
      aux2 += aux.charAt(i);
      j++;
    }
    fld.value = '';
    len2 = aux2.length;
    for (i = len2 - 1; i >= 0; i--)
    fld.value += aux2.charAt(i);
    fld.value += decSep + aux.substr(len - 2, len);
  }
  return false;
}



function formatCurrency1(num) {
		
		num = num.toString().replace(/\$|\,/g,'');
		if(isNaN(num))
		num = "0";
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num*100+0.50000000001);
		cents = num%100;
		num = Math.floor(num/100).toString();
		if(cents<10)
		cents = "0" + cents;
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
		num.substring(num.length-(4*i+3));
	return (((sign)?'':'-') + num + '.' + cents);
}


function formatCurrency(field) {
		var num = field.value;
		num = num.toString().replace(/\$|\,/g,'');
		if(isNaN(num))
		num = "0";
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num*100+0.50000000001);
		cents = num%100;
		num = Math.floor(num/100).toString();
		if(cents<10)
		cents = "0" + cents;
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
		num.substring(num.length-(4*i+3));
		
		if(isNaN(field.value) || field.value == "" ){

			field.value = ""
		}
		else
		{ 
			field.value = (((sign)?'':'-') + num + '.' + cents);
		}
}
function formatCurrency2(field) {
		var num = field.value;
		num = num.toString().replace(/\$|\,/g,'');
		if(isNaN(num))
		num = "0";
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num*100+0.50000000001);
		cents = num%100;
		num = Math.floor(num/100).toString();
		if(cents<10)
		cents = "0" + cents;
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
		num.substring(num.length-(4*i+3));
		field.value = (((sign)?'':'-') + num + '.' + cents);
}
function formatDecimals(field) {
		var num = field.value;
		num = num.toString().replace(/\$|\,/g,'');
		if(isNaN(num)){
		num = "0";
		}
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num*100+0.50000000001);
		cents = num%100;
		num = Math.floor(num/100).toString();
		if(cents<10)
		cents = "0" + cents;
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
		num.substring(num.length-(4*i+3));
		
		var num1 = field.value;
		//num1 = num1.toString().replace(/^\s+|\s+$/g,"");		
		num1 = num1.toString().replace(/\$|\,/g,'');
		if(isNaN(num1) || num1== "" || Math.abs(num1) == 0){
			field.value = "";			
		}
		else
		{ 	
			field.value = (((sign)?'':'-') + num + '.' + cents);
			
		}	
		
}

function formatFeeDecimals(field) {
		var num = field.value;
		num = num.toString().replace(/\$|\,/g,'');
		if(isNaN(num)){
		num = "0";
		}
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num*100+0.50000000001);
		cents = num%100;
		num = Math.floor(num/100).toString();
		if(cents<10)
		cents = "0" + cents;
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
		num.substring(num.length-(4*i+3));
		
		var num1 = field.value;
		//num1 = num1.toString().replace(/^\s+|\s+$/g,"");		
		num1 = num1.toString().replace(/\$|\,/g,'');
		if(isNaN(num1) || num1== ""){
			field.value = "";			
		}
		else
		{ 	
			field.value = (((sign)?'':'-') + num + '.' + cents);
			
		}	
		
}

/* Duplicate Methods - This does not wipe out the invalid amount fields  */
function formatFeeDecimalsNoWipeout(field) {
		var num = field.value;
		num = num.toString().replace(/\$|\,/g,'');
		if(isNaN(num)){
		num = "0";
		}
		sign = (num == (num = Math.abs(num)));
		num = Math.floor(num*100+0.50000000001);
		cents = num%100;
		num = Math.floor(num/100).toString();
		if(cents<10)
		cents = "0" + cents;
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+','+
		num.substring(num.length-(4*i+3));
		
		var num1 = field.value;
		//num1 = num1.toString().replace(/^\s+|\s+$/g,"");		
		num1 = num1.toString().replace(/\$|\,/g,'');
		if(isNaN(num1) || num1== ""){
			//field.value = "";			
		}
		else
		{ 	
			field.value = (((sign)?'':'-') + num + '.' + cents);
			
		}		
}

/* VInay - commented as was duplicated... 
function validatenumber(event, obj){

    var code = (event.which) ? event.which : event.keyCode;
    var character = String.fromCharCode(code);
 	var val = obj.value;
 	
    if ((code >= 48 && code <= 57))
    { 
            return true;
    
    }
    else if (code == 45)
    { // Check negative sign
    
      if (obj.value.indexOf("-") < 0)
        return true;
           
    }
    else if (code == 46)
    { // Check dot
      if (obj.value.indexOf(".") < 0)
        return true;
    }
    else if (code == 8)
    { // Allow backspace
      return true;
    }
    else if (code >=37 && code <= 40)
    { // Allow directional arrows
      return true;
    }
    
 
    return false;
  }
  */
function maskSSN(ssn) {
	if(checkSSNDigitFormat(ssn.value)){
		formatSSN(ssn);
	}	
}

function checkSSNFormat(ssnValue){
	var ssnformat = /^\d{3}-\d{2}-\d{4}$/;
	return ssnformat.test(ssnValue);
}

function checkSSNDigitFormat(digitValue){
	var digitformat = /^\d{9}$/;
	return digitformat.test(digitValue);
}

function formatSSN(ssnField) {
	var ssn = ssnField.value;
	if (ssn.indexOf("-") == -1) {
		if (ssn.length == 9) {
			ssn = ssn.substr(0,3)
				+ '-'
				+ ssn.substr(3,2)
				+ '-'
				+ ssn.substr(5,4);
			ssnField.value = ssn;						
		}
	}
}

function prepareHints(idArr) {
	
	if(idArr) {	
		for (var i=0; i<idArr.length; i++){
			var input = document.getElementById(idArr[i]);
			// test to see if the hint span exists first
			if (input.parentNode.getElementsByTagName("span")[0]) {
			
				// the span exists!  on focus, show the hint
				input.onmouseover = function () {
				
					this.parentNode.getElementsByTagName("span")[0].style.display = "block";
				}
				// when the cursor moves away from the field, hide the hint
				input.onmouseout = function () {
					this.parentNode.getElementsByTagName("span")[0].style.display = "none";
				}
			}
		}
	}	
}

function validatenumber(event, obj){

    var code = (event.which) ? event.which : event.keyCode;
    var character = String.fromCharCode(code);
 	var val = obj.value;
	//alert(code);
    if ((code >= 48 && code <= 57))
    { 
     	 	return true;
    }
    else if (code == 45)
    { // Check negative sign
    
      if (obj.value.indexOf("-") < 0)
      {obj.value = "-" + obj.value;
        return false;
        }
        return false;
           
    }
    else if (code == 46)
    { // Check dot
      if (obj.value.indexOf(".") < 0)
        return true;
    }
    else if (code == 8)
    { // Allow backspace
      return true;
    }
    else if (code >=37 && code <= 40)
    { 
      // VINAY - COMMENTED, direction keys DO NOT have a ASCII code.
      // Allow directional arrows
      //return true;
    }

 
    return false;
  }

function removeExtraDigitsAftrTwoDec(event, obj){
	var objVal = obj.value;
	var index = objVal.indexOf(".");
	if(index != -1){
		var strLength = index + 3; 
		var decStr = objVal.substring(index+1);
		if(decStr.length > 2){
			obj.value= objVal.substring(0,strLength);

		}
	}
}
function disableCopyPaste(evt) {
	var code = (evt.which) ? evt.which : evt.keyCode;
	if(evt.ctrlKey && (code==99 || code==67 || code==118 || code==86)) {	
		evt.keyCode = null;		
	}
	return true;
}
function validateNumeric(event, obj){
	var code = (event.which) ? event.which : event.keyCode;
    var character = String.fromCharCode(code);
 	var val = obj.value;
    if ((code >= 48 && code <= 57)) { 
     	 	return true;
    }
    else if (code == 45 || code == 46 || code == 8){ // Check negative sign
        return false;
    }
    
    return false;
}

//String Trim functions
function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}
function ltrim(stringToTrim) {
	return stringToTrim.replace(/^\s+/,"");
}
function rtrim(stringToTrim) {
	return stringToTrim.replace(/\s+$/,"");
}

function textCounter(field, countfield, maxlimit) {

if (field.value.length > maxlimit) 
{

field.value = field.value.substring(0, maxlimit);
} else {

 countfield.value = maxlimit - field.value.length;
 }
}

//function to get next sibling
function getNextSibling(startBrother){
    endBrother=startBrother.nextSibling;
    while(endBrother.nodeType!=1){
        endBrother = endBrother.nextSibling;
    }

    return endBrother;
}

//function to get first Child
function getFirstchild(n) {
    var x=n.firstChild;  
    while (x.nodeType!=1) {
        x=x.nextSibling;
    }

    return x;
}

//function to get last Child
function get_lastchild(n) {
    var x=n.lastChild;
    while (x.nodeType!=1) {
        x=x.previousSibling;
    }

    return x;
}

function checkSpace(event){
	var code = (event.which) ? event.which : event.keyCode;
	if(code != 32){
		return true;
	}
	return false;
}

function imageSizeDiff(logoWidth, logoHeight, maxLogWidth, maxLogoHeight) {

	var maxWidth = maxLogWidth;
	var maxHeight = maxLogoHeight;

	var widthDiff;
	var heightDiff;
	var widthDiffPercentage = 0;
	var	heightDiffPercentage = 0
	var	sizeDiffPercentage = 0;
	var x = parseInt(logoWidth);
	var y = parseInt(logoHeight);	
	
	if ( x > maxWidth ) {
		widthDiff = x - maxWidth ;
		widthDiffPercentage = widthDiff/x;
	}
	if ( y > maxHeight ) {
		heightDiff = y - maxHeight ;
		heightDiffPercentage = heightDiff/y;
	}
	
	if( widthDiffPercentage > heightDiffPercentage ) 
		sizeDiffPercentage = widthDiffPercentage;
	else 
		sizeDiffPercentage = heightDiffPercentage;

	return sizeDiffPercentage;
}

function scaleImage(imgObj, maxLogWidth, maxLogoHeight) {

	if(loadImageBoolean) {
		var logoImage = new Image();
		logoImage.src = imgObj.src;
		var logoImageWidth= imgObj.width;
		var logoImageHeight= imgObj.height;
		var sizeDiffPercentage = imageSizeDiff (logoImageWidth, logoImageHeight, maxLogWidth, maxLogoHeight);
		
		if(sizeDiffPercentage > 0 ) {
			logoImageWidth = logoImageWidth - logoImageWidth * sizeDiffPercentage;
			logoImage.width = logoImageWidth; 
		 	logoImageHeight = logoImageHeight - logoImageHeight * sizeDiffPercentage;
		 	logoImage.height = logoImageHeight;
		}
		
		loadImageBoolean = false;	
	 	imgObj.src = logoImage.src;
	 	imgObj.width = logoImage.width;
	 	imgObj.height = logoImage.height;
	 	imgObj.style.visibility="visible";
 	}
 }
 
 function validateAmount(event, obj){

    var code = (event.which) ? event.which : event.keyCode;
    var character = String.fromCharCode(code);
 	var val = obj.value;
	//alert(code);
    if ((code >= 48 && code <= 57))
    { 
     	 	return true;
    }    else if (code == 46)
    { // Check dot
      if (obj.value.indexOf(".") < 0)
        return true;
    }
    else if (code == 8)
    { // Allow backspace
      return true;
    }
    return false;
  }
 