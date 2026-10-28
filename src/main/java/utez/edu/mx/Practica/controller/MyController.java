package utez.edu.mx.Practica.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.Practica.controller.dto.RequestCrearTicketDto;
import utez.edu.mx.Practica.controller.dto.RequestReasignarPrioridad;
import utez.edu.mx.Practica.model.Ticket;
import utez.edu.mx.Practica.service.MyService;

@RestController
@RequestMapping("/myservices")
@CrossOrigin("*")
public class MyController {

    private final MyService myservice;

    public MyController(MyService myservice) {
        this.myservice = myservice;
    }

    @PostMapping("/crear-ticket")
    public ResponseEntity<Ticket> crearTicket
            (@RequestBody @Valid RequestCrearTicketDto payload){

        return ResponseEntity.status(201).body(myservice.crearTicket(payload));
    }

    @PatchMapping("/actualizar-estado/{id}")
    public ResponseEntity<Ticket> actualizarTicket(@PathVariable Long id){
        return ResponseEntity.status(200).body(myservice.actualizarEstado(id));
    }

    @PatchMapping("/reasignar-prioridad")
    public ResponseEntity<Ticket> reasignarPrioridad
            (@RequestBody @Valid RequestReasignarPrioridad payload){
        return ResponseEntity.status(201).body(myservice.reasignarPrioridad(payload));
    }
}
