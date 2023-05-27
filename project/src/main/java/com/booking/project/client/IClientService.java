package com.booking.project.client;

import java.util.List;

/**
 * Interface which separates the database from the java logic. <br>
 * Here are all the methods used to interact with the lower level of database
 */
public interface IClientService {

    /**
     * Get all the Clients in the database
     * @return The list of the Clients found in the database
     */
    List<Client> getClients();

    /**
     * Get a Client from the database
     * @return a Client from the database
     */
    Client getClient(Long id);

    /**
     * Method used to insert a new Client in the database
     * @param client a new Client to insert in the database
     */
    Client createClient(Client client);

    /**
     * Method used to delete a Client from the database (if the Client exists).
     * @param id the id of the Client to be deleted.
     */
    void deleteClient(Long id);

    /**
     * Method used to update a Client with different details (if the Client exists).
     * @param id the id of the Client to be updated.
     * @param client the Client new specifications.
     */
    void updateClient(Long id, Client client);

}
