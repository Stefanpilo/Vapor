function loadHomePageScript() {
		let gameContainerList = document.getElementsByClassName("gameContainer");

		for(let i = 0; i < gameContainerList.length; i++) {
			gameContainerList[i].addEventListener('click', (element) => {
				window.location.href = "/Vapor/jsp/videogiocoSinglePage.jsp?id=" + element.target.dataset.idvideogioco;
			})
		}
}
document.addEventListener("DOMContentLoaded", loadHomePageScript);