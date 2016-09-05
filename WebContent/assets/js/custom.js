/** ********************************************** **
	Your Custom Javascript File
	Put here all your custom functions
*************************************************** **/



/** Remove Panel
	Function called by app.js on panel Close (remove)
 ************************************************** **/
	function _closePanel(panel_id) {
		/** 
			EXAMPLE - LOCAL STORAGE PANEL REMOVE|UNREMOVE

			// SET PANEL HIDDEN
			localStorage.setItem(panel_id, 'closed');
			
			// SET PANEL VISIBLE
			localStorage.removeItem(panel_id);
		**/	
	}
	
	$('#ssn').keyup(function() {
	    var val = this.value.replace(/\D/g, '');
	    var newVal = '';
	    var sizes = [3, 2, 4];

	    for (var i in sizes) {
	      if (val.length > sizes[i]) {
	        newVal += val.substr(0, sizes[i]) + '-';
	        val = val.substr(sizes[i]);
	      }
	      else
	        break;        
	    }

	    newVal += val;
	    this.value = newVal;
	});