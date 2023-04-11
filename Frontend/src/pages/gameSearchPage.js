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
        this.bindClassMethods(['onStateChange', 'renderFulLGameList', 'renderUserList', 'showUserList', 'showPopUp'], this);
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
        document.getElementById('all-games-button').addEventListener('click', this.renderFulLGameList);
        document.getElementById('search-button').addEventListener('click', this.showPopUp);
        document.getElementById('search-users-button').addEventListener('click', this.showUserList);
        this.dataStore.addChangeListener(this.onStateChange);

        this.client = new VideoGameClient();
        this.userClient = new UserClient();

        const games = await this.client.getAllGames();
        this.dataStore.set("state", this.GET_ALL_GAMES);


        if (games && games.length > 0) {

            console.log(games);
            this.dataStore.set('games', games);
            this.dataStore.set('state', this.GET_ALL_GAMES);

        } else if (games.length === 0) {

            this.dataStore.set("state", this.NO_GAMES);
            this.errorHandler("There are no games listed in our database currently! Why don't you try adding one! " +
                "Click on that \"Admin\" link above!");

        } else {

            this.errorHandler();

        }
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
            await this.renderPopUpSearch();
        } else if (state === this.GET_ALL_GAMES) {
            popUpSearch.classList.remove("active")
            getAllGames.classList.add("active")
            getAllUsers.classList.remove("active")
            noGamesSection.classList.remove("active")
            await this.renderFulLGameList();
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
            await this.renderUserList();
        }
    }



    // Render Methods --------------------------------------------------------------------------------------------------



    async renderFulLGameList(event) {

        event.preventDefault();

        // const state = this.dataStore.get("state");
        //
        // if (state != this.GET_ALL_GAMES) {
        //     this.dataStore.set("state", this.GET_ALL_GAMES);
        // }

        let gameHtml = "";                              // Variable named gameHtml that starts as an empty string of HTML information.
        const games = this.dataStore.get("games");

        // if (games) {
        for (let i = 0; i < games.length; i++) {

            // for (const catalogRecord of games) {
            gameHtml += ` 
            
                <div class = "card">
                    <h2> ${games[i].title} </h2>
                    <div id = "info-1">
                        <ul>
                            <li>Developer: ${games[i].developer}</li>
                            <li>Country of Origin: ${games[i].country}</li>
                            <li>Year: ${games[i].year}</li>
                        </ul>
                    </div>
    
                    <div id = "info-2">
                        <li>Genre: ${games[i].genre}</li>
                        <li>Platforms: ${games[i].platforms}</li>
                        <li>Tags: ${games[i].tags}</li>
                    </div>
                    
                    <div id="description">
                        <p>
                            ${games[i].description}
                         
                        </p>
                        <img src = ${games[i].image} width="400" height="400">
                    </div>
                </div>
      
                `;

            // }
        }

            document.getElementById("games-list").innerHTML = gameHtml;
    }


    async renderUserList() {
        console.log("render user list 2");                                              // Currently infinitely looping. Very sad will look into this tomorrow.
        const users = await this.userClient.getAllUsers();
        this.dataStore.set("users", users)

        let userHtml = "";

        if (users && users.length > 0) {
            const userList = this.dataStore.get("users");

            for (let i = 0; i < userList.length; i++) {
                console.log(userList);
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
                `;
            }

        } else if (users.length === 0) {

            this.dataStore.set("state", this.NO_GAMES);
            this.errorHandler("There are no users listed in our database currently! We don't have friends! Sad :(");

        } else {

            this.errorHandler("Error retrieving users. Try again later!");              // Edit this later for users.

        }

        document.getElementById("user-list").innerHTML = userHtml;
    }



    // Event Handlers --------------------------------------------------------------------------------------------------


    async showUserList(event) {
        console.log("render user list");

        event.preventDefault();
        this.dataStore.set("state", this.GET_ALL_USERS);

    }

    async showPopUp(event) {
        console.log("render pop up");

        event.preventDefault();
        this.dataStore.set("state", this.POP_UP_SEARCH);
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

