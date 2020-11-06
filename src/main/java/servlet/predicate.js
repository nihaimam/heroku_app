//global XML request variable
var xhr

function showV1(str) //Called from HTML onkeyup for variable 1
{
	//var v1 = document.getElementById('v1').value;
	if (str.length == 0) {
		document.getElementById("eq_"+str).innerHTML = "";
		return;
	}
	else
	{
		document.getElementbyId("eq_"+str).innerHTML = str;
	}
	return;
}
/*
	
	//input taken in
	xhr=GetXmlHttpObject();

	//error in creation
	if (xhr == null) {
		alert("Your browser does not support XMLHTTP!");
		return;
	}

	//response - backend handler
	var url = "https://cs.gmu.edu:8443/swe432-assign5-ntb.herokuapp.com/AsynchHandler";
	url = url + "?V1SoFar=" + str;
	url = url + "&sid=" + Math.random();

	//callback function when server responds
	xhr.onreadystatechange = stateChanged;
	xhr.open("GET", url, true); //true == asynchronous
	xhr.send(null); //send asynchronously
}

//on return, change the DOM with the response text
function stateChanged()
{
	if (xhr.readyState == 4) {
		document.getElementById("table").innerHTML = xhr.responseText;
	}
}

function GetXmlHttpObject()
{
	//IE7+, Firefox, Chrome, Opera, Safari
	if (window.XMLHttpRequest)
	{
		return new XMLHttpRequest();
	}
	
	//IE6, IE5
	if (window.ActiveXObject)
	{
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
	return null;
}
*/
