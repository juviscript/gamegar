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
        this.bindClassMethods(['onStateChange', 'renderFulLGameList', 'onGetAllGames', 'renderPopUpSearch', 'renderUserList'], this);
        this.dataStore = new DataStore();
        // this.dataStore.addChangeListener(this.renderFulLGameList())
        // this.onGetAllGames();
        // this.client = new VideoGameClient();

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
        // document.getElementById('choose-search-form').addEventListener('submit', this.onGet);
        document.getElementById('all-games-button').addEventListener('click', this.renderFulLGameList);
        document.getElementById('search-button').addEventListener('click', this.renderPopUpSearch);
        document.getElementById('search-users-button').addEventListener('click', this.renderUserList);
        this.dataStore.addChangeListener(this.onStateChange);

        this.client = new VideoGameClient();

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
            this.errorHandler("Error retrieving games. Try again later!");

        }
    }

    // async fetchGames() {
    //     const games = await this.client.getAllGames(this.errorHandler)
    //
    //     if (games && games.length > 0) {
    //         for (const game of games) {
    //             await this.renderFulLGameList();
    //         }
    //     }
    //
    // }

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
            this.renderPopUpSearch();
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
            this.renderUserList();
        }
    }



    // Render Methods --------------------------------------------------------------------------------------------------



    async renderFulLGameList() {
        const state = this.dataStore.get("state");

        if (state != this.GET_ALL_GAMES) {
            this.dataStore.set("state", this.GET_ALL_GAMES);
        }

        let gameHtml = "";                              // Variable named gameHtml that starts as an empty string of HTML information.
        const games = this.dataStore.get("games");

            for (const catalogRecord of games) {
                gameHtml += ` 
            
                <div class = "card">
                    <h2> ${catalogRecord.title} </h2>
                    <div id = "info-1">
                        <ul>
                            <li>Developer: ${catalogRecord.developer}</li>
                            <li>Country of Origin: ${catalogRecord.country}</li>
                            <li>Year: ${catalogRecord.year}</li>
                        </ul>
                    </div>
    
                    <div id = "info-2">
                        <li>Genre: ${catalogRecord.genre}</li>
                        <li>Platforms: ${catalogRecord.platforms}</li>
                        <li>Tags: ${catalogRecord.tags}</li>
                    </div>
    
                    <div id="description">
                        <p>
                            ${catalogRecord.description}
                        </p>
                    </div>
                </div>
      
                `;
            }

            document.getElementById("games-list").innerHTML = gameHtml;
    }



    async renderPopUpSearch() {
        this.dataStore.set("state", this.POP_UP_SEARCH);
    }



    async renderUserList() {
        this.dataStore.set("state", this.GET_ALL_USERS);

        const state = this.dataStore.get("state");

        if (state === this.GET_ALL_USERS) {
            this.client = new UserClient();
        } else {
            this.client = new VideoGameClient();
        }

                // Not sure about this. Going to test it out later.


        let userHtml = "";
        const users = this.dataStore.get("users");

        for (const userRecord of users) {
            userHtml += ` 
            
                <div class = "card">
                    <h2> ${userRecord.username} </h2>
                    <div id = "user-info">
                        <ul>
                            <li>Name: ${userRecord.name}</li>
                            <li>Email: ${userRecord.email}</li>
                            <li>Birthday: ${userRecord.birthday}</li>
                        </ul>
                </div>
                `;
        }

        document.getElementById("user-list").innerHTML = userHtml;
    }



    // async onCreate(event) {
    //     // Prevent the page from refreshing on form submit
    //     event.preventDefault();
    //     this.dataStore.set("example", null);
    //
    //     let name = document.getElementById("create-name-field").value;
    //
    //     const createdExample = await this.client.createExample(name, this.errorHandler);         This is probably going to be on the Admin page.
    //     this.dataStore.set("example", createdExample);                                           No creation will be done on here, only loading.
    //
    //     if (createdExample) {
    //         this.showMessage(`Created ${createdExample.name}!`)
    //     } else {
    //         this.errorHandler("Error creating!  Try again...");
    //     }
    // }


    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetAllGames() {

    }

    // async onSelectedSearchParameter {
    //
    // }

    // // async onPopUpSearch(event) {
    //
    //     // event.preventDefault();
    //     //
    //     // let parameter = document.getElementById("pop-up-search").value;
    //     //
    //     // const games = this.dataStore.get("games");
    //     // let selectedParam = null;
    //     //
    //     // for (const game of games) {
    //     //     if (game.id === )
    //     // }
    //
    //     //
    //     // if (games && games.length > 0) {
    //     //     for (const games of games) {
    //     //         games.reservations = await this.fetchReservations(concert.id);           Commenting out as I'm unsure if this is required.
    //     //         games.purchases = await this.fetchPurchases(concert.id);
    //     //     }
    //     // }
    //
    //     this.dataStore.set("games", games);
    // }
    //
    // async getAllUsers() {
    //
    // }


}



/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const gameSearchPage = new GameSearchPage();
    gameSearchPage.mount();
};

window.addEventListener('DOMContentLoaded', main);

