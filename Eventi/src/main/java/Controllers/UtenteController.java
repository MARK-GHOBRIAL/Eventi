package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Entities.Prenotazione;
import Entities.Utente;
import Services.UtenteService;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Utente> getUtenti() {
        return this.utenteService.getUtenti();
    }

    @GetMapping("/me")
    public Utente getUtente(@AuthenticationPrincipal Utente utente) {
        return utente;
    }

    @GetMapping("/me/prenotazioni")
    public List<Prenotazione> getPrenotazioniUtente(@AuthenticationPrincipal Utente utente) {
        return utente.getPrenotazioni();
    }
}
