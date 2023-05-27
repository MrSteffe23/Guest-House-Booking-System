package com.booking.project.client;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Class is the closest point from the user. It defines the end points of the
 * application and lets the {@link ClientService} to deal with the logic behind this operations.<br>
 * All requests from the users are coming here first.
 */
@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    private final IClientService clientService;

    /**
     * Constructor which have the role to implement Dependency Injection for the clientService attribute.
     * @param clientService the reference to the Service layer.
     */
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return a list with all the Clients in the database.
     */
    @GetMapping
    public List<Client> getClients(){
        return clientService.getClients();
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @param id the id of the Client to be found.
     */
    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id){
        return clientService.getClient(id);
    }

    /**
     * Inserts a new Client in the database, if possible.
     * @param client JSON with all the data for a Client
     */
    @PostMapping
    public Client createClient(@RequestBody Client client){
        return clientService.createClient(client);
    }

    /**
     * Deletes a Client from the database, if possible.
     * @param id the id of the Client to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }

    /**
     * Updates a Client from the database with new specifications.
     * @param id the id of the Client to be updated.
     * @param client the Client with new specifications.
     */
    @PutMapping("/{id}")
    public void updateClient(@RequestBody Client client, @PathVariable Long id){
        clientService.updateClient(id, client);
    }
}
