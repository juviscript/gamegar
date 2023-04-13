import BaseClass from '../util/baseClass';
import DataStore from '../util/DataStore';
import VideoGameClient from "../api/videoGameClient";

/**
 * Logic needed for the create playlist page of the website.
 */
class GameAdmin extends BaseClass {
    constructor() {
        super();
        this.bindClassMethods(['onSubmit', 'onRefresh', 'renderGames'], this);
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
        this.fetchGames();


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


    // Render Methods --------------------------------------------------------------------------------------------------

    renderGames() {
        let gameHtml = "";                              // Variable named gameHtml that equals an empty string
        const games = this.dataStore.get("games");

        if (games) {
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
                            <img src = ${game.image} width="400" height="400">
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

    async onSubmit(event) {
        // Prevent the form from refreshing the page
        event.preventDefault();

        // Set the loading flag
        let createButton = document.getElementById('create-game');
        createButton.innerText = 'Loading...';
        createButton.disabled = true;


        // Get the values from the form inputs
        const title = document.getElementById('title').value;
        const developer = document.getElementById('developer').value;
        const genre = document.getElementById('genre').value;
        const year = document.getElementById('year').value;
        const description = document.getElementById('description').value;
        const country = document.getElementById('country').value;
        let platforms = document.getElementById('platforms').value;
        const tags = document.getElementById('tags').value;
        const image = document.getElementById('image').value;

        ;

<<<<<<< HEAD
        // const output = document.querySelector('output');
        // const form = document.querySelector('form');
        //
        // form.addEventListener('input', (e) => {
        //     const data = new FormData(form);
        //     const url = new URL(form.action, window.location.href);              https://www.aleksandrhovhannisyan.com/blog/serializing-html-form-data-with-javascript/
        //     url.search = new URLSearchParams(data).toString();
        //     // do whatever you want with the URL
        // });


        const games = await this.client.createGame(title, developer, genre, year, description, country, platforms, tags, image, this.errorHandler);

=======
        // Create the concert
        const games = await this.client.createGame(title, developer, genre, year, description, country, platforms, tags, this.errorHandler);
>>>>>>> 63bdd9f (Fixed styling for database and user info cards.)

        // Reset the form
        document.getElementById("create-game-form").reset();

        // Re-enable the form
        createButton.innerText = 'Create';
        createButton.disabled = false;
        this.onRefresh();

    }



}



/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const gameAdmin = new GameAdmin();
    gameAdmin.mount();
};

window.addEventListener('DOMContentLoaded', main);


