package med.voll.api.repositories;

import med.voll.api.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsersRepository extends JpaRepository<Users,Long> {

    UserDetails findByUserName(String username);
}
