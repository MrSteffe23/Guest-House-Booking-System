package com.booking.project.admin;

import com.booking.project.house.IHouseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Class which implements the contract of the interface {@link IHouseService}. <br>
 * It has the implementation of the methods for PostgreSQL database.
 * Here is all the logic of the application.
 */
@Service
public class AdminService implements IAdminService{
    /**
     * Attribute which represents the DataAccess layer.
     */
    private final AdminRepository adminRepository;
    /**
     * Constructor which have the role to implement Dependency Injection for the adminRepository attribute.
     * @param adminRepository the reference to the DataAccess layer.
     */
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    /**
     * This method is used for display purposes. You can see all the admins in the database.
     * @return all the admins in the database in a List.
     */
    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    /**
     * Creates an Admin in the database, given the data in the parameter.
     * @param admin JSON with data for an admin.
     */
    @Override
    public void createAdmin(Admin admin) {
        validateUsername(admin.getUsername());
        validateEmail(admin.getEmail());
        adminRepository.save(admin);
    }

    /**
     * First, this method checks to see if the database have an admin with the specified id. If yes,
     * the admin is being deleted, otherwise, an exception is thrown.
     * @param id the id of the Admin to be deleted.
     * @throws IllegalStateException if the database doesn't have an admin with the specified id.
     */
    @Override
    public void deleteAdmin(Long id) {
        checkValidIdAdmin(id);
        adminRepository.deleteById(id);
    }

    /**
     * First, this method checks to see if an Admin with the specified id exists, so that it can be updated.<br>
     * Second, the method verifies the username and email of the new admin.<br>
     * Then, the Admin extracted with the specified id is updated and then saved in the database.
     * @param id the id of the Admin to be updated.
     * @param admin the Admin with new specifications.
     */
    @Override
    public void updateAdmin(Long id, Admin admin) {
        checkValidIdAdmin(id);
        validateEmail(admin.getEmail());
        Admin adminToUpdate = adminRepository.findById(id).get();
        if(!(admin.getUsername().equals(adminToUpdate.getUsername())))
            validateUsername(admin.getUsername());

        adminToUpdate.setUsername(admin.getUsername());
        adminToUpdate.setPassword(admin.getPassword());
        adminToUpdate.setEmail(admin.getEmail());

        adminRepository.save(adminToUpdate);
    }

    /**
     * Functions which verifies if the database has an admin with the specified id
     * @param id id of the admin that you want to look for
     */
    public void checkValidIdAdmin(Long id){
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if(!adminOptional.isPresent()){
            throw new IllegalStateException(String.format("The Admin with id %s doesn't exist.", id));
        }
    }

    /**
     * Checks if the database have an Admin called "username". If yes, then this method throws
     * an error with a message, if not, it does nothing.
     * @param username the username of a possible Admin.
     * @throws IllegalStateException if the database already have an admin called "username".
     */
    private void validateUsername(String username){
        Optional<Admin> adminOptional = adminRepository.getHouseByusername(username);
        if(adminOptional.isPresent()){
            throw new IllegalStateException(String.format("The admin %s already exists", username));
        }
    }

    /**
     * Verifies if the String "email" is a valid email
     * @param email the String which is tested for an email
     * @throws IllegalStateException if the String given as parameter it's not an email
     */
    private void validateEmail(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        if(pattern.matcher(email).matches() == false){
            throw new IllegalStateException(String.format("The email %s it's not valid for an admin. Please insert another one", email));
        }
    }
}
