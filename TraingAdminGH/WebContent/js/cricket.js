var lastKeyDown;
          
 function funDown() 
 { 
     if(window.event && window.event.keyCode == 17)  
     { // Capture and remap ctrl
       window.event.keyCode = 555; 	
	   lastKeyDown = 'Ctrl';
       return false; 
     } 
            
     if(window.event && window.event.keyCode == 78)  
     { // Capture and remap N
	   if(lastKeyDown == 'Ctrl') {
		   //alert('Ctrl+N has been disabled');
	       window.event.keyCode = 555;
		   //delete_cookie('Ravi');
	       return false; 
	   }
     } 
	 
	 if(window.event && window.event.keyCode != 78 && window.event.keyCode != 17)  
	 {
		 lastKeyDown='Not Ctrl or N';
	 }
 }

function delete_cookie ( cookie_name )
{
  var cookie_date = new Date ( );  // current date & time
  cookie_date.setTime ( cookie_date.getTime() - 1 );
  document.cookie = cookie_name += "=; expires=" + cookie_date.toGMTString();
}