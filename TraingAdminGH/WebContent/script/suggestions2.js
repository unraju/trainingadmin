
/**
 * Provides suggestions for state names (USA).
 * @class
 * @scope public
 */
function StateSuggestions() {
    this.states = [
         "01:00 AM", "02:00 AM", "03:00 AM","04:00 AM","05:00 AM ","06:00 AM ",
         "07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM","12:00 AM",  
         "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM", "06:00 PM",
         "07:00 PM", "08:00 PM", "09:00 PM", "10:00 PM", "11:00 PM","12:00 PM",
         "1:00 AM", "2:00 AM", "3:00 AM","4:00 AM","5:00 AM ","6:00 AM ",
         "7:00 AM", "8:00 AM", "9:00 AM",
         "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM",
         "7:00 PM", "8:00 PM", "9:00 PM"

    ];
}

/**
 * Request suggestions for the given autosuggest control. 
 * @scope protected
 * @param oAutoSuggestControl The autosuggest control to provide suggestions for.
 */
StateSuggestions.prototype.requestSuggestions = function (oAutoSuggestControl /*:AutoSuggestControl*/,
                                                          bTypeAhead /*:boolean*/) {
    var aSuggestions = [];
    var sTextboxValue = oAutoSuggestControl.textbox.value;
    
    if (sTextboxValue.length > 0){
    
        //search for matching states
        for (var i=0; i < this.states.length; i++) { 
            if (this.states[i].indexOf(sTextboxValue) == 0) {
                aSuggestions.push(this.states[i]);
            } 
        }
    }

    //provide suggestions to the control
    oAutoSuggestControl.autosuggest(aSuggestions, bTypeAhead);
};