function loadCarrelloScript() {
	let CarrelloServlet = "/Vapor/CarrelloServlet";
	
	let modifiedInput = null;
	let updateQuantity = function(element, rowID) {
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
					let rowList = document.getElementsByClassName("tableRow");
					for(let i = 0; i < rowList.length; i++) {
							let prezzoCorrente = rowList[i].getElementsByClassName("prezzo")[0].dataset.prezzo;
							rowList[i].getElementsByClassName("prezzo")[0].innerHTML = prezzoCorrente * document.getElementsByClassName("changeQuantity")[0].value; 
					}
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
				updateQuantity(element, element.parentNode.parentNode.id);
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
