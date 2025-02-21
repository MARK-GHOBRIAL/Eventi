package Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Entities.Utente;

import java.util.Optional;

@Repository
public interface UtenteDAO extends JpaRepository<Utente, Long> {
    Optional<Utente> findByEmail(String email);

    Optional<Utente> findByUsername(String username);

}
