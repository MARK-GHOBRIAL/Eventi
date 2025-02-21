package payloads.errorPayloads;


import java.time.LocalDateTime;

public record ErrorDTO(String message, LocalDateTime time) {
}