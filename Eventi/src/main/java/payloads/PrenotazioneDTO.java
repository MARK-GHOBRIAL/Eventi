package payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PrenotazioneDTO(
        @NotNull(message = "l'id dell' evento è un campo obbligatorio!")
        int evento_id,
        @NotNull(message = "Il numero dei partecipanti è un campo obbligatorio!")
        int numero_partecipanti,
        @NotNull(message = "l'id dell'user è un campo obbligatorio!")
        int utente_id,
        @NotEmpty(message = "la data è obbligatoria")
        LocalDate data
) {
}