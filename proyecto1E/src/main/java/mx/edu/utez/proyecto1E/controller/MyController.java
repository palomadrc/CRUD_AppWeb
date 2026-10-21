package mx.edu.utez.proyecto1E.controller;

import mx.edu.utez.proyecto1E.controller.dtos.RequestBodyDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"}) //permitir cualquier origen
@RequestMapping("/my-services")
public class MyController {

    @GetMapping
    public String miPrimerServicio(){
        return "Hello world";
    }

    @GetMapping("/segundo")
    public String segundoServicio(){
        return "Este es el servicio";
    }

    @PostMapping
    public String tercerServicio(){
        return "Tercer servicio";
    }

    @GetMapping("/path/{id}")
    public String pathVariable(@PathVariable String id){
        return "Hola: " + id;
    }

    @PostMapping("/body")
    public String body(@RequestBody RequestBodyDTO payload){

        System.out.println(payload.getNombre());
        System.out.println(payload.getEdad());
        return "Body";
    }

}
