import BaseClass from '../util/baseClass';
import DataStore from '../util/DataStore';
import VideoGameClient from "../api/videoGameClient";

/**
 * Logic needed for the create playlist page of the website.
 */
class GameAdmin extends BaseClass {
    constructor() {
        super();
        this.bindClassMethods(['onSubmit', 'onRefresh', 'renderConcerts'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    mount() {
        document.getElementById('refresh').addEventListener('click', this.onRefresh);
        document.getElementById('create-game-form').addEventListener('submit', this.onSubmit);

        this.client = new VideoGameClient();
        this.dataStore.addChangeListener(this.renderGames)
        this.fetchConcerts();
    }

    async fetchGames() {
        const games = await this.client.getAllGames(this.errorHandler)

        if (games && games.length > 0) {
            for (const game of games) {
                games.list = await this.fetchGames(game.id);
            }
        }
        this.dataStore.set("games", games);
    }

    async fetchGames(id) {
        return await this.client.getAllGames(id, this.errorHandler);
    }


    // Render Methods --------------------------------------------------------------------------------------------------

    renderGames() {
        let gameHtml = "";
        const games = this.dataStore.get("games");

        if (games.length <= 0) {
            for (const game of games) {

                gameHtml += `
                    <div class="card">
                        <h2> ${game.title} </h2>
                        <div id="info-1">
                            <ul>
                                <li>Developer: ${game.developer}</li>
                                <li>Country of Origin: ${game.country}</li>
                                <li>Year: ${game.year}</li>
                            </ul>
                        </div>

                        <div id="info-2">
                            <li>Genre: ${game.genre}</li>
                            <li>Platforms: ${game.platforms}</li>
                            <li>Tags: ${game.tags}</li>
                        </div>

                        <div id="description">
                            <p>
                                ${game.description}
                            </p>
                        </div>
                `;
            }
        } else {
            gameHtml = `<div> There are no games... :( </div>`;
        }


        document.getElementById("games-list").innerHTML = gameHtml;
    }



    // Event Handlers --------------------------------------------------------------------------------------------------

    onRefresh() {
        this.fetchGames();
    }



    /**
     * Method to run when the create playlist submit button is pressed. Call the MusicPlaylistService to create the
     * playlist.
    //  */
    // async onSubmit(event) {
    //     // Prevent the form from refreshing the page
    //     event.preventDefault();
    //
    //     // Set the loading flag
    //     let createButton = document.getElementById('create-concert');
    //     createButton.innerText = 'Loading...';
    //     createButton.disabled = true;
    //
    //     // Get the values from the form inputs
    //     const concertName = document.getElementById('concert-name').value;
    //     const date = document.getElementById('date').value;
    //     const baseTicketPrice = document.getElementById('ticket-price').value;
    //
    //     // Create the concert
    //     const concert = await this.client.createConcert(concertName, date, baseTicketPrice);
    //
    //     // Reset the form
    //     document.getElementById("create-playlist-form").reset();
    //
    //     // Re-enable the form
    //     createButton.innerText = 'Create';
    //     createButton.disabled = false;
    //     this.onRefresh();
    // }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const concertAdmin = new ConcertAdmin();
    concertAdmin.mount();
};

window.addEventListener('DOMContentLoaded', main);
