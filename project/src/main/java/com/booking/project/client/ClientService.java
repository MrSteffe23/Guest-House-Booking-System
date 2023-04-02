package com.booking.project.client;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class which implements the contract of the interface {@link IClientService}. <br>
 * It has the implementation of the methods for PostgreSQL database.
 * Here is all the logic of the application.
 */
@Service
public class ClientService implements IClientService{
    /**
     * Attribute which represents the DataAccess layer.
     */
    private final ClientRepository clientRepository;

    /**
     * Constructor which have the role to implement Dependency Injection for the clientRepository attribute.
     * @param clientRepository the reference to the DataAccess layer.
     */
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * This method is used for display purposes. You can see all the clients in the database.
     * @return all the clients in the database in a List.
     */
    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    /**
     * Creates a Client in the database, given the data in the parameter.
     * @param client JSON with data for a house.
     */
    @Override
    public void createClient(Client client) {
        validateEmail(client.getEmail());
        validatePhoneNumber(client.getPhoneNumber());
        clientRepository.save(client);
    }

    /**
     * First, this method checks to see if the database have a client with the specified id. If yes,
     * the client is being deleted, otherwise, an exception is thrown.
     * @param id the id of the Client to be deleted.
     * @throws IllegalStateException if the database doesn't have a client with the specified id.
     */
    @Override
    public void deleteClient(Long id) {
        checkValidIdClient(id);
        clientRepository.deleteById(id);
    }

    @Override
    public void updateClient(Long id, Client client) {

    }

    /**
     * Validates of the String "email" is a valid email
     * @param email the String which is tested for an email
     * @throws IllegalStateException if the String given as parameter it's not an email
     */
    private void validateEmail(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        if(pattern.matcher(email).matches() == false){
            throw new IllegalStateException(String.format("The email %s it's not valid. Please insert another one", email));
        }
    }

    /**
     * Validates if the String "phoneNumber" is a valid real phone number. It only checks to have 10 digits with no spaces
     * @param phoneNumber the String which is tested for a phoneNumber
     * @throws IllegalStateException if the String given as parameter it's not a phoneNumber
     */
    private void validatePhoneNumber(String phoneNumber){
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        if(pattern.matcher(phoneNumber).matches() == false){
            throw new IllegalStateException(String.format("The phoneNumber %s it's not valid. Please insert another" +
                    "one containing 10 digits, no spaces", phoneNumber));
        }
    }

    /**
     * Functions which verifies if the database has a client with the specified id
     * @param id id of the client that you want to look for
     */
    public void checkValidIdClient(Long id){
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(!clientOptional.isPresent()){
            throw new IllegalStateException(String.format("The Client with id %s doesn't exist.", id));
        }
    }

}
