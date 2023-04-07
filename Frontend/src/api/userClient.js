import BaseClass from "../util/baseClass";
import axios from 'axios'

export default class UserClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getAllUsers', 'getUserById', 'createUser', 'getUserByUsername', 'getUserByEmail', 'getGameByDeveloper'];
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
    async getAllUsers(errorCallback) {
        try {
            const response = await this.client.get(`/users`);
            return response.data;
        } catch(error) {
            this.handleError("getAllUsers", error, errorCallback);
        }
    }

    /**
     * Gets the game for the given ID.
     * @param id Unique identifier for a concert
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The game
     */
    async getUserById(id, errorCallback) {
        try {
            const response = await this.client.get(`/users/${id}`);
            return response.data.game;
        } catch (error) {
            this.handleError("getUserById", error, errorCallback)
        }
    }

    async getUserByUsername(username, errorCallback) {
        try {
            const response = await this.client.get(`/games/${username}`);
            return response.data.game;
        } catch (error) {
            this.handleError("getUserByUsername", error, errorCallback)
        }
    }

    async getUserByEmail(email, errorCallback) {
        try {
            const response = await this.client.get(`/games/${email}`);
            return response.data.game;
        } catch (error) {
            this.handleError("getUserByEmail", error, errorCallback)
        }
    }

    async createUser (name, username, email, birthday, errorCallback) {
        try {
            const response = await this.client.post(`games`, {
                name: name,
                username: username,
                email: email,
                birthday: birthday
            });
            return response.data;
        } catch (error) {
            this.handleError("createUser", error, errorCallback);
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