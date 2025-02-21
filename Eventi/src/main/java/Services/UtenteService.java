package Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pierpaolo.colasante.u5w3d5.entities.Evento;
import pierpaolo.colasante.u5w3d5.entities.Utente;
import pierpaolo.colasante.u5w3d5.exceptions.BadRequestException;
import pierpaolo.colasante.u5w3d5.exceptions.NotFoundExceptions;
import pierpaolo.colasante.u5w3d5.payloads.UtenteDTO;
import pierpaolo.colasante.u5w3d5.repositories.UtenteDAO;

import java.util.List;

@Service
public class UtenteService {
    @Autowired
    private UtenteDAO utenteDAO;

    public List<Utente> getUtenti() {
        return this.utenteDAO.findAll();
    }

    public Utente findById(long id) {
        return utenteDAO.findById(id).orElseThrow(() -> new NotFoundExceptions(id));
    }

    public Utente findByEmail(String email) {
        return utenteDAO.findByEmail(email).orElseThrow(() -> new NotFoundExceptions("Utente con email " + email + " non trovata!!!"));
    }

    public Utente findByIdAndUpdate(long id, Utente body) {
        Utente found = this.findById(id);
        if (body.getUsername() != null) {
            found.setUsername(body.getUsername());
        }
        if (body.getNome() != null) {
            found.setNome(body.getNome());
        }
        if (body.getCognome() != null) {
            found.setCognome(body.getCognome());
        }
        if (body.getEmail() != null) {
            found.setEmail(body.getEmail());
        }
        if (body.getPassword() != null) {
            found.setPassword(body.getPassword());
        }
        if (body.getRuolo() != null) {
            found.setRuolo(body.getRuolo());
        }

        return utenteDAO.save(found);
    }

    public void findByIdAndDelete(int id) {
        Utente found = this.findById(id);
        if (!found.getPrenotazioni().isEmpty()) {
            throw new BadRequestException("ATTENZIONE impossibile cancellare Utente di id: " + id + " è associato a uno o più prenotazioni.");
        }
        utenteDAO.delete(found);
    }
}
