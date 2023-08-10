import BaseClass from "../util/baseClass";
import axios from 'axios'

export default class UserClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getAllUsers', 'getUserById', 'createUser'];
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
    async getUserById(userId, errorCallback) {
        try {
            const response = await this.client.get(`/users/${userId}`);
            return response.data.game;
        } catch (error) {
            this.handleError("getUserById", error, errorCallback)
        }
    }

    async createUser (name, username, email, birthday, errorCallback) {
        try {
            const response = await this.client.post(`users`, {
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