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
        this.bindClassMethods(['onStateChange', 'onCreate', 'renderExample'], this);
        this.dataStore = new DataStore();

        // Possible loading states of the page.

        this.LOADING = 0;
        this.POP_UP_SEARCH = 1
        this.GET_ALL_GAMES = 2
        this.NO_GAMES = 3;
        this.GET_ALL_USERS = 4;
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        this.dataStore.addChangeListener(this.onStateChange)

        // Starts the index.html on the loading page.
        this.dataStore.set("state", this.LOADING)

        this.client = new VideoGameClient();
        const games = await this.client.getAllGames();

        if (games && games.length > 0) {
            this.dataStore.set('games', games);
            this.dataStore.set('state', this.POP_UP_SEARCH);
        } else if (games.length === 0) {
            this.dataStore.set("state", tjhis.NO_GAMES);
            this.errorHandler("There are no games listed in our database currently! Why don't you try adding one! " +
                "Click on that \"Admin\" link above!");
        } else {
            this.errorHandler("Error retrieving games. Try again later!");

        }
    }

    onStateChange() {
        const state = this.dataStore.get("state");

        const loadingSection = document.getElementById("loading")
        const popUpSearch = document.getElementById("pop-up-search")
        const getAllGames = document.getElementById("games-list")
        const noGamesSection = document.getElementById("empty-games")
        const getAllUsers = document.getElementById("user-list")

        if (state === this.LOADING) {
            loadingSection.classList.add("active")
            popUpSearch.classList.remove("active")
            getAllGames.classList.remove("active")
            noGamesSection.classList.remove("active")
            getAllGames.classList.remove("active")
            getAllUsers.classList.remove("active")
        } else if (state === this.POP_UP_SEARCH) {
            loadingSection.classList.remove("active")
            popUpSearch.classList.add("active")
            getAllGames.classList.remove("active")
            noGamesSection.classList.remove("active")
            getAllUsers.classList.remove("active")
            getAllGames.classList.remove("active")
            this.renderPopUpSearch();
        } else if (state === this.GET_ALL_GAMES) {
            loadingSection.classList.remove("active")
            popUpSearch.classList.remove("active")
            getAllGames.classList.add("active")
            getAllUsers.classList.remove("active")
            noGamesSection.classList.remove("active")
            this.renderFulLGameList();
        } else if (state === this.NO_GAMES) {
            loadingSection.classList.remove("active")
            popUpSearch.classList.remove("active")
            getAllGames.classList.remove("active")
            noGamesSection.classList.add("active")
            getAllUsers.classList.remove("active")
        } else if (state === this.GET_ALL_USERS) {
            loadingSection.classList.remove("active")
            popUpSearch.classList.remove("active")
            getAllGames.classList.remove("active")
            noGamesSection.classList.remove("active")
            getAllUsers.classList.add("active")
            this.renderUserList();
        }
    }



    // Render Methods --------------------------------------------------------------------------------------------------



    async renderFulLGameList() {
        let gameHtml = "";                              // Variable named gameHtml that starts as an empty string of HTML information.
        const games = this.dataStore.get("games");

            for (const game of games) {
                gameHtml += ` 
            
                <div class = "card">
                    <h2> ${game.title} </h2>
                    <div id = "info-1">
                        <ul>
                            <li>Developer: ${game.developer}</li>
                            <li>Country of Origin: ${game.country}</li>
                            <li>Year: ${game.year}</li>
                        </ul>
                    </div>
    
                    <div id = "info-2">
                        <li>Genre: ${game.genre}</li>
                        <li>Platforms: ${game.platforms}</li>
                        <li>Tags: ${game.tags}</li>
                    </div>
    
                    <div id="description">
                        <p>
                            ${game.description}
                        </p>
                    </div>
                </div>
      
                `;
            }

            document.getElementById("games-list").innerHTML = gameHtml;

    }


}

    async renderPopUpSearch() {
        let searchParameters = document.getElementById("game-search");

        const games = this.dataStore.get("games");

        let options = "";

        for (const game of games) {
            options += '<option value = '

        }

    }



    // async renderUserList() {
    //     this.client = new UserClient();                             // Not sure about this. Going to test it out later.
    //
    // }



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

    // async onSelectedSearchParameter {
    //
    // }
    //
    // async getAllGames() {
    //     const games = await this.client.getAllGames(this.errorHandler)
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
    //
    //
}
}


/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const examplePage = new GameSearchPage();
    examplePage.mount();
};

window.addEventListener('DOMContentLoaded', main);

