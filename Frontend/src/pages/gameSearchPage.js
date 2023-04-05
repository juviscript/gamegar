import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ExampleClient from "../api/videoGameClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class GameSearchPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onStateChange', 'onCreate', 'renderExample'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        this.client = new ExampleClient();

        this.dataStore.addChangeListener(this.renderExample)
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderExample() {
        let resultArea = document.getElementById("result-info");

        const example = this.dataStore.get("example");

        if (example) {
            resultArea.innerHTML = `
                <div>ID: ${example.id}</div>
                <div>Name: ${example.name}</div>
            `
        } else {
            resultArea.innerHTML = "No Item";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;
        this.dataStore.set("example", null);

        let result = await this.client.getExample(id, this.errorHandler);
        this.dataStore.set("example", result);
        if (result) {
            this.showMessage(`Got ${result.name}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async getAllGames() {
        const games = await this.client.getAllGames(this.errorHandler)

        //
        // if (games && games.length > 0) {
        //     for (const games of games) {
        //         games.reservations = await this.fetchReservations(concert.id);           Commenting out as I'm unsure if this is required.
        //         games.purchases = await this.fetchPurchases(concert.id);
        //     }
        // }

        this.dataStore.set("games", games);
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    renderGames() {
        let gameHTML = "";
        const concerts = this.dataStore.get("concerts");

        if (concerts) {
            for (const concert of concerts) {
                gameHTML += `
                    <div class="card">
                        <h2>${concert.name}</h2>
                        <div>Date: ${concert.date}</div>
                        <div>Base Price: ${this.formatCurrency(concert.ticketBasePrice)}</div>
                        <p>
                            <h3>Ticket Reservations</h3>
                            <ul>
                `;
                if (concert.reservations && concert.reservations.length > 0) {
                    for (const reservation of concert.reservations) {
                        gameHTML += `
                                <li>
                                    <div>Ticket ID: ${reservation.ticketId}</div>
                                    <div>Date Reserved: ${reservation.dateOfReservation}</div>
                                    <div>Reservation Closed: ${reservation.reservationClosed}</div>
                                    <div>Date Reservation Closed: ${reservation.dateReservationClosed}</div>
                                    <div>Ticket Purchased: ${reservation.purchasedTicket}</div>
                                </li>
                        `;
                    }
                } else {
                    gameHTML += `
                                <li>No Ticket Reservations.</li>
                    `;
                }
                gameHTML += `
                            </ul>
                        </p>
                        <p>
                            <h3>Ticket Purchases</h3>
                            <ul>
                `;
                if (concert.purchases && concert.purchases.length > 0) {
                    for (const purchase of concert.purchases) {
                        gameHTML += `
                                <li>
                                    <div>Ticket ID: ${purchase.ticketId}</div>
                                    <div>Date Purchased: ${purchase.dateOfPurchase}</div>
                                    <div>Price Paid: ${purchase.pricePaid}</div>
                                </li>
                        `;
                    }
                } else {
                    gameHTML += `
                                <li>No Ticket Purchases.</li>
                    `;
                }
                gameHTML += `
                            </ul>
                        </p>
                    </div>`;
            }
        } else {
            gameHTML = `<div>There are no concerts...</div>`;
        }

        document.getElementById("concert-list").innerHTML = gameHTML;
    }


    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("example", null);

        let name = document.getElementById("create-name-field").value;

        const createdExample = await this.client.createExample(name, this.errorHandler);
        this.dataStore.set("example", createdExample);

        if (createdExample) {
            this.showMessage(`Created ${createdExample.name}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
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
