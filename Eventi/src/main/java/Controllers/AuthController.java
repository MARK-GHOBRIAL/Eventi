package Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import Entities.Utente;
import Exceptions.BadRequestException;
import payloads.UtenteDTO;
import payloads.UtenteResponseDTO;
import payloads.loginPayload.UtenteLoginDTO;
import payloads.loginPayload.UtenteLoginResponseDTO;
import Services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UtenteLoginResponseDTO login(@RequestBody UtenteLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UtenteLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UtenteResponseDTO createUser(@RequestBody @Validated UtenteDTO newUserPayload, BindingResult validation) {
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        } else {
            Utente newUtente = authService.save(newUserPayload);
            return new UtenteResponseDTO(newUtente.getId());
        }
    }
}
