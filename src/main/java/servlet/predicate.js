//global XML request variable
var xhr

function loadPost()
{
	var v1 = document.getElementById("v1").value;
	var op = document.getElementById("op").value;
	var v2 = document.getElementById("v2").value;

	xhr = GetXmlHttpObject();
	if (xhr == null)
	{
		alert("Your browser does not support XMLHTTP!");
		return;
	}
	
	// Backend handler
	var url = "https://swe432servlet.herokuapp.com/AsynchHandler";
	url = url + "?var1=" + v1;
	url = url + "?oper=" + op;
	url = url + "?var2=" + v2;
	url = url + "&sid=" + Math.random();
	
	// callback function when server responds
	xhr.onreadystatechange = stateChanged;
	// An HTTP GET request to url, true=asynchronous
	xhr.open("GET", url, true);
	// Send the request asynchronously
	xhr.send(null);

}

function stateChanged() // On return, change the DOM with the response text
{
	if (xhr.readyState == 4)
	{
		document.getElementById("final").innerHTML = xhr.responseText;
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
