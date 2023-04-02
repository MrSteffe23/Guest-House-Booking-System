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

    @PostMapping
    public void createClient(@RequestBody Client client){
        clientService.createClient(client);
    }
}
