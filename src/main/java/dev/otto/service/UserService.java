package dev.otto.service;

import dev.otto.domain.Users;
import dev.otto.exceptions.UserNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {
    public Users createUser(Users users) {
        Users.persist(users);
        return users;
    }

    public List<Users> findAllUsers(Integer page, Integer pageSize) {
        return Users.findAll()
                .page(page,pageSize)
                .list();
    }

    public Users findUsersById(UUID userID) {
        return (Users) Users.findByIdOptional(userID).orElseThrow(UserNotFoundException::new);
    }

    public Users updateUser(UUID userID, Users users) {
        var user = findUsersById(userID);

        user.username = users.username;
        user.email = users.email;

        Users.persist(user);
        return user;
    }

    public void deleteUser(UUID userID) {
       var user = findUsersById(userID);

        Users.deleteById(user.id);
    }
}
