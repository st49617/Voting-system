package cz.upce.votingsystemapplication.service;

import cz.upce.votingsystemapplication.dao.UserDao;
import cz.upce.votingsystemapplication.dto.UserDto;
import cz.upce.votingsystemapplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void addUser(User user) {
        userDao.save(user);
    }

    public UserDto getUser(Long id) {
        Optional<User> foundUser = userDao.findById(id);
        if (foundUser.isPresent()) {
            UserDto createdUserDto = new UserDto(foundUser.get().getId(), foundUser.get().getFirstName(), foundUser.get().getLastName(), foundUser.get().getEmail());
            return createdUserDto;
        } else {
            return null;
        }
    }

    public List<UserDto> getAllUsers() {
        List<User> foundUsers = userDao.findAll();
        List<UserDto> foundUserDtos = new ArrayList<>(foundUsers.size());
        for (User foundUser : foundUsers) {
            foundUserDtos.add(new UserDto(foundUser.getId(), foundUser.getFirstName(), foundUser.getLastName(), foundUser.getEmail()));
        }
        return foundUserDtos;
    }

    public void deleteUser(Long id) {
        Optional<User> foundUser = userDao.findById(id);
        if (foundUser.isPresent()) {
            userDao.delete(foundUser.get());
        } else {
            throw new RuntimeException("Uživatel se zadaným ID neexistuje");
        }

    }

}
