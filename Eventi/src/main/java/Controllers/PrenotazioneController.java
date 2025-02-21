package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import Entities.Prenotazione;
import Entities.Utente;
import payloads.PrenotazioneDTO;
import payloads.PrenotazioneResponseDTO;
import Services.PrenotazioneService;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Prenotazione> getPrenotazioni() {
        return this.prenotazioneService.getPrenotazioni();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public PrenotazioneResponseDTO savePrenotazioni(@RequestBody PrenotazioneDTO payload) {
        Prenotazione a = prenotazioneService.savePrenotazione(payload);
        return new PrenotazioneResponseDTO(a.getId());
    }
}
