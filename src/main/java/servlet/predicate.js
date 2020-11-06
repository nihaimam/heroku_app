//global XML request variable
var xhr

function showV1() //Called from HTML onkeyup for variable 1
{
	var v1 = document.getElementById("v1").value;
	document.getElementById("eq_v1").innerHTML = v1;
}

function showOp() //Called from HTML onkeyup for operator
{
	var op = document.getElementById("op").value;
	document.getElementById("eq_op").innerHTML = op;
}

function showV2() //Called from HTML onkeyup for variable 2
{
	var v2 = document.getElementById("v2").value;
    document.getElementById("eq_v2").innerHTML = v2;
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
