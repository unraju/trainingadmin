
/**
 *
 * Filename: Common.js
 * Description:This file is used for all client side javascript functionality.
 * Date: 9/27/2002
 * Copyright: Copyright (c) 2002
 * Company: Mastek Ltd
 * version 1.0
 */


//Variable Decalarations for Various button types to be called from footer buttons
// in setAction Calls
var fldSearch ="ListSearch";
var fldNew="Add";
var fldUpdate="Update";
var fldDelete="Delete";
var fldClear="Clear";
var fldSubmit="Create";
var fldReset="Reset";
var fldCancel="Cancel";
var fldDetail ="Search";


var flagCLEAR="C";
var flagUPDATE="U";
var flagINSERT="I";
var flagDISPLAY="X";
var flagDELETE="D";
var popup_hide="1";
var popup_show="0";
var fldSpace = "";

var NUMERICS = 1;
var ALPHA = 2;
var UPPER = 4;
var LOWER = 8;
var SPACE = 16;
var DASH = 32;
var SLASH = 64;
var APOSTROPHE = 128 ;
var EMAILAT = 256;
var COMMA = 512;
var AMPERSAND = 1024;
var PERIOD = 2048;
var POUND = 4096;
var UNDERSCORE = 8192;
var PLUS = 16384; 
var ALL = 32768;
var COLON = 65536;
var DOT = 66;

var ThirteenZerosCheck = 10000000000000;
var ThreeZerosCheck = 1000;
var HundredCheck = 100;
var FiveZerosCheck = 100000;

//Following flags are for the checkDecimalAndPrecision function
flagExtraDecimal = 0;
flagOnlyDecimal = 1;
flagNonNumeric = 2;
flagMaxLimit = 3;
flagFieldValid = 4;

//following flags are added for checkDecimalWithPrecision function
flagExceedPecisionLimit = 5;
flagExceedNumericLimit = 6;
flagIsBlank = 7;
flagIsValid = 8;


/* Block Added by jimmy on 28/1/2003 for Date Validation */
// Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=3099;


function isInteger(s){
 var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}
 
function stripCharsInBag(s, bag){
 var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}
 
function daysInFebruary (year){
 // February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
 for (var i = 1; i <= n; i++) {
  this[i] = 31
  if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
  if (i==2) {this[i] = 29}
   } 
   return this
}
 
function isDate(dtStr){
 var daysInMonth = DaysArray(12)
 var pos1=dtStr.indexOf(dtCh)
 var pos2=dtStr.indexOf(dtCh,pos1+1)
 var strDay=dtStr.substring(0,pos1)
 var strMonth=dtStr.substring(pos1+1,pos2)
 var strYear=dtStr.substring(pos2+1)
 strYr=strYear
 if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
 if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
 for (var i = 1; i <= 3; i++) {
  if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
 }
 month=parseInt(strMonth)
 day=parseInt(strDay)
 year=parseInt(strYr)
 if (pos1==-1 || pos2==-1){
  //alert("The date format should be : mm/dd/yyyy")
  return false;
 }
 if (strMonth.length<1 || month<1 || month>12){
  //alert("Please enter a valid month")
  return false;
 }
 if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
  //alert("Please enter a valid day")
  return false;
 }
 if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
  //alert("Please enter a valid 4 digit year between "+minYear+" and "+maxYear)
  return false;
 }
 if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
  //alert("Please enter a valid date")
  return false;
 }
return true;
}
 




function getWindowHandle() {
	
	var win;

	if(opener) win=opener;
	else win = window.dialogArguments;

	return win;
}

// gives focus on the selected edit box
function showFocus(field,msg) {
	alert(msg)
	field.focus()
	field.select()
}

function CHMisEmpty(field)
{
  // Language : JavaScript
  // accepts  : textField
  // returns  : boolean (true , false)
  // does     : checks whether the given input
  // box is empty
  // Returns true if string s is empty or
  // whitespace characters only.

  // whitespace characters
  var whitespace = " \b\t\n\r";
  theInput = field.value ;
  theLength = theInput.length;

  // Is the text field empty?
  if((theInput == null) || (theLength == 0))
  {
    //alert ("return false");
    // indeed te text field is empty.
    return (true);
  }
  // Search through string's characters one by one
  // until we find a non-whitespace character.
  // When we do, return false; if we don't, return true.

  for (var i = 0; i < theLength ; i++)
  {
    // Check that current character isn't whitespace.
    var theChar = theInput.charAt(i);
    if (whitespace.indexOf(theChar) == -1)
    {
      return (false);
    }
    //else
    //{
    //  return (true);
    //}

  }//for loop ends

  // All characters are whitespace.
  return (true);
}// function isEmpty ends 











/* this function trims the leading and trailing spaces of a string*/
function trim(str)
{

while(''+str.charAt(0)==' ') str=str.substring(1,str.length);

while(''+str.charAt(str.length-1)==' ') str=str.substring(0,str.length-1);

return str;
}

/*
	Purpose:      This function will get the value of the checkbox/radio button from the list.
	              It will also get the values of all other fields mentioned in the parameter.
	Parameters:   formName - This is the name of the form which contains the checkbox/radio button.
	              fieldName - This is the name of the checkbox/radio button.
	              otherFields - This is the array of fieldnames whose value is to be retrieved. If not, pass null.
	Return Value: It returns the two-dimensional array of values of all fields where the checkbox/radio button.
	              Each element of the array contains a pointer to all the values of a particular checkbox.
	Note:         All the checkboxes/radio should have the same name.
*/
function CHMgetValues(formName, fieldName, otherFieldNames)
{
 if(otherFieldNames)
 {
   var retValues = new Array();
   var len = 0;
   if(eval('document.'+formName+'.'+fieldName))  
     len = eval('document.'+formName+'.'+fieldName).length;
   
   if(len != 0 && len == null )
   {
     retValues[0] = new Array();
     retValues[0][0] = eval('document.'+formName+'.'+fieldName).value;
     for(var j=1; j<=otherFieldNames.length; j++)
       retValues[0][j] = eval('document.'+formName+'.'+otherFieldNames[j-1]).value;
   }
   else
   {
     var i=0;
     for(var cnt=0; cnt < len; cnt++)
     {
         retValues[i] = new Array();
         retValues[i][0] = eval('document.'+formName+'.'+fieldName)[cnt].value;
         for(var j=1; j<=otherFieldNames.length; j++)
           retValues[i][j] = eval('document.'+formName+'.'+otherFieldNames[j-1])[cnt].value;

         i++;
     }
   }
 			
   return retValues;
 }
 }

/**
* @author Jimmy Shah
* Used by the buttons of button_pallete to set the action field
*/

function setActionMany(frm_form, a_fieldName, frm_target)
      {
		  
          var fieldName;
		  fieldName =  frm_form.name + a_fieldName;
		  frm_form.target=frm_target;
		  frm_form.action.value = fieldName;
		  frm_form.actionevent.value = fieldName;
		    frm_form.submit();
  		}

/**
* @author Devesh Rao 
* Opens a Page onto a new Browser ..the second function opens a browser of given size
*/
function openBrowse(url)
{
	var title = "" ; //Browse" + parseInt(Math.random()*999) + "Window" + parseInt(Math.random()*999);
	var style = 'toolbar=no,location=no,copyhistory=no,scrollbars=no,resizable=no';
	style += 'top=' + (screen.height - 410) + ',left=' + (screen.width - 580) + ',width=1,height=1';
	win_hndl = window.open(url,title, style);
	//window.resizeTo(1,1);
	win_hndl.focus();
	return win_hndl
}
/**
* @author Devesh Rao 
* Opens a Page onto a new Browser ..the second function opens a browser of given size
*/
function openBigBrowse(url)
{
	var title = "Browse" + parseInt(Math.random()*999) + "Window" + parseInt(Math.random()*999);

	var style = 'toolbar=no,location=no,copyhistory=no,scrollbars=yes,resizable=no,';
	style += 'top=' + (screen.height - 410) + ',left=' + (screen.width - 580) + ',width=900,height=600';
	
	win_hndl = window.open(url,title, style);
	win_hndl.focus();
	return win_hndl
}

/**
* @author Jimmy Shah
* Used by the buttons of button_pallete to set the action field
*/

function setAction(frm_form, a_fieldName, frm_target)
        {
          var fieldName;
		  frm_form.target=frm_target;
		  frm_form.action.value = frm_form.name + frm_form.actiontype.value;
		  frm_form.actionevent.value = frm_form.name + frm_form.actiontype.value;
		  frm_form.submit();
		  return false;
		  
		}
/**
* @author Ashwini Nadkarni
*  Called to check if the field entered is not blank
*  returns true if the fiels comprises of only spaces or does not contain anything
*/
function checkIfBlank(frm_field)
{
	var frm_field_val=frm_field.value
	var len=frm_field_val.length;
	
	var count=0;
	if (len==0)
	{
		return true;
	}
	for(i=0;i<len;i++)
	{
		if (frm_field_val.charAt(i)==" ")
		{
			count++;	
		}
	}
	if (count==len)
	{
		return true;
	}
		return false;
}
/*
* @author Devesh H Rao
* Function checks the value of the field and returns empty string in case of null field .
* If the debug flag is true then it returns the field value unchanged, else changes the value.
*/
function replaceNullDropDown(strFieldValue){
	var debugFlag = false;
	if(debugFlag!=true){
		if((strFieldValue=='0') || (strFieldValue=='')||(strFieldValue=='null')){
		  return fldSpace;
		}
		else{
			return strFieldValue;
		}
	}
	else{
		return strFieldValue;
	}
}
/*
* @author Devesh H Rao
* Function checks the value of the field and returns empty string in case of null field .
* If the debug flag is true then it returns the field value unchanged, else changes the value.
*/
function replaceNull(strFieldValue){
	var debugFlag = false;
	if(debugFlag!=true){
		if(strFieldValue=='null'){
		  return '';
		}
		else{
			return strFieldValue;
		}
	}
	else{
		return strFieldValue;
	}
}

/**
* Function returns true if the text box parameter passed is a number
*/
function CHMisNumber(theInputField)
{

  theInput = theInputField.value;
  theLength = theInput.length ;
  for (var i = 0 ; i < theLength ; i++)
  {
	//check if number field contains alphabets or spaces
    if (theInput.charAt(i) < '0' || theInput.charAt(i) > '9')
    {
	   return false;
	}
  }// for ends
  return true;
}// function isNumber ends

function isDecimalWithPrecision(theInputField, fieldname, allowedTotalDigits, allowedDigitsAfterDecimal)
{
  if(isDecimal(theInputField, fieldname))
  {
	var actualDigitsBeforeDecimal = "";
    var actualDigitsAfterDecimal = "";

    var allowedDigitsAfterDecimal = (allowedDigitsAfterDecimal)?allowedDigitsAfterDecimal:0;
	var allowedDigitsBeforeDecimal = allowedTotalDigits - allowedDigitsAfterDecimal;

	var theInputValue = theInputField.value;

    var idx = theInputValue.indexOf(".");

    if(idx != -1) 
	{
      actualDigitsBeforeDecimal = theInputValue.substring(0,idx).length;
	  actualDigitsAfterDecimal = theInputValue.substring(idx+1).length;
	}
	else
	{
      actualDigitsBeforeDecimal = theInputValue.substring(0).length;
	  actualDigitsAfterDecimal = 0;
    }

	if(actualDigitsBeforeDecimal > allowedDigitsBeforeDecimal)
    {
	   alert(getErrorMsg(8,fieldname,allowedDigitsBeforeDecimal));
	   return (false);
    }
	else if(actualDigitsAfterDecimal > allowedDigitsAfterDecimal)
    {
	   alert(getErrorMsg(9,fieldname,allowedDigitsAfterDecimal));
	   return (false);
    }

    return true;
  }
  else
    return false;
}
/**
  * Function which verifies whether text supplied to it is email
  * @param text - String 
  * @return boolean - true if valid / false if not
  */
function isEmail( field )
  {
	text = field.value;
	//alert('inside isEmail');
	if(isEmpty(field))
	  {
		//alert('Empty Text Box');
		return true;
	  }
	else
	{
			var i;
			var index;
			for( i = 0; i < text.length; i++ )
			{
			  oneChar = text.charAt( i );
			  if ( ! isCharValid( oneChar, PERIOD|ALPHA|NUMERICS|EMAILAT|DASH|UNDERSCORE|PLUS ) )
			  {
				return false;
			  }
			}
			if( ( index = text.indexOf( '@' ) ) == -1 ) 
			  {
				return false;
			  }
			var user = text.substring( 0, index );
			var domain = text.substring( index, text.length );
			if ( domain.indexOf( '@', 1 ) != -1 ) 
			  {
				return false;
			  }
			if ( ( ( index = domain.indexOf( '.' ) ) == -1 ) ||( user == "" ) )
			{
			  return false ;
			}
			var suffix = domain.substring(domain.lastIndexOf('.') +1);
			//if(! isTLD(suffix) ){
			//	alert(BADEMAIL);
			//  field.focus();
			//	field.select();
			//  return false;
		//	}
			while( index != -1 )
			{
			  if ( ( index == 0 ) || ( index == domain.length - 1 ) )
			  {
				return false;
			  }
			  if ( domain.charAt( index + 1 ) == '.' )
			  {
				return false;
			  }
			  index++;
			  index = domain.indexOf( '.', index );
			}
			 //alert('Valid Email Id');
			 return true;
	}
  } //isEmail

 /**
  * Function which verifies whether text supplied to it is phone
  * @param text - String 
  * @return boolean - true if valid / false if not
  */
  function CHMisPhone( field )
  {
	text = field.value;
	if(isEmpty(field))
	  {
		//alert('Empty Text Box');
		return true;
	  }
	else
	{
		var i;
		var oneChar;
		for( i = 0; i < text.length; i++ )
		{
		  oneChar = text.charAt( i );
		  if ( ! isCharValid( oneChar, DASH|NUMERICS ) )
		  {
			alert(getErrorMsg(11));
			field.focus();
			field.select();
			return false;
		  }
		}
		text = removeNonDigits( text );
		if( text.length <6 )
		  {
			alert(getErrorMsg(12));
			field.focus();
			field.select();
			return false;
		  }
		  
		//alert('Valid Phone Number');
		return true;
	}
  }//end of isPhone
/**
* @author Bharat Sukhwani
* This function checks whether the input field has a valid percent value.
* Returns false if the input is not a valid percent value, returns true otherwise.
*  Calls the isDecimal function.
-- input :- formField (e.g. document.formName.fieldName)
*/
function CHMisPercent(theInputField){
	 theInput = theInputField.value;
	if(theInput>100){
		alert("Percentage cannot be greater than 100");
		theInputField.focus();
		theInputField.select();
		return false;
	}
	return isDecimal(theInputField);
}


  // Language : JavaScript
  // In Parameters : The Field whose Value is to be checked if it is a decimal
  // Out Parameters : Boolean (true-if value passed is decimal, false-if value passed is not decimal)
  // Purpose : Checks whether the given value of the input is decimal or not. If the  value is not 
  // decimal then an alert is generated and the focus is brought back on the field. However if 
  // there is nothing in the field, this function returns "True" . 

function isDecimal(theInputField)
{

  theInput = theInputField.value;
  theLength = theInput.length ;
  var dec_count = 0 ;

  for (var i = 0 ; i < theLength ; i++)
  {
    if(theInput.charAt(i) == '.')
      // the text field has decimal point entry
    {
      dec_count = dec_count + 1;
    }
  }
 //check if number field contains more than one decimal points '.'
  if(dec_count > 1)
  {
	alert('The field cannot contain two decimal points');
	theInputField.focus();
	theInputField.select();
    return(false);
  }
  //check if decimal field contains just a  '.'
  if (dec_count == 1 && dec_count == theLength)
  {
	alert('The field cannot contain just a decimal');
	theInputField.focus();
	theInputField.select();
  	return false;
  }
  for (var i = 0 ; i < theLength ; i++)
  {
	//check if decimal field contains laphabets or spaces
    if (theInput.charAt(i) < '0' || theInput.charAt(i) > '9')
    {
	  if(theInput.charAt(i) == '-'){
		  continue;
	  }
      if(theInput.charAt(i) != '.')
      {
       alert("This field cannot contain alphabets or spaces");
       theInputField.focus();
	   theInputField.select();
       return(false);
      }
    }
  }// for ends
  return (true);
}// function isDecimal ends

/*  This function compares the  value of the input text box with the passd value and returns true if the value of the textbox is graeter than the passed value, false otherwise
*/
function checkGreater(field , val)
{
	if(field.value > val)
	{
		return true;
	}
	else
	{
		field.focus();
		field.select();
		return false;
	}
}


  // Language : JavaScript
  // In Parameters : The Field whose Value is to be checked if it is a decimal
  // Out Parameters : Boolean (true-if value passed is decimal, false-if value passed is not decimal)
  // Purpose : Checks whether the given value of the input is decimal or not. If the  value is not 
  // decimal then an alert is generated and the focus is brought back on the field. However if 
  // there is nothing in the field, this function returns "True" . 



function checkDecimalAndPrecision(theInputField, val)
{

  theInput = theInputField.value;
  theLength = theInput.length ;
  var dec_count = 0 ;

  for (var i = 0 ; i < theLength ; i++)
  {
    if(theInput.charAt(i) == '.')
      // the text field has decimal point entry
    {
      dec_count = dec_count + 1;
    }
  }

 //check if number field contains more than one decimal points '.'
  if(dec_count > 1)
  {
	//alert('The field cannot contain two decimal points');
	theInputField.focus();
	theInputField.select();
    return flagExtraDecimal;
  }
  //check if decimal field contains just a  '.'
  if (dec_count == 1 && dec_count == theLength)
  {
	//alert('The field cannot contain just a decimal');
	theInputField.focus();
	theInputField.select();
  	return flagOnlyDecimal;
  }
  for (var i = 0 ; i < theLength ; i++)
  {
	//check if decimal field contains laphabets or spaces
    if (theInput.charAt(i) < '0' || theInput.charAt(i) > '9')
    {
      if(theInput.charAt(i) != '.' && theInput.charAt(i) != ',')
      {
   //    alert("This field cannot contain alphabets or spaces");
       theInputField.focus();
	   theInputField.select();
       return flagNonNumeric;
      }
    }
  }// for ends
	if(theInputField.value >= val)
	{
   //    alert("This field cannot be greater than or equal to" + val);
		theInputField.focus();
		theInputField.select();
		return flagMaxLimit;
	}
	else
	{
		return flagFieldValid;
	}
}

/**
* checkDecimalWithPrecesion 
* Ashish Gokhale
* 27/12/2002
*
*/
function checkDecimalWithPrecesion (field, totalDecimal, allowedPrecision)
{

	//alert('inside checkDecimalWithPrecision');
  var value = field.value;
  if (value.length == 0) {
    //value is empty / field is blank
	return flagIsBlank;
  } 
  
  if (isDecimal(field)) 
  {
    dindex = value.indexOf(".", 0);
	var s1 = value;
    var adj = 0;
	
	if (dindex > 0) {
	  s1 = value.substring (0, dindex);
	  var adj = (dindex > 0)?1:0;

	  var s2 = value.substring (dindex + 1, value.length);

	  if (s2.length > allowedPrecision) {
	    return flagExceedNumericLimit;
	  }
	} 
	
	if (s1.length > (totalDecimal - allowedPrecision + adj)) {
	  //alert ("Exceeds allowed numerical length");	
	  return flagExceedPecisionLimit;
	}
  }
  else
	{
	  return flagIsBlank;
	}
  return flagIsValid;  
}

//Following commented by Vinaysheel to put new function that removes hardcoded alerts
/*
function checkDecimalAndPrecision(theInputField, val)
{

  theInput = theInputField.value;
  theLength = theInput.length ;
  var dec_count = 0 ;

  for (var i = 0 ; i < theLength ; i++)
  {
    if(theInput.charAt(i) == '.')
      // the text field has decimal point entry
    {
      dec_count = dec_count + 1;
    }
  }
 //check if number field contains more than one decimal points '.'
  if(dec_count > 1)
  {
	alert('The field cannot contain two decimal points');
	theInputField.focus();
	theInputField.select();
    return(false);
  }
  //check if decimal field contains just a  '.'
  if (dec_count == 1 && dec_count == theLength)
  {
	alert('The field cannot contain just a decimal');
	theInputField.focus();
	theInputField.select();
  	return false;
  }
  for (var i = 0 ; i < theLength ; i++)
  {
	//check if decimal field contains laphabets or spaces
    if (theInput.charAt(i) < '0' || theInput.charAt(i) > '9')
    {
      if(theInput.charAt(i) != '.' && theInput.charAt(i) != ',')
      {
       alert("This field cannot contain alphabets or spaces");
       theInputField.focus();
	   theInputField.select();
       return false;
      }
    }
  }// for ends
	if(theInputField.value >= val)
	{
       alert("This field cannot be greater than or equal to" + val);
		theInputField.focus();
		theInputField.select();
		return false;
	}
	else
	{
		return true;
	}
}// function isDecimal ends
*/
/**
* @author Ashwini Nadkarni
*  Called to check if the combox value is selected
* returns false if not selected
*/
function checkIfSelected(frm_field)
{
	if (frm_field.options.selectedIndex==0)
	{	
		//selct is selected
		return false;
	}
	else
		return true;
}

/**
* @author Ashwini Nadkarni
* function to check if firstdt is greater than or equal to second
*/
function compareDt(firstdt,seconddt)
{
//compares if firstdate is greater than or equal to seconddate
	var day1=firstdt.substring(0,2)
	var  month1=firstdt.substring(3,5)
	var year1=firstdt.substring(6);
	var day2=seconddt.substring(0,2)
	var month2=seconddt.substring(3,5)
	var year2=seconddt.substring(6);
	

   if (year1 > year2) return true;
   else if (year1 < year2) return false;
   else if (month1 > month2) return true;
   else if (month1 < month2) return false;
   else if (day1 < day2) return false;
   else return true;
}

/**
* @auther Ashish Gokhale
* 
* function to increment the number of months ina date by given number
*/
function addMonths (dt, months)
{
	var month=Number(dt.substring(0,2));
	var day=Number(dt.substring(3,5));
	var year=Number(dt.substring(6));
	
	d = new Date (year, month - 1, day);
	d.setMonth(month - 1 + Number(months));

	var newMonth = d.getMonth() + 1;
	var newDay = d.getDate();
	var newYear = d.getFullYear();

	if(newMonth.length !=2)
	{
		newMonth='0'+ newMonth;
	}

	if(newDay.length !=2)
	{
		newDay ='0'+ newDay;
	}

	return (newMonth + "/" + newDay + "/" + newYear);

}

/**
* checkDecimalWithPrecesion 
* Ashish Gokhale
* 27/12/2002
*
*/
/*function checkDecimalWithPrecesion (field, totalDecimal, allowedPrecision)
{
  var value = field.value;
  if (value.length == 0) {
    //value is empty / field is blank
	return flagIsBlank;
  } 
  
  if (isDecimal(field)) 
  {
    dindex = value.indexOf(".", 0);
	var s1 = value;
    var adj = 0;
	
	if (dindex > 0) {
	  s1 = value.substring (0, dindex);
	  var adj = (dindex > 0)?1:0;

	  var s2 = value.substring (dindex + 1, value.length);

	  if (s2.length > allowedPrecision) {
	    return flagExceedNumericLimit;
	  }
	} 
	
	if (s1.length > (totalDecimal - allowedPrecision + adj)) {
	  //alert ("Exceeds allowed numerical length");	
	  return flagExceedPecisionLimit;
	}
  }
  return flagIsValid;  
}*/

/**
* @author Ashwini Nadkarni
* function to check if firstdt is greater than or equal to second
*/
function compareDtForEqual(firstdt,seconddt)
{
//compares if firstdate is greater than or equal to seconddate
	var day1=firstdt.substring(0,2)
	var month1=firstdt.substring(3,5)
	var year1=firstdt.substring(6);
	var day2=seconddt.substring(0,2)
	var month2=seconddt.substring(3,5)
	var year2=seconddt.substring(6);
	

   if (year1 > year2) return true;
   else if (year1 < year2) return false;
   else if (month1 > month2) return true;
   else if (month1 < month2) return false;
   else if (day1 <= day2) return false;
   else return true;
}


/**
* @author Ashwini Nadkarni
*  Called by  the links of the list page to set the name of the action, value of the key and to submit the page.
*  It also sets the target of the form so as to open the target JSP in the same page or in a 
*	new page(i.e. as a pop-up) 
*  Calls the checkMandatory function to check if all the mandatory fields of the form are entered.
*/
function setActionAndSubmit(frm_form,actionName,keyField,frm_target)	{
		frm_form.target=frm_target;
		frm_form.action.value=actionName;
		frm_form.actionevent.value = actionName;
		frm_form.strPKey.value=keyField;
		frm_form.submit();
		return false;
	}
/**
* @author Ashwini Nadkarni
*  Called by  all the buttons ( and few links such as Search etc.) to set the name of the action and to 
*  It also sets the target of the form so as to open the target JSP in the same page or in a 
*	new page(i.e. as a pop-up) 
*  Calls the checkMandatory function to check if all the mandatory fields of the form are entered
*  submit the page.
*/
	function setActionOnlyAndSubmit(frm_form,actionName,frm_target)	{
	/*	if(checkMandatory(frm_form)==false){
			return false;
		}
		*/
		frm_form.target=frm_target;
		frm_form.action.value=actionName;
		frm_form.actionevent.value=actionName;
		frm_form.submit();
		return false;
	}




/*
   Purpose:       This function is used for select all facility. i.e., checking
                  one checkbox should all others and vice-versa
   Parameters:    obj - This is the checkbox object which is used for select all facility.
                  formName - This is the name of the form which contains the check boxes.
                  fieldName - This is the name of the checkbox which will be affected by select all facility.
   Note:          All the checkboxes that are affected by the select all checkbox should have the same name.                   
*/   

function CHMcheckUncheckAll(isCheck, formName, fieldName)
{
    var obj = eval('document.'+formName+'.'+fieldName);
	var len ;
	
	if(obj) len = obj.length;
	else return;
	
	if( len == null )
		{
			if(! obj.disabled)
			{
				obj.checked = isCheck;
				changeStatus(formName,fieldName,obj)
			}
		}
	else
	{
		for(var lvar_Cnt=0; lvar_Cnt < len; lvar_Cnt++)
		{
		   if(! obj[lvar_Cnt].disabled) {
			   obj[lvar_Cnt].checked = isCheck;
   			   changeStatus(formName,fieldName,obj[lvar_Cnt])
		   }
		}
	}
	return;
}


/*
    Purpose:    This function is used in select all scenario.
                Even if one of the checkbox is unchecked than the select all checkbox should also be unchecked.
    Parameters: obj - This is the checkbox object which is used for select all facility.
                formName - This is the name of the form which contains the select all check box.
                fieldName - This is the name of the select all checkbox.
*/		
function CHMtestCheckAll(obj, formName, fieldName)
{
	changeStatus(formName,obj.name,obj);
    if(obj.checked == false)
      eval('document.'+formName+'.'+fieldName).checked=false;
	
  return;
}
			
/*
    Purpose:    This function is used in select all scenario.
                Even if one of the checkbox is unchecked than the select all checkbox should also be unchecked.
    Parameters: obj - This is the checkbox object which is used for select all facility.
                formName - This is the name of the form which contains the select all check box.
                fieldName - This is the name of the select all checkbox.
*/		
function testCheckAllCheck(obj, formName, fieldName)
{
  if(obj.checked == false)
    eval('document.'+formName+'.'+fieldName).checked=false;
	
  return;
}




/*** All this functions are related to calendar popup ***/

/* 
   Purpose:    This function will open the calendar popup.
               It will fill the textbox on the form with the date selected.
   Parameters: obj - It is the textbox object in which we want to save the date. */
   
var winModalWindow;   // Global variable for calender window.
var dateObj;     // Global variable for calender date field.
function CHMshowCalender(obj)
{
  if (window.showModalDialog)
  {
    cal=window.showModalDialog('./common/popup/calender.htm','','dialogWidth:12.5;dialogHeight:17.25;scrollbars:no;status:no;');
	
	if(!cal)
	{
 		return false;
	}
		obj.value=cal;
  }
  else
  {
    try{
		window.top.captureEvents (Event.CLICK|Event.FOCUS)
		window.top.onclick=IgnoreEvents
		window.top.onfocus=HandleFocus 
		winModalWindow = window.open ("../popup/calender.htm","ModalChild","dependent=yes,width=225,height=275")
		winModalWindow.focus()
		dateObj = obj;
	}
	catch(exception){
		alert(exception);
	}
  }
  
  return;
}

/*  It is called if the browser is Netscape. 
    The calender popup will call it to set the date in in your field. */
function CHMnetscapeDate(value)
{
  dateObj.value=value;
  return;
}



/* It will ignore the focus on parent window tii the child window is active. */
function CHMHandleFocus()
{
  if (winModalWindow)
  {
    if (!winModalWindow.closed)
    {
      winModalWindow.focus()
    }
    else
    {
      window.top.releaseEvents (Event.CLICK|Event.FOCUS)
    }
  }
  return false
}


/*  
    Purpose:      It opens a child modal window.
    Parameters:   pageName - The URL of the page you want to open in the modal window.
                  width - The width of the modal dialog.
                  height - The height of the modal dialog.  */
function CHMopenModalDialog(pageName,left,top,height,width)
{
  if(window.showModalDialog)
  {
     return window.showModalDialog(pageName,window,'dialogWidth:'+width/13+';dialogHeight:'+height/13+';scrollbars=no;status:no;dialogTop=' + top + ';dialogLeft=' + left + ';');
  }
  else
  {
    try{
		window.top.captureEvents (Event.CLICK|Event.FOCUS)
		window.top.onclick=IgnoreEvents
		window.top.onfocus=HandleFocus 
		winModalWindow = openChildWindow(pageName,width,height);
		winModalWindow.focus()
	}
	catch(exception){
		alert(exception);
	}  
  }
  return;
}


/*
   Purpose:    This function will call a page mentioned in the parameter in the targetWindow.
   Parameters: pageName - The name of the page to be opened.
               targetWindow - The window object where the page will be called.  */
function changeDocument(pageName, targetWindow)
{
  if(targetWindow == null) targetWindow = window;
  targetWindow.location.href=pageName;
}






/** ALL THE FUNCTIONS FOR SELECTING MASTERS **/

var objChannelTypeInvokerTextBox // This will store the text box object which called the channel type master.
var objChannelTypeInvokerLabel // This will store the label object which called the channel type master.

var objDesignationInvokerTextBox // This will store the text box object which called the designation master.
var objDesignationInvokerLabel // This will store the label object which called the designation master.

var objExamInvokerTextBox // This will store the text box object which called the exam master.
var objExamInvokerLabel // This will store the label object which called the exam master.

var objCommissionTypeInvokerTextBox // This will store the text box object which called the commission type master.
var objCommissionTypeInvokerLabel // This will store the label object which called the commission type master.

var objProductInvokerTextBox // This will store the text box object which called the product type master.
var objProductTypeInvokerLabel // This will store the label object which called the product master.
var objProductTypeInvokerHidden  // This will store the hidden type object which called the product master.

var objContractInvokerTextBox // This will store the text box object which called the contract type master.
var objContractTypeInvokerHidden  // This will store the hidden type object which called the contract master.

var objAgentCodeInvokerTextBox // This will store the text box object which called the channel type master.
var objAgentNameInvokerLabel // This will store the label object which called the channel type master.

/*
   Purpose:   It will show the master of all the channel types. Selected master will be displayed in the field passed as parameter.
   Parameter: objTextBox - The field which will display the selected value.
              objDescription - The field which wil display the description.*/   
function showChannelTypes(objTextBox, objLabel)
{
  objChannelTypeInvokerTextBox = objTextBox;
  objChannelTypeInvokerLabel = objLabel;
  openModalDialog('../popup/cm_channel_search_listing.htm',screen.width-50,'400');
  
  return;
}

/*
   Purpose:     It is a callback function called by modal window. It will be called when the child wants to return the result.
   Parameters:  code - The exam code selected by the user on the modal window.
                description - The description associated with the code.  */
function setSelectedChannelType(code, description)
{
  if( code != null && code != '')
  {
     objChannelTypeInvokerTextBox.value = code; 
     if( objChannelTypeInvokerLabel != null)  objChannelTypeInvokerLabel.innerHTML = description;
  }
  return;
}



/*
   Purpose:   It will show the master of all the designation codes. Selected master will be displayed in the field passed as parameter.
   Parameter: objTextBox - The field which will display the selected value.
              objDescription - The field which wil display the description.  */
function showDesignations(objTextBox, objLabel)
{
  objDesignationInvokerTextBox = objTextBox;
  objDesignationInvokerLabel = objLabel;
  openModalDialog('../popup/cm_designation_search_listing.htm',screen.width-50,'400');
  return;
}

/*
   Purpose:     It is a callback function called by modal window. It will be called when the child wants to return the result.
   Parameters:  code - The exam code selected by the user on the modal window.
                description - The description associated with the code.  */
function setSelectedDesignation(code, description)
{
  if( code != null && code != '')
  {
     objDesignationInvokerTextBox.value = code; 
     if( objDesignationInvokerLabel != null)  objDesignationInvokerLabel.innerHTML = description;
  }
  return;
}

/*
   Purpose:   It will show the master of all the exam/seminar codes. Selected master will be displayed in the field passed as parameter.
   Parameter: objTextBox - The field which will display the selected value.
              objDescription - The field which wil display the description.  */
function showExams(objTextBox, objLabel)
{
  objExamInvokerTextBox = objTextBox;
  objExamInvokerLabel = objLabel;
  
  openModalDialog('../popup/cm_exam_search_listing.htm',screen.width-50,'400');
  return;
}

/*
   Purpose:     It is a callback function called by modal window. It will be called when the child wants to return the result.
   Parameters:  code - The exam code selected by the user on the modal window.
                description - The description associated with the code.  */
function setSelectedExam(code, description)
{
  if( code != null && code != '')
  {
     objExamInvokerTextBox.value = code; 
     if( objExamInvokerLabel != null)  objExamInvokerLabel.value = innerHTML;
  }
  return;
}

/*
   Purpose:   It will show the master of all the products. Selected master will be displayed in the field passed as parameter.
   Parameter: objTextBox - The field which will display the selected value.
              objDescription - The field which wil display the description.
              objHiddens - The array field which will store the hidden values. */   
function showProducts(objTextBox, objLabel, objHiddens)
{
  objProductInvokerTextBox = objTextBox;
  objProductInvokerLabel = objLabel;
  objProductInvokerHidden = objHiddens;
  
  openModalDialog('../popup/cm_products_search_listing.htm',screen.width-50,'400');
  
  return;
}

/*
   Purpose:     It is a callback function called by modal window. It will be called when the child wants to return the result.
   Parameters:  code - The exam code selected by the user on the modal window.
                description - The description associated with the code.  
                otherValues - Extra values returned by the master popup. */
function setSelectedProduct(code, description, otherValues)
{
  if( code != null && code != '')
  {
     objProductInvokerTextBox.value = code; 
     if( objProductInvokerLabel != null)
     {
       if (objProductInvokerLabel.type == 'text') objProductInvokerLabel.value = description;
       else objProductInvokerLabel.innerHTML = description;
     }
     
     for(var i=0; objProductInvokerHidden != null && i<objProductInvokerHidden.length; i++)
       objProductInvokerHidden[i].value = otherValues[i];
  }

  return;
}

/*   Purpose:   It will show the master of all the products. Selected master will be displayed in the field passed as parameter.*/
 function showProductsWithMultipleOption()
{
  openModalDialog('../popup/cm_multiple_products_search_listing.htm',screen.width-50,'400');
  
  return;
}

/*
   Purpose:   It will show the master of all the commission types. Selected master will be displayed in the field passed as parameter.
   Parameter: objTextBox - The field which will display the selected value.
              objDescription - The field which will display the description.*/
function showCommissionTypes(objTextBox, objDescription)
{
  objCommissionTypeInvokerTextBox = objTextBox;
  objCommissionTypeInvokerLabel = objDescription;
  openModalDialog('../popup/cm_commission_type_search_listing.htm',screen.width-50,'400');
  
  return;
}

/*
   Purpose:     It is a callback function called by modal window. It will be called when the child wants to return the result.
   Parameters:  code - The exam code selected by the user on the modal window.
                description - The description associated with the code.  */
function setSelectedCommissionType(code, description)
{
  if( code != null && code != '')
  {
     objCommissionTypeInvokerTextBox.value = code; 
     if( objCommissionTypeInvokerLabel != null )
     {
       if( objCommissionTypeInvokerLabel.type == 'text') objCommissionTypeInvokerLabel.value = description;
       else objCommissionTypeInvokerLabel.innerHTML = description;
     }
  }
  return;
}

/*
   Purpose:   It will show the master of all the contracts. Selected master will be displayed in the field passed as parameter.*/
function showContractsWithMultipleOption()
{
/*  openModalDialog('../popup/cm_multiple_contracts_search_listing.htm',screen.width-50,'400');*/
openBigBrowse('../cm_pre_multiple_contracts_list.jsp');
  return;
}


/*
   Purpose:   It will show the master of all the contracts. Selected master will be displayed in the field passed as parameter.*/
function showContracts()
{
 openModalDialog('../popup/cm_contracts_search_listing.htm',screen.width-50,'400');
  
  return;
}


/*
   Purpose:   It will show the master of all the agents. Selected master will be displayed in the field passed as parameter.
   Parameter: objTextBox - The field which will display the selected value.
              objDescription - The field which wil display the description.*/   
function showAgents(objTextBox, objLabel)
{
  objChannelTypeInvokerTextBox = objTextBox;
  objChannelTypeInvokerLabel = objLabel;
  openModalDialog('../popup/cm_agent_search_listing.htm',screen.width-50,'400');
  
  return;
}

/*
   Purpose:     It is a callback function called by modal window. It will be called when the child wants to return the result.
   Parameters:  code - The exam code selected by the user on the modal window.
                description - The description associated with the code.  */
function setSelectedAgent(code, description)
{
  if( code != null && code != '')
  {
     if(objAgentCodeInvokerTextBox) objAgentCodeInvokerTextBox.value = code; 
     if(objAgentNameInvokerLabel)   objChannelNameInvokerLabel.innerHTML = description;
  }
  return;
}


/** SELECTING MASTERS FUNCTIONS OVER **/


/** ALL THE FUNCTIONS FOR ADDING ROWS DYNAMICALLY **/




/*
    Purpose:       It deletes a row from the table.
    Parameters:    oTableBody - The handle to the body of the table, whose row is to be deleted.
                   formName - The name of the form which contains the checkbox.
                   checkBoxName - The name of the delete checkbox. All delete checkboxes should have the same name.
                   cntOfCells - Number of cells contained in the row being deleted.  */
function CHMdeleteRow(oTableBody,formName,checkBoxName,cntOfCells)
{
  // Loop through rows
  var i = 0;
  
  for( i=0; oTableBody.rows.length >= 1 && i<eval('document.'+formName+'.'+checkBoxName).length;i++)
  {
    if(eval('document.'+formName+'.'+checkBoxName)[i].checked)
    {  
      oTableRow = oTableBody.rows[i];
      for(;cntOfCells>0;cntOfCells--)
      {
        oTableRow.deleteCell(0);
      }
      oTableRow = oTableBody.deleteRow(i);
      i-=1;
    }
  }
  if(oTableBody.rows.length==1 && eval('document.'+formName+'.'+checkBoxName).checked)
  {
    oTableRow = oTableBody.rows[0];
    for(;cntOfCells>0;cntOfCells--)
    {
      oTableRow.deleteCell(0);
    }
    oTableRow  = oTableBody.deleteRow(0);
  }
}



        
var totalRowsInWorkExp=5;   // This will count the number of rows added in work exp. 
var totalColsInWorkExp=2;  // Number of cols in the row in work exp.
        
var totalRowsInDocuments=5;   // This will count the number of rows added in docs. 
var totalColsInDocuments=4;  // Number of cols in the row in docs.
        
var totalRowsInLanguage=5;   // This will count the number of rows added in languages 
var totalColsInLanguage=3;  // Number of cols in the row in languages

var totalRowsInChildren = 2;  // This will count the number of rows added in children
var totalColsInChildren = 6;  // Number of cols in the row in children

var totalRowsInHousehold = 2;  // This will count the number of rows added in household
var totalColsInHousehold = 6;  // Number of cols in the row in household

            
/*  This is called on clicking of add button. It creates an array of cells to be added in the row. 
    It then calls the function to actually create a row with all the required parameters.*/
function addWorkExpRow(oWorkExpTableBody, formName)
{
  var cellArray = new Array(); // Array of cell contents
  var cellArrayWidth = new Array();  //Array of cell width
      
  cellArray[0] = '<INPUT TYPE="checkbox" ID="cb_check_workexp_details" NAME="cb_check_workexp_details" ONCLICK="testCheckAll(this,\''+formName+'\',\'cb_workexp_checkall\');"  VALUE="tr'+totalRowsInWorkExp+'" CLASS="engTablbl">';
  cellArrayWidth[0] = '5%';
        
  cellArray[1] = '<INPUT TYPE="text" ID="atb_work'+totalRowsInWorkExp+'_company_name" NAME="atb_work'+totalRowsInWorkExp+'_company_name" CLASS="engTablbl" SIZE="20">';
  cellArrayWidth[1] = '20%';
        
  cellArray[2] = '<INPUT TYPE="text" ID="atb_work'+totalRowsInWorkExp+'_position" NAME="atb_work'+totalRowsInWorkExp+'_position" CLASS="engTablbl" SIZE="15">';
  cellArrayWidth[2] = '15%';
        
  cellArray[3] = '<INPUT TYPE="text" ID="dtb_work'+totalRowsInWorkExp+'_from" NAME="dtb_work'+totalRowsInWorkExp+'_from" CLASS="engTablbl" SIZE="10">  <A HREF="" ONCLICK="showCalender(document.'+formName+'.dtb_work'+totalRowsInWorkExp+'_from);return false;"><IMG SRC="../protoreview/protoreview/images/Date.gif" ALT="Date" BORDER="0" WIDTH="25" HEIGHT="17"></A>';
  cellArrayWidth[3] = '15%';
        
  cellArray[4] = '<INPUT TYPE="text" ID="dtb_work'+totalRowsInWorkExp+'_to" NAME="dtb_work'+totalRowsInWorkExp+'_to" CLASS="engTablbl" SIZE="10">  <A HREF="" ONCLICK="showCalender(document.'+formName+'.dtb_work'+totalRowsInWorkExp+'_to);return false;"><IMG SRC="../protoreview/protoreview/images/Date.gif" ALT="Date" BORDER="0" WIDTH="25" HEIGHT="17"></A>';
  cellArrayWidth[4] = '15%';
        
  cellArray[5] = '<INPUT TYPE="text" ID="atb_work'+totalRowsInWorkExp+'_business_secured" NAME="atb_work'+totalRowsInWorkExp+'_business_secured" CLASS="engTablbl" SIZE="10">';
  cellArrayWidth[5] = '15%';
        
  cellArray[6] = '<TEXTAREA ID="ata_work'+totalRowsInWorkExp+'_remarks" NAME="ata_work'+totalRowsInWorkExp+'_remarks" CLASS="engTablbl" COLS="15" ROWS="1"></TEXTAREA>';	  	  
  cellArrayWidth[6] = '15%';
      
  addRow(oWorkExpTableBody,(totalRowsInWorkExp-1),cellArray,cellArrayWidth);
  totalRowsInWorkExp++;
}
        
       
/*  This is called on clicking of add button. It creates an array of cells to be added in the row. 
    It then calls the function to actually create a row with all the required parameters.*/
function addLanguageRow(oLanguageTableBody, formName)
{
   var cellArray = new Array(); // Array of cell contents
   var cellArrayWidth = new Array();  //Array of cell width
      
   cellArray[0]= '<INPUT TYPE="checkbox" ID="cb_check_language" NAME="cb_check_language"  ONCLICK="testCheckAll(this,\''+formName+'\',\'cb_language_checkall\');" VALUE="tr'+totalRowsInLanguage+'" CLASS="engTablbl">';
   cellArrayWidth[0]= '10%';
	  
   cellArray[1]= '<SELECT ID="atb_language'+totalRowsInLanguage+'" NAME="atb_language'+totalRowsInLanguage+'" CLASS="engTablbl"><OPTION VALUE="" SELECTED>-Select-</OPTION><OPTION VALUE="English">English</OPTION><OPTION VALUE="Malay">Malay</OPTION><OPTION VALUE="Mandarin">Mandarin</OPTION><OPTION VALUE="Tamil">Tamil</OPTION></OPTION></SELECT>';
   cellArrayWidth[1] = '40%';
	  
   cellArray[2]= '<SELECT ID="add_language'+totalRowsInLanguage+'_spoken" NAME="add_language'+totalRowsInLanguage+'_spoken" CLASS="engTablbl"><OPTION VALUE="" SELECTED>-Select-</OPTION><OPTION VALUE="Poor">Poor</OPTION><OPTION VALUE="Fair">Fair</OPTION><OPTION VALUE="Fluent">Fluent</OPTION></SELECT>';
   cellArrayWidth[2] = '20%';
	  
   cellArray[3]= '<SELECT ID="add_language'+totalRowsInLanguage+'_written" NAME="add_language'+totalRowsInLanguage+'_written" CLASS="engTablbl"><OPTION VALUE="" SELECTED>-Select-</OPTION><OPTION VALUE="Poor">Poor</OPTION><OPTION VALUE="Fair">Fair</OPTION><OPTION VALUE="Fluent">Fluent</OPTION></SELECT> ';
   cellArrayWidth[3] = '30%';
	  	  
   addRow(oLanguageTableBody,(totalRowsInLanguage-1),cellArray,cellArrayWidth);
   totalRowsInLanguage++;
}        

/*  This is called on clicking of add button. It creates an array of cells to be added in the row. 
    It then calls the function to actually create a row with all the required parameters.*/
function addDocumentRow(oDocumentsTableBody, formName)
{
   var cellArray = new Array();
   var cellArrayWidth = new Array();
   
   cellArray[0] = '<INPUT TYPE="checkbox" ID="cb_check_document_details" NAME="cb_check_document_details" ONCLICK="testCheckAll(this,\''+formName+'\',\'cb_documents_checkall\');" CLASS="engTablbl">';
   cellArrayWidth[0] = '5%';
                
   cellArray[1] = '<INPUT TYPE="text" ID="atb_document'+totalRowsInDocuments+'_code" NAME="atb_document'+totalRowsInDocuments+'_code" CLASS="engTablbl" SIZE="30"> <INPUT TYPE="button" CLASS="engbtnIcon" VALUE="S">';
   cellArrayWidth[1] = '50%';
   
   cellArray[2] = '<INPUT TYPE="radio" ID="arb_document'+totalRowsInDocuments+'_mandatory" NAME="arb_document'+totalRowsInDocuments+'_mandatory" CLASS="engTablbl" VALUE="Yes" CHECKED>Yes <INPUT TYPE="radio" ID="arb_document'+totalRowsInDocuments+'_mandatory" NAME="arb_document'+totalRowsInDocuments+'_mandatory" CLASS="engTablbl" VALUE="No">No';
   cellArrayWidth[2] = '25%';
   
   cellArray[3] = '<INPUT TYPE="text" ID="ntb_document'+totalRowsInDocuments+'_reminder_period" NAME="ntb_document'+totalRowsInDocuments+'_reminder_period" CLASS="engTablbl" SIZE="4">';
   cellArrayWidth[3] = '20%';
   
   addRow(oDocumentsTableBody, (totalRowsInDocuments-1), cellArray, cellArrayWidth);
   totalRowsInDocuments++;
}

/* This is called on clicking of add button. It creates an array of cells to be added in the row. 
   It then calls the function to actually create a row with all the required parameters.*/
function addChildrenRow(oChildrenTableBody, formName)
{
  var cellArray = new Array();
  var cellArrayWidth = new Array();
                 
  cellArray[0] = '<INPUT TYPE="checkbox" ID="cb_check_children_details" NAME="cb_check_children_details" ONCLICK="testCheckAll(this,\''+formName+'\',\'cb_children_checkall\');" CLASS="engTablbl">';
  cellArrayWidth[0] = '5%';
  
  cellArray[1] = '<INPUT TYPE="text" ID="atb_child'+totalRowsInChildren+'_name" NAME="atb_child'+totalRowsInChildren+'_name" CLASS="engTablbl" SIZE="25">'
  cellArrayWidth[1] = '20%';
  
  cellArray[2] = '<SELECT ID="add_child'+totalRowsInChildren+'_gender" NAME="add_child'+totalRowsInChildren+'_gender" CLASS="engTablbl"><OPTION VALUE="Male">Male</OPTION><OPTION VALUE="Female">Female</OPTION></SELECT>';
  cellArrayWidth[2] = '10%';
  
  cellArray[3] = '<INPUT TYPE="text" ID="atb_child'+totalRowsInChildren+'_nric_type" NAME="atb_child'+totalRowsInChildren+'_nric_type" CLASS="engTablbl">';
  cellArrayWidth[3] = '15%';
  
  cellArray[4] = '<INPUT TYPE="text" ID="atb_child'+totalRowsInChildren+'_nric_number" NAME="atb_child'+totalRowsInChildren+'_nric_number" CLASS="engTablbl">';
  cellArrayWidth[4] = '15%';
  
  cellArray[5] = '<INPUT TYPE="text" ID="dtb_child'+totalRowsInChildren+'_birth_date" NAME="dtb_child'+totalRowsInChildren+'_birth_date" CLASS="engTablbl" SIZE="10">   <A HREF="" ONCLICK="showCalender(document.'+formName+'.dtb_child'+totalRowsInChildren+'_birth_date);return false;"><IMG SRC="../protoreview/protoreview/images/Date.gif" ALT="Date" BORDER="0" WIDTH="25" HEIGHT="17"></A>';
  cellArrayWidth[5] = '20%';
  
  cellArray[6] = '<SELECT ID="add_child'+totalRowsInChildren+'_health" NAME="add_child'+totalRowsInChildren+'_health" CLASS="engTablbl"><OPTION VALUE="Good">Good</OPTION><OPTION VALUE="Poor">Poor</OPTION>';
  cellArrayWidth[6] = '15%';
  
  addRow(oChildrenTableBody, (totalRowsInChildren-1), cellArray, cellArrayWidth);
  totalRowsInChildren++;
}

function addHouseholdRow(oHouseholdTableBody, formName)
{
  var cellArray = new Array();
  var cellArrayWidth = new Array();
  
  cellArray[0] = '<INPUT TYPE="checkbox" ID="cb_check_household_details" NAME="cb_check_household_details" ONCLICK="testCheckAll(this,\''+formName+'\',\'cb_household_checkall\');" CLASS="engTablbl">';
  cellArrayWidth[0] = '5%';
  
  cellArray[1] = '<INPUT TYPE="text" ID="atb_household_name" NAME="atb_household_name" VALUE="" CLASS="tbLabel" SIZE="25" READONLY>';
  cellArrayWidth[1] = '25%';
  
  cellArray[2] = '<INPUT ID="add_household_relationship" NAME="add_household_relationship" VALUE="" SIZE="10" CLASS="tbLabel" READONLY>';
  cellArrayWidth[2] = '10%';
  
  cellArray[3] = '<INPUT ID="add_household_gender" NAME="add_household_gender" VALUE="" SIZE="6" CLASS="tbLabel" READONLY>';
  cellArrayWidth[3] = '10%';
  
  cellArray[4] = '<INPUT TYPE="text" ID="atb_household_id_type" NAME="atb_household_id_type" SIZE="10" CLASS="tbLabel" READONLY>';
  cellArrayWidth[4] = '10%';
  
  cellArray[5] = '<INPUT TYPE="text" ID="atb_household_old_id_number" NAME="atb_household_old_id_number" SIZE="10" CLASS="tbLabel" READONLY>';
  cellArrayWidth[5] = '15%';
  
  cellArray[6] = '<INPUT TYPE="text" ID="atb_household_new_id_number" NAME="atb_household_new_id_number" SIZE="10" CLASS="tbLabel" READONLY>';
  cellArrayWidth[6] = '15%';
  
  cellArray[7] = '<A HREF="" ONCLICK="openModalDialog(\'../popup/cm_household_details.htm\',screen.width,\'490\'); return false;" TITLE="click to see details">Details</A>';
  cellArrayWidth[7] = '5%';
                
  addRow(oHouseholdTableBody, (totalRowsInHousehold-1), cellArray, cellArrayWidth);
  totalRowsInHousehold++;
}

/** FUNCTIONS FOR ADDING ROWS DYNAMICALLY OVER **/






function removeComma(fld) {
	var val = "";
	for (i=0; i<fld.length ; i++) {
		if(fld.charAt(i) == ","){
			continue
     	}
		else{
			val = val + fld.charAt(i)
		}
	}
	return val;
}


//********************************************************
// Removes spaces from the edit box, both leading and trailing
function removeSpaces(fld) {
		var val = ""
		l_length_of_data = fld.length;
		l_ctr=0;
		t_ctr=0;
		while(l_ctr != l_length_of_data) {
			if(fld.charAt(l_ctr) == " ") {
				l_ctr++
			}
			else{
				break;
			}
		}
		while(t_ctr != l_length_of_data) {
			if(fld.charAt(l_length_of_data - t_ctr -1) == " ") {
				t_ctr++
			}
			else{
				break;
			}
		}
		if(l_ctr > 0 || t_ctr > 0){
			for(var i=l_ctr;i < l_length_of_data-t_ctr;i++){
				val = val + fld.charAt(i)
			}
			return val
		}
		else{
			return fld;
		}
		
}


//********************************************************
// Adjust spaces such that therte will be one space between any two meaningful data.
//for eg "P1    <    1" will be displayed as "P1 < 1"
function adjustSpaces(fld) {
	var val = ""
	l_length_of_data = fld.length;
	countspace = 0
	for(ctr=0;ctr<l_length_of_data;ctr++){
		if(fld.charAt(ctr) != " ") {
			val = val + fld.charAt(ctr)
			countspace = 0;
		}
		else{
			if(countspace == 0){
				val = val + fld.charAt(ctr);
				countspace = 1;
			}
		}
	}
	return val
}




/* It will get the index of the row where the date icon was clicked 
          and the appropriate text field will be returned for displaying the selected date.
		  author :Ashwini NAdkarni*/

        function getImgFieldInArray(src,displayField,frm_name)
        {
          var retObject = eval('document.'+frm_name+'.'+displayField);
          if(retObject.length)
          {
            var anchorArray = document.anchors;
            var cnt = -1;
            for(var i=0; i<anchorArray.length; i++)
            {
              if(anchorArray[i].id == src.id)
              {
                cnt++;
                if(anchorArray[i] == src) break;
              }
            }
            return retObject[cnt];
          }
          else
			{		
			  return retObject;
			}
        }
/*function to find the source 
author :Ashwini NAdkarni*/
        function changeStatusForImgSource(src)
        {
			
          var anchorArray = document.anchors;
          var cnt = -1;
		  
          for(var i=0; i<anchorArray.length; i++)
          {
              if(anchorArray[i].id == src.id)
              {
                cnt++;
                if(anchorArray[i] == src) break;
              }
          }
		   return cnt;
        }

/*function to change status 
author :Ashwini NAdkarni*/
function changeStatusImg(form_name,chBox,src)
{
var statusFlag;
var PKValue;
var rowCont;

var obj1 = eval('document.'+form_name+'.statusFlag');
var obj2 = eval('document.'+form_name+'.PKValue');
var obj3 = eval('document.'+form_name+'.'+chBox);

if (obj1.length != null)
{

	rowCont=changeStatusForImgSource(src);
	statusFlag=obj1[rowCont].value;
	PKValue=obj2[rowCont].value;
}
else 
{
	statusFlag=obj1.value;
	PKValue=obj2.value;
}


if (obj1.length != null)
{
	if ((statusFlag=="X" || statusFlag=="U")&& obj3[rowCont].checked)
	{ 
		obj1[rowCont].value="D";
	}
	if ( (statusFlag=="X") && (! obj3[rowCont].checked) )
	{
		obj1[rowCont].value="U";
	}
	
	if (statusFlag=="I" && obj3[rowCont].checked)
	{
		obj1[rowCont].value="C";
	}
	if (statusFlag=="C" && !obj3[rowCont].checked)
	{
		obj1[rowCont].value="I";
	}
	if (statusFlag=="D" && !obj3[rowCont].checked)
	{
		obj1[rowCont].value="U";
	}
}
else
{
	if ((statusFlag=="X" || statusFlag=="U")&& obj3.checked)
	{
		obj1.value="D";
	}
	if (statusFlag=="X" && ! obj3.checked) 
	{
		obj1.value="U";
	}
	if (statusFlag=="I" && obj3.checked)
	{
		obj1.value="C";
	}
	if (statusFlag=="C" && !obj3.checked)
	{
		obj1.value="I";
	}
	if (statusFlag=="D" && !obj3.checked)
	{
		obj1.value="U";
	}
}
}



/* It will get the index of the row where the input element is clicked. */
       function getFieldInArray(src,fieldName,form_name)
        {
          var objArray = eval('document'+'.'+form_name+'.'+fieldName);
		
		  if(objArray.length)
          {
            for(var i=0; i<objArray.length; i++)
            {
               if(objArray[i] == src) break;
            }
            
            return i;
          }
          else 
			  return null;
        }

/*function to set the status */
function changeStatus(form_name,chBox,src)
{
/*
	chBox corresponds to the check box name
*/

var displayField=src.name;
var rowCont=getFieldInArray(src,displayField,form_name)

if (rowCont!=null)
{
	
	statusFlag=eval('document.'+form_name+'.statusFlag')[rowCont].value
	PKValue=eval('document.'+form_name+'.PKValue')[rowCont].value
}
else 
{
	statusFlag=eval('document.'+form_name+'.statusFlag').value
	PKValue=eval('document.'+form_name+'.PKValue').value
}


if (rowCont!=null)
{
	//alert("before statusFlag =" + eval('document.'+form_name+'.statusFlag')[rowCont].value);
	if ((statusFlag=="X" || statusFlag=="U")&& eval('document.'+form_name+'.'+chBox)[rowCont].checked)
	{ 
		eval('document.'+form_name+'.statusFlag')[rowCont].value="D";
	}
	if ((statusFlag=="X") && (! (eval('document.'+form_name+'.'+chBox)[rowCont].checked)) )
	{
		eval('document.'+form_name+'.statusFlag')[rowCont].value="U";
	}
	
	if (statusFlag=="I" && eval('document.'+form_name+'.'+chBox)[rowCont].checked)
	{
		eval('document.'+form_name+'.statusFlag')[rowCont].value="C";
	}
	if ((statusFlag=="C" && !(eval('document.'+form_name+'.'+chBox)[rowCont].checked)))
	{
		eval('document.'+form_name+'.statusFlag')[rowCont].value="I";
	}
	if ((statusFlag=="D" && !(eval('document.'+form_name+'.'+chBox)[rowCont].checked)))
	{
		eval('document.'+form_name+'.statusFlag')[rowCont].value="U";
	}
	//alert("after statusFlag =" + eval('document.'+form_name+'.statusFlag')[rowCont].value);

}
else
{
	if ((statusFlag=="X" || statusFlag=="U")&& eval('document.'+form_name+'.'+chBox).checked)
	{
		eval('document.'+form_name+'.statusFlag').value="D";
	}
	if ((statusFlag=="X") && (! (eval('document.'+form_name+'.'+chBox).checked)) )
	{
		eval('document.'+form_name+'.statusFlag').value="U";
	}
	if (statusFlag=="I" && eval('document.'+form_name+'.'+chBox).checked)
	{
		eval('document.'+form_name+'.statusFlag').value="C";
	}
	if ((statusFlag=="C" && !(eval('document.'+form_name+'.'+chBox).checked)))
	{
		eval('document.'+form_name+'.statusFlag').value="I";
	}
	if ((statusFlag=="D" && !(eval('document.'+form_name+'.'+chBox).checked)))
	{
		eval('document.'+form_name+'.statusFlag').value="U";
	}
}
}  

function changeStatusSelect(form_name,chBox,src)
        {
          var objArray = eval('document'+'.'+form_name+'.statusFlag');
		  if(objArray.length)
		  {
			changeStatus(form_name,chBox,src)
		  }
		  else{
			    statusFlag=eval('document.'+form_name+'.statusFlag').value
				if ((statusFlag=="X" || statusFlag=="U")&& eval('document.'+form_name+'.'+chBox).checked)
				{
					eval('document.'+form_name+'.statusFlag').value="D";
				}
				if ((statusFlag=="X") && (! (eval('document.'+form_name+'.'+chBox).checked)) )
				{
					eval('document.'+form_name+'.statusFlag').value="U";
				}
				if (statusFlag=="I" && eval('document.'+form_name+'.'+chBox).checked)
				{
					eval('document.'+form_name+'.statusFlag').value="C";
				}
				if ((statusFlag=="C" && !(eval('document.'+form_name+'.'+chBox).checked)))
				{
					eval('document.'+form_name+'.statusFlag').value="I";
				}
				if ((statusFlag=="D" && !(eval('document.'+form_name+'.'+chBox).checked)))
				{
					eval('document.'+form_name+'.statusFlag').value="U";
				}
			}
        }


	function changeStatusSelectForMultipleDetail(form_name,chBox,src,status)
        {
          var objArray = eval('document'+'.'+form_name+ '.' + status);
		  if(objArray.length)
		  {
			changeStatusForMultipleDetail(form_name,chBox,src,status)
		  }
		  else{
			    statusFlag=eval('document.'+form_name + '.' + status).value
				if ((statusFlag=="X" || statusFlag=="U")&& eval('document.'+form_name+'.'+chBox).checked)
				{
					eval('document.'+form_name+'.' + status).value="D";
				}
				if ((statusFlag=="X") && (! (eval('document.'+form_name+'.'+chBox).checked)) )
				{
					eval('document.'+form_name+'.' + status).value="U";
				}
				if (statusFlag=="I" && eval('document.'+form_name+'.'+chBox).checked)
				{
					eval('document.'+form_name+'.' + status).value="C";
				}
				if ((statusFlag=="C" && !(eval('document.'+form_name+'.'+chBox).checked)))
				{
					eval('document.'+form_name+'.' + status).value="I";
				}
				if ((statusFlag=="D" && !(eval('document.'+form_name+'.'+chBox).checked)))
				{
					eval('document.'+form_name+'.' + status).value="U";
				}
			}
        }




function changeStatusImgForMultipleDetail(form_name,chBox,src, status)
{
var statusFlag;

var rowCont;

var obj1 = eval('document.'+form_name+'.' +status);
//var obj2 = eval('document.'+form_name+'.PKValue');
var obj3 = eval('document.'+form_name+'.'+chBox);

if (obj1.length != null)
{

	rowCont=changeStatusForImgSource(src);
	statusFlag=obj1[rowCont].value;

}
else 
{
	statusFlag=obj1.value;

}


if (obj1.length != null)
{
	if ((statusFlag=="X" || statusFlag=="U")&& obj3[rowCont].checked)
	{ 
		obj1[rowCont].value="D";
	}
	if ( (statusFlag=="X") && (! obj3[rowCont].checked) )
	{
		obj1[rowCont].value="U";
	}
	
	if (statusFlag=="I" && obj3[rowCont].checked)
	{
		obj1[rowCont].value="C";
	}
	if (statusFlag=="C" && !obj3[rowCont].checked)
	{
		obj1[rowCont].value="I";
	}
	if (statusFlag=="D" && !obj3[rowCont].checked)
	{
		obj1[rowCont].value="U";
	}
}
else
{
	if ((statusFlag=="X" || statusFlag=="U")&& obj3.checked)
	{
		obj1.value="D";
	}
	if (statusFlag=="X" && ! obj3.checked) 
	{
		obj1.value="U";
	}
	if (statusFlag=="I" && obj3.checked)
	{
		obj1.value="C";
	}
	if (statusFlag=="C" && !obj3.checked)
	{
		obj1.value="I";
	}
	if (statusFlag=="D" && !obj3.checked)
	{
		obj1.value="U";
	}
}
}


//Adds elemets to Dropdown
function addElementToList(targetList,txt,val ) {
	targetList.options[targetList.length] = new Option(txt, val,false,true);
}


//Apply BUG FIX to remove English Select. And keep it Multilingual
function removeAll(targetList)
{
	var selectCaption =  targetList.options[0].text;
	var selectValue =  targetList.options[0].value;

	for ( i = 0; i <= targetList.length-1; i++)
  	{
        targetList.options[i] =null;
		i=i-1;
    }
	//	targetList.options[targetList.length] = new Option("Select", " ", true, true);
		targetList.options[targetList.length] = new Option(selectCaption , selectValue , true, true);
}

function postThruFrame(url){
	parent.hiddenFrame.location.href=url;
}
/*
Adding functions for Perssitency--
Code hasnt been reviewed -- VinaySheel Code to be reviewed by Vinay*/

function testCheckAllForMultipleDetail(obj, formName, fieldName, statusFlag)
{

	changeStatusForMultipleDetail(formName,obj.name,obj, statusFlag);
    if(obj.checked == false)
      eval('document.'+formName+'.'+fieldName).checked=false;
	
  return;
}


/*function to set the status */
function changeStatusForMultipleDetail(form_name,chBox,src, status)
{
/*
	chBox corresponds to the check box name
*/

var displayField=src.name;
var rowCont=getFieldInArray(src,displayField,form_name)
if (rowCont!=null)
{
	statusFlag=eval('document.'+form_name+'.' + status)[rowCont].value
}
else 
{
	statusFlag=eval('document.'+form_name+'.' + status).value
}


if (rowCont!=null)
{
	//alert("before statusFlag =" + eval('document.'+form_name+'.statusFlag')[rowCont].value);
	if ((statusFlag=="X" || statusFlag=="U")&& eval('document.'+form_name+'.'+chBox)[rowCont].checked)
	{ 
		eval('document.'+form_name+'.' + status)[rowCont].value="D";
	}
	if ((statusFlag=="X") && (! (eval('document.'+form_name+'.'+chBox)[rowCont].checked)) )
	{
		eval('document.'+form_name+'.' + status)[rowCont].value="U";
	}
	
	if (statusFlag=="I" && eval('document.'+form_name+'.'+chBox)[rowCont].checked)
	{
		eval('document.'+form_name+'.' + status)[rowCont].value="C";
	}
	if ((statusFlag=="C" && !(eval('document.'+form_name+'.'+chBox)[rowCont].checked)))
	{
		eval('document.'+form_name+'.' + status)[rowCont].value="I";
	}
	if ((statusFlag=="D" && !(eval('document.'+form_name+'.'+chBox)[rowCont].checked)))
	{
		eval('document.'+form_name+'.' + status)[rowCont].value="U";
	}
	//alert("after statusFlag =" + eval('document.'+form_name+'.statusFlag')[rowCont].value);

}
else
{
	if ((statusFlag=="X" || statusFlag=="U")&& eval('document.'+form_name+'.'+chBox).checked)
	{
		eval('document.'+form_name+'.' + status).value="D";
	}
	if ((statusFlag=="X") && (! (eval('document.'+form_name+'.'+chBox).checked)) )
	{
		eval('document.'+form_name+'.' + status).value="U";
	}
	if (statusFlag=="I" && eval('document.'+form_name+'.'+chBox).checked)
	{
		eval('document.'+form_name+'.' + status).value="C";
	}
	if ((statusFlag=="C" && !(eval('document.'+form_name+'.'+chBox).checked)))
	{
		eval('document.'+form_name+'.' + status).value="I";
	}
	if ((statusFlag=="D" && !(eval('document.'+form_name+'.'+chBox).checked)))
	{
		eval('document.'+form_name+'.' + status).value="U";
	}
}
}  

/*
	Adding functions for Perssitency--
	Code hasnt been reviewed -- 
	VinaySheel Code to be reviewed by Vinay
*/
/*
	Following function checks for a single quote in a search criteria text field. 
	  // Language : JavaScript
  // accepts  : textField
  // returns  : boolean (true , false)
  // does     : checks whether the given input
  // box contains a single quote or not
  // Returns true if no single quote
  // 
  */

function isSingleQuote(fld) 
{
	var val = "";
	for (i=0; i<fld.length ; i++) 
	{
		if(fld.charAt(i) == "'")
		{
			return false;
     	}
	}
	return true;
}

/*
	Adding functions for Perssitency--
	Code hasnt been reviewed -- 
	VinaySheel Code to be reviewed by Vinay
	Added by Vinaysheel
*/

function checkUncheckAllForMultipleDetail(isCheck, formName, fieldName, statusFlag)
{
    var obj = eval('document.'+formName+'.'+fieldName);
	var len ;
	
	if(obj) len = obj.length;
	else return;
	
	if( len == null )
	{
		if(!obj.disabled)
		{
			obj.checked = isCheck;
		}
	}
	else
	{
		for(var lvar_Cnt=0; lvar_Cnt < len; lvar_Cnt++)
		{
		   if(! obj[lvar_Cnt].disabled) {
			   obj[lvar_Cnt].checked = isCheck;
   			   changeStatusForMultipleDetail(formName,fieldName,obj[lvar_Cnt], statusFlag)
		   }
		}
	}
	return;
}


/*function to set the status */
function changeStatusForAll(form_name,chBox)
{
/*
	chBox corresponds to the check box name
*/

//var displayField=eval('document.'+form_name+'.' + chBox);
var objArray = eval('document'+'.'+form_name+'.'+chBox);
if(objArray.length)
{
   for(var i=0; i<objArray.length; i++){
		statusFlag=eval('document.'+form_name+'.statusFlag')[i].value
		//alert("before statusFlag =" + eval('document.'+form_name+'.statusFlag')[rowCont].value);
		if ((statusFlag=="X" || statusFlag=="U")&& eval('document.'+form_name+'.'+chBox)[i].checked)
		{ 
			eval('document.'+form_name+'.statusFlag')[i].value="D";
		}
		if ((statusFlag=="X") && (! (eval('document.'+form_name+'.'+chBox)[i].checked)) )
		{
			eval('document.'+form_name+'.statusFlag')[i].value="U";
		}
		
		if (statusFlag=="I" && eval('document.'+form_name+'.'+chBox)[i].checked)
		{
			eval('document.'+form_name+'.statusFlag')[i].value="C";
		}
		if ((statusFlag=="C" && !(eval('document.'+form_name+'.'+chBox)[i].checked)))
		{
			eval('document.'+form_name+'.statusFlag')[i].value="I";
		}
		if ((statusFlag=="D" && !(eval('document.'+form_name+'.'+chBox)[i].checked)))
		{
			eval('document.'+form_name+'.statusFlag')[i].value="U";
		}
		//alert("after statusFlag =" + eval('document.'+form_name+'.statusFlag')[rowCont].value);
   }
}
else
{
	statusFlag=eval('document.'+form_name+'.statusFlag').value
	if ((statusFlag=="X" || statusFlag=="U")&& eval('document.'+form_name+'.'+chBox).checked)
	{
		eval('document.'+form_name+'.statusFlag').value="D";
	}
	if ((statusFlag=="X") && (! (eval('document.'+form_name+'.'+chBox).checked)) )
	{
		eval('document.'+form_name+'.statusFlag').value="U";
	}
	if (statusFlag=="I" && eval('document.'+form_name+'.'+chBox).checked)
	{
		eval('document.'+form_name+'.statusFlag').value="C";
	}
	if ((statusFlag=="C" && !(eval('document.'+form_name+'.'+chBox).checked)))
	{
		eval('document.'+form_name+'.statusFlag').value="I";
	}
	if ((statusFlag=="D" && !(eval('document.'+form_name+'.'+chBox).checked)))
	{
		eval('document.'+form_name+'.statusFlag').value="U";
	}
}
}  

/*function to set the status */
function changeStatusForAllForMultipleDetail(form_name,chBox,status)
{
/*
	chBox corresponds to the check box name
*/

//var displayField=eval('document.'+form_name+'.' + chBox);
var objArray = eval('document'+'.'+form_name+'.'+chBox);
if(objArray.length)
{
   for(var i=0; i<objArray.length; i++){
		//statusFlag=eval('document.'+form_name+'.statusFlag')[i].value
		statusFlag=eval('document.'+form_name+'.' + status)[i].value
		//alert("before statusFlag =" + eval('document.'+form_name+'.statusFlag')[rowCont].value);
		if ((statusFlag=="X" || statusFlag=="U")&& eval('document.'+form_name+'.'+chBox)[i].checked)
		{ 
			eval('document.'+form_name+'.' + status)[i].value="D";
		}
		if ((statusFlag=="X") && (! (eval('document.'+form_name+'.'+chBox)[i].checked)) )
		{
			eval('document.'+form_name+'.' + status)[i].value="U";
		}
		
		if (statusFlag=="I" && eval('document.'+form_name+'.'+chBox)[i].checked)
		{
			eval('document.'+form_name+'.' + status)[i].value="C";
		}
		if ((statusFlag=="C" && !(eval('document.'+form_name+'.'+chBox)[i].checked)))
		{
			eval('document.'+form_name+'.' + status)[i].value="I";
		}
		if ((statusFlag=="D" && !(eval('document.'+form_name+'.'+chBox)[i].checked)))
		{
			eval('document.'+form_name+'.' + status)[i].value="U";
		}
		//alert("after statusFlag =" + eval('document.'+form_name+'.statusFlag')[rowCont].value);
   }
}
else
{
	//statusFlag=eval('document.'+form_name+'.statusFlag').value
	statusFlag=eval('document.'+form_name+'.' + status).value
	if ((statusFlag=="X" || statusFlag=="U")&& eval('document.'+form_name+'.'+chBox).checked)
	{
		eval('document.'+form_name+'.' + status).value="D";
	}
	if ((statusFlag=="X") && (! (eval('document.'+form_name+'.'+chBox).checked)) )
	{
		eval('document.'+form_name+'.' + status).value="U";
	}
	if (statusFlag=="I" && eval('document.'+form_name+'.'+chBox).checked)
	{
		eval('document.'+form_name+'.' + status).value="C";
	}
	if ((statusFlag=="C" && !(eval('document.'+form_name+'.'+chBox).checked)))
	{
		eval('document.'+form_name+'.' + status).value="I";
	}
	if ((statusFlag=="D" && !(eval('document.'+form_name+'.'+chBox).checked)))
	{
		eval('document.'+form_name+'.' + status).value="U";
	}
}
}  



/*
	Purpose:      This function will get the value of the checkbox/radio button from the list.
	              It will also get the values of all other fields mentioned in the parameter.
	Parameters:   formName - This is the name of the form which contains the checkbox/radio button.
	              fieldName - This is the name of the checkbox/radio button.
	              otherFields - This is the array of fieldnames whose value is to be retrieved. If not, pass null.
	Return Value: It returns the two-dimensional array of values of all fields where the checkbox/radio button is,
				and the values in other fields is non empty
	              Each element of the array contains a pointer to all the values of a particular checkbox.
	Note:         All the checkboxes/radio should have the same name.
*/
function getOnlyFullValues(formName, fieldName, otherFieldNames)
{
 if(otherFieldNames)
 {
   var retValues = new Array();
   var len = 0;
   if(eval('document.'+formName+'.'+fieldName))  
     len = eval('document.'+formName+'.'+fieldName).length;
   
   if(len != 0 && len == null )
   {
     retValues[0] = new Array();
       for(var j=1; j<=otherFieldNames.length; j++)
	   {
			if( eval('document.'+formName+'.'+otherFieldNames[j-1]).value != null 
				&&  eval('document.'+formName+'.'+otherFieldNames[j-1]).value != "")
		   {
				retValues[0][j] = eval('document.'+formName+'.'+otherFieldNames[j-1]).value;
				retValues[0][0] = eval('document.'+formName+'.'+fieldName).value;
		   }
	   }
   }
   else
   {
     var i=0;
     for(var cnt=0; cnt < len; cnt++)
     {
        

           for(var j=1; j<=otherFieldNames.length; j++)
		 {
			 if(eval('document.'+formName+'.'+otherFieldNames[j-1])[cnt].value != null 
				 && eval('document.'+formName+'.'+otherFieldNames[j-1])[cnt].value != "")
			 {
					   retValues[i] = new Array();
				retValues[i][j] = eval('document.'+formName+'.'+otherFieldNames[j-1])[cnt].value;
				retValues[i][0] = eval('document.'+formName+'.'+fieldName)[cnt].value;
				i++;
			 }
		 }
     }
   }
   return retValues;
 }
 }


 /**
* FUnctions to do with input width and different types of input methode detetctions.
* These function may change at later stage to accomodate wider character set.
* Vinay C
*/

/**
* This function determines if the characters in the string are all Half width entries
*
*/
function isHalfWidth(dataValue )
{
	if (dataValue == "" ) {
		return("");
	}
	
	var unicode_value = 0;
	var halfwidth = true;
	var charCode = 0;
	for (var i = 0; i < dataValue.length; i++)	{
		unicode_value  = dataValue.charCodeAt(i);
//		alert("Code is " + unicode_value  )

		if (! (unicode_value  < 127 || (unicode_value  >= 65393 && unicode_value  <= 65437)) ){
//			alert("Improper Character " + unicode_value  + ">>"  + dataValue.charAt(i));
			halfwidth = false;
			break;
		}
	}
	return halfwidth;	
}

/**
* This function determines if the characters in the string are all full width entries
*
*/
function isFullWidth(dataValue)
{

	if (dataValue == "" ) {
		return("");
	}
	
	var unicode_value = 0;
	var fullwidth = true;
	var charCode = 0;
	for (var i = 0; i < dataValue.length; i++)	{
		unicode_value  = dataValue.charCodeAt(i);
//		alert("Code is " + unicode_value  )

		if (! ((unicode_value  >= 65296 && unicode_value  <= 65370) || 
				(unicode_value  >= 12450 && unicode_value  <= 12531)  ||
				(unicode_value  >= 12354 && unicode_value  <= 12435) ))	{
//			alert("Improper Character " + unicode_value  + ">>"  + dataValue.charAt(i));
			fullwidth = false;
			break;
		}
	}

	return fullwidth;	
}

/*
* Function returns true if the characters are a combination of Half width and FUll width
* false in case they are only of one type
*/
function isCharSetMixed(dataObject) {

var dataValue = dataObject.value;

if (isHalfWidth(dataValue) == false && isFullWidth(dataValue) == false)
return true;

return false;

}


/**
*  @author Vinay Cerejo
*  Function returns the object from the elements onthe documument mapping to the name 
*  useful for getting the object when its name is known for doin the manipulation on it
*  n=string name
*  d=document object
*/
 function findObj(n, d) { //v4.0
	 var p,i,x;  
		if(!d) d=document; 
		 if((p=n.indexOf("?"))>0&&parent.frames.length) {
			 d=parent.frames.document;
			 //n=n.substring(0,p);
		 }
		if(!(x=d[n])&&d.all)
			 x=d.all[n];
		for (i=0;!x&&i<d.forms.length;i++) 
			 x=d.forms[i][n];
		for(i=0;!x&&d.layers&&i<d.layers.length;i++)
			 x=MM_findObj(n,d.layers[i].document);
		if(!x && document.getElementById)
			 x=document.getElementById(n);
		return x;
}


/**
 Function doing the sorting of the table column data
*/

function sortHeader(colName,sortDir,sortType){

//	alert("In sortHeader "  + " col " + colName + " dir " + sortDir + " type " + sortType);
	//check for column with no data
	if (!colName )	return false;
	if (colName.value)	return false;
	
	//alert("post")

	var obj = MM_findObj(sortDir,document);

	if(obj.value=="ASCENDING"){
		ascending=true;
		obj.value="DESCENDING";

//		alert(obj.value)
	}	
	else{
		ascending=false;
		obj.value="ASCENDING";

	//	alert(obj.value)
	}

	//copy column data into temp array
	var arrTemp = new Array();
	for(i=0;i<colName.length;i++){
		arrTemp[arrTemp.length] = [colName[i].value,i];
	}
	
//	alert('b4 callingsortFunction ')
//	alert(arrTemp);

	sortFunction(arrTemp,ascending,sortType);

//	alert('after callingsortFunction ')
	//		alert(arrTemp);
	chmPrintColumn(arrTemp);

		
	return false;
}


 function chmPrintColumn(arrTemp){
	//alert("In printColumn");

	 var temp = new Array();
		for(k=0;k<arrColNames.length;k++){
		var str = arrColNames[k];
		var obj = MM_findObj(str,document);
		temp = new Array();
		//alert(str);
		//alert(obj);
		if(obj){
			for(i=0;i<arrTemp.length;i++){
				 temp[i] = obj[i].value;     
			}
			for(i=0;i<arrTemp.length;i++){
				 obj[i].value=temp[arrTemp[i][1]];     
			}
		}
	  }
 }


/**
 Function gets the parameter in dd/mm/yyy format and it converts it into mm/dd/yyyy format.
*/
	function convertDateFormat(dt){
		if(dt == ""){
			return ""
		}
		v1 = dt.substring(0,2);
		v2 = dt.substring(3,5);
		v3 = dt.substring(6,10);
		return v2 + "/" + v1 + "/" + v3;
	}




/**
 Function which returns the sorted values for a table 
 containing numeric and alphanumeric data.
*/
function sortFunction(arrTemp,orderAsc,sortElementType){
   var temp ;
   var value1;
   var value2;
    
 //  alert("in sortFunction");
   
   for(i=0;i<arrTemp.length;i++){
		 for(j=0;j<arrTemp.length-1;j++){
			  
			  //extract value depedning on data type
			  if(sortElementType=='N'){

				  	if(arrTemp[j][0] == ""){
						value1 = -1;
					}
					else
				  {
						value1 = eval(arrTemp[j][0]);
				  }
					if(arrTemp[j+1][0] == ""){
						value2 = -1;
					}
					else
				  {
						value2 = eval(arrTemp[j+1][0]);
				  }

				  

			  }
			  else if (sortElementType == 'D'){
					dataVal1 = convertDateFormat(arrTemp[j][0])
					dataVal2 = convertDateFormat(arrTemp[j+1][0])
					if(dataVal1 == ""){
						dataVal1 = "01/01/1900"
					}
					if(dataVal2 == ""){
						dataVal2 = "01/01/1900"
					}
					value1 = new Date(dataVal1);
					value2 = new Date(dataVal2);
			  }
			  else if (sortElementType == 'A'){
				  // alert('proper call to sort A Function');
				   value1 = arrTemp[j][0];
				  // alert(value1);
				   value2 = arrTemp[j+1][0];
				   value1 = value1.toUpperCase();
				   value2 = value2.toUpperCase();
				   if(value1 == ""){
					   value1 = "zzzzzzzzzzzzzzzzzzzz";
				   }
				   if(value2 == ""){
					   value2 = "zzzzzzzzzzzzzzzzzzzz";
				   }
				 //  alert(value2);
			  }
			  else {
			//	  alert('Improper call to sort Function');
				  return false;
			  }

			if(sortElementType=='N'){
				if(value1!=value2){
					if(orderAsc && (value1 < value2)){
						temp = arrTemp[j];
						arrTemp[j] = arrTemp[j+1];
						arrTemp[j+1] = temp;
					}
					if(!orderAsc && (value1 > value2)){
						temp = arrTemp[j];
						arrTemp[j] = arrTemp[j+1];
						arrTemp[j+1] = temp;
					}
				}
				//return;
			}

			 //apply interchange of data elements to sort in order
			 if(value1!=value2 && sortElementType!='N')
				 {
						//alert(value1 + ':::' + value2);
					  if(orderAsc && ((value1 < value2)||((value1=='')&&(value2!=''))))
					{
			//			  alert('interchanging');

			///			  alert('j b4 changing' + arrTemp[j]);
			//			  alert('j + 1 b4 changing' + arrTemp[j + 1]);
						temp = arrTemp[j];
						arrTemp[j] = arrTemp[j+1];
						arrTemp[j+1] = temp;
				//		alert('j after changing' + arrTemp[j]);
				//		alert('j + 1 after changing' +arrTemp[j + 1]);

					}
				  if(!orderAsc && ((value1 > value2)||(value1=='')&&(value2!='')))
					{
						temp = arrTemp[j];
						arrTemp[j] = arrTemp[j+1]
						arrTemp[j+1] = temp;
				  }
			 }
		 }
	}

//	alert('Leaving sort Function');

}
  
  
   /*
     Purpose      : This function accepts a array of fields. It checks if 
                    any of the field of the passed field(s) are empty or not
                    If atleast one field is non-empty then it returns false
                    else if all the field values are empty then it returns
                    true. This function is useful to pass all the fields of 
                    the search criteria of a form and check if atleast one 
                    search criteria has been entered or not.
                    The possible field types are text, dropdown
     Parameters   : arrFields the array of fields
                    msg the message to be displayed if all the values are empty
     Return Value : true if all the fields are empty else false
   */
   function chmIsAllEmpty(arrFields,msg)
   {
     // set this flag to true if you want this function to validate
     var bValidate = true; 
     var len = arrFields.length;
     var iIndex = 0;

     if(!bValidate)
     {
       return false;
     }

     for(iIndex = 0; iIndex < len; iIndex++)
     {
       if(eval(arrFields[iIndex]))
       {
         if(!isEmpty(eval(arrFields[iIndex])))
         {
           return false;
         }
       }
     }
     alert(msg);
     return true;
   }

   /*
     Purpose      : clears the value of the passed field and sets it to blank
                    It first checks if the date button is enabled or disabled
                    If the date button is disabled, it means that the date is 
                    non editable and so it will not clear the date field
     Parameters   : oField the field to be cleared
                    oDateButton the button for the date popup window
     Return Value : none
   */
   function cmClearDateField(oField, oDateButton)
   {
      if(eval(oDateButton))
      {
         if(eval(oDateButton).disabled == false)
         {
            if(eval(oField))
            {
               eval(oField).value = "";
            }
         }
      }
   }

   /*
     Purpose      : used to hide a div
     Parameters   : strDivName the div to be hidden
     Return Value : none
   */
   function cmHideDiv(strDivName)
   {
      document.getElementById('div_'+strDivName).style.visibility = 'hidden';
   }

   /*
     Purpose      : used to show a div
     Parameters   : strDivName the div to be shown
     Return Value : none
   */
   function cmShowDiv(strDivName)
   {
      document.getElementById('div_'+strDivName).style.visibility = 'visible';
   }

/** It checks if the string has any spaces. 
**/
function hasSpace(obj,fieldname)
{
  text = obj.value;
  if(isEmpty(obj))
  {
    return false;
  }
  else
  {
    var i;
    var oneChar;
    for( i = 0; i < text.length; i++ )
    {
      oneChar = text.charAt( i );
      if (oneChar == ' ')
      {
        alert(getErrorMsg(39,fieldname));
        obj.focus();
        obj.select();
        return true;
      }
    }
    return false;
  }
}//end of hasSpecialChar

  /**
  * Purpose: This function called when  Proposal Number Link is clicked 
  * Parameters:proposalNumber for which the workarea is to be loaded
  * Returns: None
  */

  function clearField(src)
  {
	src.value="";
	return;
  }


/** Code Added by Anuja for date validations **/

function validateDate(date, errMsg)
{
 if(!checkIfBlank(date))
{
	 var dt=date.value
	 if (isDate(dt)==false)
	 {
	  alert(errMsg);
	  date.value="";
      date.focus();
	  return false;
	 }

	 var pos1=dt.indexOf(dtCh);
	 var pos2=dt.indexOf(dtCh,pos1+1);
	 var strDay=dt.substring(0,pos1);
	 var strMonth=dt.substring(pos1+1,pos2);
	 var strYear=dt.substring(pos2+1);

	 if(strMonth.length !=2)
		 strMonth = '0' + strMonth;

	 if(strDay.length !=2)
		 strDay = '0' + strDay;
	
	 var strDate = strDay + dtCh + strMonth + dtCh +strYear;
	 date.value = strDate;

}
 return true;
}

/** It checks if the string has any special characters. 
    This method treats anything other than SPACE, DASH, NUMERICS, ALPHA and UNDERSCORE as special char.
**/
function hasSpecialChar(obj,fieldname)
{
  text = obj.value;
  if(isEmpty(obj))
  {
    return false;
  }
  else
  {
    var i;
    var oneChar;
    for( i = 0; i < text.length; i++ )
    {
      oneChar = text.charAt( i );
      if ( ! isCharValid( oneChar, ALPHA|NUMERICS|SPACE|DASH|UNDERSCORE|SLASH|DOT ) )
      {
//        alert(getErrorMsg(26,fieldname));
        obj.focus();
        obj.select();
        return true;
      }
    }
    return false;
  }
}//end of hasSpecialChar

/** Function added to compare dates and do not display any message but returns only flag.
    This is needed in case all error messages are to be displayed in one go. **/
function isStringDate2Greater(date1,date2)
{
 // date1 and date2 are strings
  //This function checks if date2 is later than date1
  //If the second date is null then it sets date2 to current date and checks if first date is later than the current date
  var sd=date1;
  if (date1 == "") return false;
  else if (date2 == "") return false;
  else
  {
    var ed=date2;
    var End= new Date(changeDateFormat(ed));
  }
  var Start= new Date(changeDateFormat(sd));
  var diff=End-Start;
  if (diff>=0) return true;
  else
  {
//    alert(msg);	
    return false;
  }
} // function isStringDate2Greater ends

/* Function changes the format of string from dd/mm/yyyy to mm/dd/yyyy as reqd by java standards
*/
function changeDateFormat(dateStr){

 var idx1 = dateStr.indexOf("/");
 var idx2 = dateStr.indexOf("/",(idx1+1));
 return dateStr.substring(idx1+1,idx2)+"/"+dateStr.substring(0,idx1)+"/"+dateStr.substring(idx2+1); 
} 

function isStringDate2Smaller(date1,date2,msg)
{  // date1 and date2 are strings
  //This function checks if date2 is later than date1
  //If the second date is null then it sets date2 to current date and checks if first date is before than the current date
  var sd=date1;
  if (date1 == "") return false;
  else if (date2 == "") return false;
  else
  {
    var ed=date2;
    var End= new Date(changeDateFormat(ed));
  }
  var Start= new Date(changeDateFormat(sd));
  var diff=End-Start;
  if (diff<=0) return true;
  else
  {
    if(msg && msg != '') alert(msg);	// means function was called for just comparing
    return false;
  }
} // function isStringDate2Smaller ends


/* End of Block by jimmy on 28/1/2003 for Date Validation */


//This function is added for adding ',' Delimiters for the amount.
function FormatNumber(num)
{       
        var sVal='';
        var minus='';
        var CommaDelimiter=',';
		
		var beforeDelimitorValue='';
		var afterDelimitorValue=''; 
		var delimitarFlag=false;
        try 
       {
		var theLength=num.length;
		 for (var i = 0 ; i < theLength ; i++)
		 {
		    if(num.charAt(i) == '.'){
				delimitarFlag=true;
			}
			if(!delimitarFlag){
				beforeDelimitorValue=beforeDelimitorValue+num.charAt(i);		
			}else{
				afterDelimitorValue=afterDelimitorValue+num.charAt(i);		
			}
		 }
		 var finalAmount=beforeDelimitorValue.toString();
	       if (finalAmount.lastIndexOf("-") == 0) { minus='-'; }
           finalAmount = FormatClean(finalAmount);
           finalAmount = parseFloat(finalAmount);
           var samount = new String(finalAmount);
	       for (var i = 0; i < Math.floor((samount.length-(1+i))/3); i++)
          {
             samount = samount.substring(0,samount.length-(4*i+3)) + CommaDelimiter + samount.substring(samount.length-(4*i+3));
           }
	   }
       catch (exception) {  }
    return minus + samount+afterDelimitorValue;
}

function FormatClean(num)
{
     var sVal='';
     var nVal = num.length;
     var sChar='';
     
   try
   {
       for(i=0;i<nVal;i++)
      {
         sChar = num.charAt(i);
         nChar = sChar.charCodeAt(0);
         if ((nChar >=48) && (nChar <=57))  { sVal += num.charAt(i);   }
      }
   }
    catch (exception) {}
    return sVal;
}

/** It will add 0's to the value till it reaches its decimal limit. **/
function fillDecimalPlaces(val, noOfDecimalPlaces)
{
  if(val.length == 0) return val;
  var retVal = val;
  var cnt = 0;
  val = ''+val;
  if(val.indexOf('.') == -1) 
  {
	cnt = noOfDecimalPlaces;
	retVal += '.';
  }
  else 
    cnt = noOfDecimalPlaces - ( val.length - val.indexOf('.') ) + 1;

  for(var i=0;i<cnt;i++)
	retVal += '0';
  return retVal;
}
