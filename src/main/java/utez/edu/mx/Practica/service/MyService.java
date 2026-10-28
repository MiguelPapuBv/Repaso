package utez.edu.mx.Practica.service;

import org.springframework.stereotype.Service;
import utez.edu.mx.Practica.controller.dto.RequestCrearTicketDto;
import utez.edu.mx.Practica.controller.dto.RequestReasignarPrioridad;
import utez.edu.mx.Practica.model.Ticket;
import utez.edu.mx.Practica.model.Usuario;
import utez.edu.mx.Practica.repository.TicketRepository;
import utez.edu.mx.Practica.repository.UsuarioRepository;

@Service
public class MyService {

    private final TicketRepository ticketRepository;

    private final UsuarioRepository usuarioRepository;


    public MyService(TicketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Ticket crearTicket (RequestCrearTicketDto payload){

        //Esto es para tener la conexion con el usuario
        Usuario usuario = usuarioRepository.findById(payload.getIdUsuario())
                //Aqui lanzamos un error si no existe para que no truene
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));
        //Aqui comparamos que el usuario si este activo
        if(!usuario.getActivo()){
            //si no lanzamos un error personalizado
            throw new RuntimeException("El usuario no esta activo");
        }
        //Aqui hacemos para que podamos setear los datos al ticket
        Ticket ticket = new Ticket();
        //Seteamos la descripcion
        ticket.setDescripcion(payload.getDescripcion());
        //Seteamos el estado por default que es abierto
        ticket.setEstado("Abierto");
        //Seteamos la prioridad
        ticket.setPrioridad(payload.getPrioridad());
        ticket.setUsuario(usuario); // vincula el ticket al usuario

        //Con el save guardamos
        return  ticketRepository.save(ticket);
    }

    public Ticket actualizarEstado(Long idTicket){

        Ticket ticket = ticketRepository.findById(idTicket)
                .orElseThrow(()-> new RuntimeException("El ticket no existe"));

        if(ticket.getEstado().equals("ABIERTO")){

            ticket.setEstado("EN_PROCESO");
        } else if (ticket.getEstado().equals("EN_PROCESO")) {
            ticket.setEstado("CERRADO");
        } else{
            throw new RuntimeException("EL ticket ya esta cerrado");
        }

        return ticketRepository.save(ticket);

    }

    public Ticket reasignarPrioridad (RequestReasignarPrioridad payload){
        Ticket ticket = ticketRepository.findById(payload.getId())
                .orElseThrow(()-> new RuntimeException("El ticket no existe"));
        if(ticket.getEstado().equals("CERRADO")){
            throw new RuntimeException("El ticket o se puede validar porque esta cerrado");
        }

        ticket.setPrioridad(payload.getPrioridad());

        return ticketRepository.save(ticket);
    }

}

