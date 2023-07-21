function loadHomePageScript() {
		let gameContainerList = document.getElementsByClassName("gameContainer");

		for(let i = 0; i < gameContainerList.length; i++) {
			gameContainerList[i].addEventListener('click', () => {
				let IDVideogioco = event.target.dataset.idvideogioco;
				document.cookie = "idvideogioco=" + IDVideogioco;
				window.location.href = "/Vapor/jsp/videogiocoSinglePage.jsp";
			})
		}
}
document.addEventListener("DOMContentLoaded", loadHomePageScript);