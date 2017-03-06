$( document ).ready(function(){
	var g = document.getElementsByClassName("mySlides");
	for (i = 1; i < g.length; i++) {
	     g[i].style.display = "none";  
	  }
	
	var seconds_left = 300;

	var interval = setInterval(function() {
	    document.getElementById('timer_div').innerHTML = --seconds_left;

		    if (seconds_left <= 0) {
			clearInterval(interval);
			$("#test").submit();
		}
	}, 1000);
	
	 	var $submit = $("#sub").hide();
	    var $submit1 = $("#dis").show();
	    var    $cbs = $("input[type='radio']").click(function() {
	           $submit.toggle( $cbs.is(":checked") );
	           $submit1.hide();
	        });

	
});

var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
  showDivs(slideIndex += n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");  
  
  if (n > x.length){slideIndex = 1}
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
     x[i].style.display = "none";  
  }
  x[slideIndex-1].style.display = "block";  
}





