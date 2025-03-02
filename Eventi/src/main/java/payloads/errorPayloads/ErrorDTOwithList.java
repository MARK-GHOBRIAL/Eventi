package payloads.errorPayloads;


import java.time.LocalDateTime;
import java.util.List;

public record ErrorDTOwithList(
        String message,
        LocalDateTime timestamp,
        List<String> errorsList
) {
}
