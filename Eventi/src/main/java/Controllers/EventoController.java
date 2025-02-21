package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.events.Event;
import Entities.Evento;
import payloads.EventoDTO;
import Services.EventoService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> getEventi() {
        return this.eventoService.getEventi();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Evento saveEvento(@RequestBody EventoDTO body) {
        return eventoService.saveEvento(body);
    }

    @PostMapping("/{id}/upload")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Evento uploadAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable int id) {
        try {
            return eventoService.uploadPicture(id, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Evento modificaEvento(@PathVariable long id, @RequestBody Evento body) {
        return eventoService.findByIdAndUpdate(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminaEvento(@PathVariable long id) {
        eventoService.findByIdAndDelete(id);
    }
}
