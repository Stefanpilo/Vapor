function loadCarrelloScript() {
	let CarrelloServlet = "/Vapor/CarrelloServlet";
	
	let modifiedInput = null;
	let updateQuantity = function(element) {
		if (element.value <= 0) {
			element.setCustomValidity("Inserire un valore maggiore di 0");
			element.reportValidity();
			return;
		}

		let jsonToSend = {
			"query type" : "update quantity",
			"quantity" : element.value,
			"id" : element.dataset.idvideogioco
		}
		let xhr = createXMLHTTPRequest();
		xhr.open("post", CarrelloServlet, true);
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					console.log(xhr.responseText);
				}
			}
		};
		xhr.send(JSON.stringify(jsonToSend));
	};
	document.querySelectorAll(".changeQuantity").forEach( (element) => {
		element.addEventListener('input', () => {
			modifiedInput = element;
		})
		element.addEventListener('change', () => {
			if (modifiedInput === element) {
				updateQuantity(element);
				modifiedInput = null
			}
		});
	});

	let removeProduct = function() {
		let buttonClicked = event.target;
		let jsonToSend = {
			"query type" : "remove product",
			"id" : event.target.dataset.idvideogioco
		}

		let xhr = createXMLHTTPRequest();
		xhr.open("post", CarrelloServlet, true),
		xhr.setRequestHeader("Content-type", "application/json");
		xhr.onreadystatechange = function() {
			if(xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200)	{
					//rimuovi la riga del bottone clickato
					buttonClicked.parentNode.parentNode.parentNode.removeChild(buttonClicked.parentNode.parentNode);
					if (document.querySelector("tbody").children.length === 0) {
						document.getElementById("tableWrapper").style.display = "none";
						document.getElementById("cartEmptyMessage").style.display = "block";
					}
				}
			}
		};
		xhr.send(JSON.stringify(jsonToSend));
	};
	document.querySelectorAll(".deleteRow_button").forEach( (element) => {
		element.addEventListener('click', removeProduct);
	});
};
document.addEventListener('DOMContentLoaded', loadCarrelloScript);


function createXMLHTTPRequest() {
	let request;
	try {
		//Firefox 1+, Chrome 1+, Opera 8+, Safari 1.2+, Edge 12+, Internet Explorer 7+
		request = new XMLHttpRequest();
	}
	catch(e) {
		try {
			//past versions of Internet Explorer
			request = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch(e) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e) {
				alert("Il browser non supporta AJAX");
				return null;
			}
		}
	}
		
	return request;
}