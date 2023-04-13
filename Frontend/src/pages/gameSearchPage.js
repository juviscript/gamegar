import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import VideoGameClient from "../api/videoGameClient";
import UserClient from "../api/userClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class GameSearchPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onStateChange', 'renderFulLGameList', 'renderUserList', 'onPopUpSubmit', 'showUserList', 'showPopUp', 'showGameList', 'showDevInfo'], this);
        this.dataStore = new DataStore();

        // Possible loading states of the page.
        this.GET_ALL_GAMES = 0;
        this.POP_UP_SEARCH = 1;
        this.NO_GAMES = 2;
        this.GET_ALL_USERS = 3;
        this.SHOW_DEV_INFO = 4;
    }


    /**
     * Once the page has loaded, set up the event handlers and fetch the game list.
     */
    async mount() {

        document.addEventListener('click', this.showGameList);
        document.getElementById('search-button').addEventListener('click', this.showPopUp);
        document.getElementById('search-users-button').addEventListener('click', this.showUserList);
        document.getElementById('about-button').addEventListener('click', this.showDevInfo);
        document.getElementById('pop-up-search-form').addEventListener('submit', this.onPopUpSubmit);
        this.dataStore.addChangeListener(this.onStateChange);

        this.client = new VideoGameClient();
        this.userClient = new UserClient();
        console.log("mount");
        const games = await this.client.getAllGames();
        this.dataStore.set("games", games);
        this.dataStore.set("state", this.GET_ALL_GAMES); //Set the state to get all games and render them

    }

    async onStateChange() {
        const state = this.dataStore.get("state");

        const popUpSearch = document.getElementById("pop-up-search");
        const getAllGames = document.getElementById("games-list");
        const noGamesSection = document.getElementById("empty-games");
        const getAllUsers = document.getElementById("user-list");
        const showDevInfo = document.getElementById("about-the-devs");
        if (state === this.POP_UP_SEARCH) {
            popUpSearch.classList.add("active");
            getAllGames.classList.remove("active");
            noGamesSection.classList.remove("active");
            getAllUsers.classList.remove("active");
            getAllGames.classList.remove("active");
            showDevInfo.classList.remove("active");
            // await this.renderPopUpSearch();
            await this.showPopUp(event);

        } else if (state === this.GET_ALL_GAMES) {
            popUpSearch.classList.remove("active");
            getAllGames.classList.add("active");
            getAllUsers.classList.remove("active");
            noGamesSection.classList.remove("active");
            showDevInfo.classList.remove("active");
            await this.renderFulLGameList(); //renders gamelist
        } else if (state === this.NO_GAMES) {
            popUpSearch.classList.remove("active");
            getAllGames.classList.remove("active");
            noGamesSection.classList.add("active");
            getAllUsers.classList.remove("active");
            showDevInfo.classList.remove("active");
        } else if (state === this.GET_ALL_USERS) {
            popUpSearch.classList.remove("active");
            getAllGames.classList.remove("active");
            noGamesSection.classList.remove("active");
            getAllUsers.classList.add("active");
            showDevInfo.classList.remove("active");
            await this.renderUserList(); //renders userlist
        } else if (state === this.SHOW_DEV_INFO) {
            popUpSearch.classList.remove("active");
            getAllGames.classList.remove("active");
            noGamesSection.classList.remove("active");
            getAllUsers.classList.remove("active");
            showDevInfo.classList.add("active");
        }
    }



    // Render Methods --------------------------------------------------------------------------------------------------



    async renderFulLGameList() {
        console.log("Rendering full game list...")
        let gameHtml = "";                              // Variable named gameHtml that starts as an empty string of HTML information.
        const games = this.dataStore.get("games");

        for (let i = 0; i < games.length; i++) {

            gameHtml += `

                <div class = "card">
                    <div id = "game-image-container">
                        <img src = ${games[i].image} id = "game-img">
                    </div>

                    <div id = "game-details">
                        <p id = "info-2">
                          <h1> ${games[i].title} </h1>
                        <b>Developer:</b> ${games[i].developer}
                        <br>
                        <b>Country of Origin:</b> ${games[i].country}
                        <br>
                        <b>Year:</b> ${games[i].year}
                        <br>
                        <b>Genre:</b> ${games[i].genre}
                        <br>
                        <b>Platforms:</b> ${games[i].platforms}
                        <br>
                        <b>Tags:</b> ${games[i].tags}
                        </p>
                        <br>
                        <p id = "description">
                            ${games[i].description}
                        </p>
                    </div>

                </div>

                `;

        }

        document.getElementById("games-list").innerHTML = gameHtml;
    }


    async renderUserList() {
        console.log("User list rendering...");
        let userHtml = "";

        const userList = this.dataStore.get("users");
        console.log(userList);

        for (let i = 0; i < userList.length; i++) {

            console.log(userList[i]);

            userHtml += `

				<div class = "card">
					<h1> ${userList[i].username} </h1>
						<div id = "user-info">
							<br>
								<li>Name: ${userList[i].name}</li>
							<br>
								<li>Email: ${userList[i].email}</li>
							<br>
								<li>Birthday: ${userList[i].birthday}</li>
							<br>
						</div>
				</div>
			
						`;
        }

        document.getElementById("user-list").innerHTML = userHtml;

    }





    // Event Handlers --------------------------------------------------------------------------------------------------

     async showGameList() {
        console.log("Triggering full game list...")
        const games = await this.client.getAllGames();
        this.dataStore.set("games", games);
        this.dataStore.set("state", this.GET_ALL_GAMES);
     }

     async showPopUp(event) {
         console.log("Triggering pop up search...");
         event.preventDefault();

         this.dataStore.set("state", this.POP_UP_SEARCH);
     }

    async showUserList() {
        console.log("Triggering full user list...")
        const users = await this.userClient.getAllUsers(this.errorHandler);
        this.dataStore.set("users", users);
        this.dataStore.set("state", this.GET_ALL_USERS);
    }

    async showDevInfo(event) {
        console.log("Triggering dev information...")
        event.preventDefault();
        this.dataStore.set("state", this.SHOW_DEV_INFO);
    }

    async onPopUpSubmit(event) {
            event.preventDefault();
            console.log("Pop up search rendering...");

            let submitButton = document.getElementById('pop-up-search-submit');
            submitButton.innerText = 'Loading...'
            submitButton.disabled = true;

            const games = await this.client.getAllGames(this.errorHandler);
            this.dataStore.set("games", games);
            this.dataStore.get("games");

            let gameId = document.getElementById('all-games-button')
            let foundGame = null;



//            let gameId = document.getElementById("game-search-id").value;
//            const game = await this.client.getGameById(gameId, this.errorHandler);
//            let gameHtml = "";
//
//
//            gameHtml += `
//
//                <div class = "card">
//
//
//                    <div id = "game-details">
//                        <p id = "info-2">
//                          <h1> ${game.title} </h1>
//                        <b>Developer:</b> ${game.developer}
//                        <br>
//                        <b>Country of Origin:</b> ${game.country}
//                        <br>
//                        <b>Year:</b> ${game.year}
//                        <br>
//                        <b>Genre:</b> ${game.genre}
//                        <br>
//                        <b>Platforms:</b> ${game.platforms}
//                        <br>
//                        <b>Tags:</b> ${game.tags}
//                        </p>
//                        <br>
//                        <p id = "description">
//                            ${game.description}
//                        </p>
//                    </div>
//
//                </div>
//
//                        `;

//
//
//            let gameHtml = "";
//
//            for (const game of games) {
//                if (game.id === gameId) {
//                    gameHtml += `
//                        <div class = "card">
//                            <div id = "game-image-container">
//                                <img src = ${game.image} id = "game-img">
//                            </div>
//
//                            <div id = "game-details">
//                                <p id = "info-2">
//                                  <h1> ${game.title} </h1>
//                                <b>Developer:</b> ${game.developer}
//                                <br>
//                                <b>Country of Origin:</b> ${game.country}
//                                <br>
//                                <b>Year:</b> ${game.year}
//                                <br>
//                                <b>Genre:</b> ${game.genre}
//                                <br>
//                                <b>Platforms:</b> ${game.platforms}
//                                <br>
//                                <b>Tags:</b> ${game.tags}
//                                </p>
//                                <br>
//                                <p id = "description">
//                                    ${game.description}
//                                </p>
//                            </div>
//
//                        </div>
//                            `;
//                } else {
//                        gameHtml = "No games with that specific value found!"
//                }
                document.getElementById("pop-up-search-list").innerHTML = gameHtml;
        }

}



/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const gameSearchPage = new GameSearchPage();
    gameSearchPage.mount();
};

window.addEventListener('DOMContentLoaded', main);