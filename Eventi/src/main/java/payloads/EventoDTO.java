package payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EventoDTO(
        @NotEmpty(message = "titolo è obbligatorio")
        @Size(min = 3, max = 30, message = "Il titolo deve avere minimo 3 caratteri, massimo 30")
        String titolo,
        @NotEmpty(message = "la descrizione è obbligatoria")
        @Size(min = 3, max = 30, message = "la descrizione deve avere minimo 3 caratteri, massimo 30")
        String descrizione,
        @NotEmpty(message = "la data è obbligatoria")
        LocalDate data,
        @NotEmpty(message = "il luogo è obbligatoria")
        @Size(min = 3, max = 30, message = "il luogo deve avere minimo 3 caratteri, massimo 30")
        String luogo,
        @NotNull(message = "posti disponibili è un campo obbligatorio!")
        Integer postiDisponibili
) {
}
