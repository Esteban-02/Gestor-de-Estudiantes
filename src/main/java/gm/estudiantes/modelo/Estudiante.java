package gm.estudiantes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data   // genera metodos set y get automaticamente
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // es para usarlo como generador de id autoincrementable
    private int idEstudiante;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;


}
