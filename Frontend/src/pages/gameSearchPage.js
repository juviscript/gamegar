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
        this.bindClassMethods(['onStateChange', 'renderFulLGameList', 'renderUserList', 'showUserList', 'showPopUp', 'showGameList'], this);
        this.dataStore = new DataStore();

        // Possible loading states of the page.
        this.GET_ALL_GAMES = 0;
        this.POP_UP_SEARCH = 1;
        this.NO_GAMES = 2;
        this.GET_ALL_USERS = 3;
    }


    /**
     * Once the page has loaded, set up the event handlers and fetch the game list.
     */
    async mount() {
        document.getElementById('all-games-button').addEventListener('click', this.showGameList);
        document.getElementById('search-button').addEventListener('click', this.showPopUp);
        document.getElementById('search-users-button').addEventListener('click', this.showUserList);
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

        const popUpSearch = document.getElementById("pop-up-search")
        const getAllGames = document.getElementById("games-list")
        const noGamesSection = document.getElementById("empty-games")
        const getAllUsers = document.getElementById("user-list")

        if (state === this.POP_UP_SEARCH) {
            popUpSearch.classList.add("active")
            getAllGames.classList.remove("active")
            noGamesSection.classList.remove("active")
            getAllUsers.classList.remove("active")
            getAllGames.classList.remove("active")
            await this.showPopUp(event);
        } else if (state === this.GET_ALL_GAMES) {
            popUpSearch.classList.remove("active")
            getAllGames.classList.add("active")
            getAllUsers.classList.remove("active")
            noGamesSection.classList.remove("active")
            await this.renderFulLGameList(); //renders gamelist
        } else if (state === this.NO_GAMES) {
            popUpSearch.classList.remove("active")
            getAllGames.classList.remove("active")
            noGamesSection.classList.add("active")
            getAllUsers.classList.remove("active")
        } else if (state === this.GET_ALL_USERS) {
            popUpSearch.classList.remove("active")
            getAllGames.classList.remove("active")
            noGamesSection.classList.remove("active")
            getAllUsers.classList.add("active")
            await this.renderUserList(); //renders userlist
        }
    }



    // Render Methods --------------------------------------------------------------------------------------------------



    async renderFulLGameList() {
        let gameHtml = "";                              // Variable named gameHtml that starts as an empty string of HTML information.
        const games = this.dataStore.get("games");

        for (let i = 0; i < games.length; i++) {

            gameHtml += ` 

                <div class = "card">
                
                    <div id = "game-title">
                        <h2> ${games[i].title} </h2>
                    </div>
                    
                    <div id = "game-image-container">
                        <img src = ${games[i].image} id = "game-img">
                    </div>

                        <div id = "game-details">
                            

                            <p id = "info-1">
                                <ul>
                                    <li>Developer: ${games[i].developer}</li>
                                    <li>Country of Origin: ${games[i].country}</li>
                                    <li>Year: ${games[i].year}</li>
                                </ul>
                            </p>

                            <p id = "info-2">
                                <ul>
                                    <li>Genre: ${games[i].genre}</li>
                                    <li>Platforms: ${games[i].platforms}</li>
                                    <li>Tags: ${games[i].tags}</li>
                                </ul>
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

        console.log("render user list 2");
        let userHtml = "";

        const userList = this.dataStore.get("users");
        console.log(userList);

        for (let i = 0; i < userList.length; i++) {

            console.log(userList[i]);

            userHtml += `

				<div class = "card">
				
					<h2> ${userList[i].username} </h2>
						<div id = "user-info">
							<ul>
								<li>Name: ${userList[i].name}</li>
								<li>Email: ${userList[i].email}</li>
								<li>Birthday: ${userList[i].birthday}</li>
							</ul>
						</div>
				</div>
			
						`;
        }

        document.getElementById("user-list").innerHTML = userHtml;

    }




    // Event Handlers --------------------------------------------------------------------------------------------------


    async showUserList() {
        const users = await this.userClient.getAllUsers(this.errorHandler);
        this.dataStore.set("users", users);
        this.dataStore.set("state", this.GET_ALL_USERS);
    }

    async showPopUp(event) {
        console.log("render pop up");

        event.preventDefault();
        this.dataStore.set("state", this.POP_UP_SEARCH);
    }

    async showGameList() {
        const games = await this.client.getAllGames();
        this.dataStore.set("games", games);
        this.dataStore.set("state", this.GET_ALL_GAMES);
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