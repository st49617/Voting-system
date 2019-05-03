package cz.upce.votingsystemapplicationConfigServer.dao;

import cz.upce.votingsystemapplicationConfigServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {

}
