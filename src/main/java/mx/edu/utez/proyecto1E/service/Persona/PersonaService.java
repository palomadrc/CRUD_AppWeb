package mx.edu.utez.proyecto1E.service.Persona;

import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestUpdatePersonaDto;
import mx.edu.utez.proyecto1E.exception.customExceptions.CustomBadException;
import mx.edu.utez.proyecto1E.model.persona.Persona;
import mx.edu.utez.proyecto1E.repository.persona.PersonaRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new CustomBadException("La persona con ID " + id + " no existe"));
    }

    public Persona updatePersona(Long id, RequestUpdatePersonaDto payload) {
        Persona persona = getPersonaById(id); // Valida existencia

        persona.setNombres(payload.getNombres());
        persona.setPrimerApellido(payload.getPrimerApellido());
        persona.setSegundoApellido(payload.getSegundoApellido());
        persona.setCurp(payload.getCurp());
        persona.setEmail(payload.getCorreo());
        persona.setFechaNacimiento(payload.getFechaNacimiento());

        return personaRepository.save(persona);
    }
}