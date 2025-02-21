package Services;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import Entities.Evento;
import Exceptions.BadRequestException;
import Exceptions.NotFoundExceptions;
import payloads.EventoDTO;
import Repositories.EventoDAO;

import java.io.IOException;
import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoDAO eventoDAO;
    @Autowired
    private Cloudinary cloudinary;

    public List<Evento> getEventi() {
        return this.eventoDAO.findAll();
    }

    public Evento findById(long id) {
        return eventoDAO.findById(id).orElseThrow(() -> new NotFoundExceptions(id));
    }

    public Evento findByIdAndUpdate(long id, Evento body) {
        Evento found = this.findById(id);
        if (body.getTitolo() != null) {
            found.setTitolo(body.getTitolo());
        }
        if (body.getDescrizione() != null) {
            found.setDescrizione(body.getDescrizione());
        }
        if (body.getData() != null) {
            found.setData(body.getData());
        }
        if (body.getLuogo() != null) {
            found.setLuogo(body.getLuogo());
        }
        if (body.getPostiDisponibili() != 0) {
            found.setPostiDisponibili(body.getPostiDisponibili());
        }
        if (body.getAvatar() != null) {
            found.setAvatar(body.getAvatar());
        }

        return eventoDAO.save(found);
    }

    public void findByIdAndDelete(long id) {
        Evento found = this.findById(id);
        if (!found.getPrenotazioni().isEmpty()) {
            throw new BadRequestException("ATTENZIONE impossibile cancellare Evento di id: " + id + " è associato a uno o più prenotazioni.");
        }
        eventoDAO.delete(found);
    }

    public Evento saveEvento(EventoDTO payload) {
        Evento newEvento = new Evento();
        newEvento.setTitolo(payload.titolo());
        newEvento.setData(payload.data());
        newEvento.setDescrizione(payload.descrizione());
        newEvento.setLuogo(payload.luogo());
        newEvento.setPostiDisponibili(payload.postiDisponibili());
        newEvento.setAvatar("https://ui-avatars.com/api/?name=" + payload.titolo());
        return eventoDAO.save(newEvento);
    }

    public Evento uploadPicture(int id, MultipartFile file) throws IOException {
        Evento found = this.findById(id);
        String url = (String) cloudinary.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap())
                .get("url");
        found.setAvatar(url);
        return eventoDAO.save(found);
    }

}