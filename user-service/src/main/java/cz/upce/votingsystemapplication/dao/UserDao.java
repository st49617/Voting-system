package cz.upce.votingsystemapplication.dao;

import cz.upce.votingsystemapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {

}
