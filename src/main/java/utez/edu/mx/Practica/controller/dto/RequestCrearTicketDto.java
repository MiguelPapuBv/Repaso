package utez.edu.mx.Practica.controller.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCrearTicketDto {

    private Long idUsuario;

    @NotBlank(message = "La descripcion no debe estar vacia")
    private String descripcion;


    private String prioridad;
}
