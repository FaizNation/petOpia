package FaizNation.petopia_dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import FaizNation.petopia_dev.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}