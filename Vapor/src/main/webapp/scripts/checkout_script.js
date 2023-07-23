function loadCheckoutScript() {
	document.getElementById("confirmPurchase").addEventListener('click', (element) => {
		let canPurchase = element.target.dataset.canpurchase;
		if (canPurchase === 'true') {
			let jsonToSend = {
				"numero carta" : document.getElementById("metodoPagamentoSelezionato").value,
				"prezzo totale" : element.target.dataset.prezzototale
			};
			let xhr = createXMLHTTPRequest();
			xhr.open("post", "/Vapor/AcquistoServlet", true);
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE) {
					if (xhr.status === 200) {
						location.assign("/Vapor/jsp/acquisto-completato.jsp");
					}
				}
			};
			xhr.send(JSON.stringify(jsonToSend));
		}
		else {
			document.getElementById("purchaseErrorViewer").style.display = "block";
		}

		
	});

}
document.addEventListener('DOMContentLoaded', loadCheckoutScript);