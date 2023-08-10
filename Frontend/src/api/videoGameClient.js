import BaseClass from "../util/baseClass";
import axios from 'axios'

export default class VideoGameClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getAllGames', 'getGameById', 'createGame', 'getGameByTitle', 'getGameByGenre'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    /**
     * Get all games
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns an array of games
     */
    async getAllGames(errorCallback) {
        try {
            const response = await this.client.get(`/games/all`);
            return response.data;
        } catch(error) {
            this.handleError("getAllGames", error, errorCallback);
        }
    }

    /**
     * Gets the game for the given ID.
     * @param id Unique identifier for a concert
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The game
     */
    async getGameById(id, errorCallback) {
        try {
            const response = await this.client.get(`/games/${id}`);
            return response.data;
        } catch (error) {
            this.handleError("getGameById", error, errorCallback)
        }
    }

    async getGameByGenre(genre, errorCallback) {
        try {
            const response = await this.client.get(`/games/${genre}`);
            return response.data.game;
        } catch (error) {
            this.handleError("getGameByGenre", error, errorCallback)
        }
    }

    async createGame(title, developer, genre, year, description, country, platforms, tags, image, errorCallback) {
        try {
            const response = await this.client.post(`games`, {
                title: title,
                developer: developer,
                genre: genre,
                year: year,
                description: description,
                country: country,
                platforms: platforms,
                tags: tags,
                image: image
            });
            return response.data;
        } catch (error) {
            this.handleError("createGame", error, errorCallback);
        }
    }

    /**
     *
     * @param title
     * @param errorCallback
     * @returns {Promise<*>}
     */
    async getGameByTitle(title, errorCallback) {
        try {
            const response = await this.client.get(`/games/${title}`);
            return response.data;
        } catch (error) {
            this.handleError("getGameByTitle", error, errorCallback);
        }
    }


    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
}
