package Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Entities.Prenotazione;

@Repository
public interface PrenotazioneDAO extends JpaRepository<Prenotazione, Long> {
}
