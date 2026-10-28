package utez.edu.mx.Practica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.Practica.model.Ticket;
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
