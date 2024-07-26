package gm.estudiantes;

import gm.estudiantes.modelo.Estudiante;
import gm.estudiantes.servicio.EstudianteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class EstudiantesApplication implements CommandLineRunner {


	@Autowired
	private EstudianteServicio estudianteServicio;
	private static final Logger logger = LoggerFactory.getLogger(EstudiantesApplication.class);
	String nl = System.lineSeparator();
	static Scanner lectura = new Scanner(System.in);


	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion");

		//Levnta la fabrica de Spring
		SpringApplication.run(EstudiantesApplication.class, args);
		logger.info("Aplicacion finalizada ");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl+"ejecutando metodo run de Spring... "+nl);



		do{
			menu();
			int opc = lectura.nextInt();
			lectura.nextLine();
			opciones(opc);
			if (opc == 6 ){
				break;
			}

		}while(true);
	}

	public void menu(){
		System.out.print("""
     						Sistema de estudiantes
					1. Listar Estudiantes
					2. Buscar Estudiantes
					3. Agregar Estudiantes
					4. Modificar Estudiantes
					5. Eliminar Estudiantes
					6. Salir
					Escoge una opcion: """);
	}

	public void opciones(int menu){
		switch (menu){
			case 1:
				System.out.println("\tListar estudiantes ");
				List<Estudiante> listEstudiantes = estudianteServicio.listarEstudiantes();
				listEstudiantes.forEach(System.out::println);
				break;
			case 2:
				System.out.println("\tBuscar estudiante");
				System.out.print("Ingrese el id del estudiante: ");
				int idEstudiante = lectura.nextInt();
				lectura.nextLine();
				Estudiante estudiante = estudianteServicio.buscarEstudiantePorID(idEstudiante);
				if (estudiante != null){
					System.out.println("Estudiante encontrado \n"+ estudiante);
				}else{
					System.out.println("Estudiante no encontrado : "+ idEstudiante);
				}

				break;
			case 3:
				System.out.println("Agregar estudiante");
				System.out.print("Nombre: ");
				String nombre = lectura.nextLine();
				System.out.print("Apellido: ");
				String apellido = lectura.nextLine();
				System.out.print("Telefono: ");
				String telefono = lectura.nextLine();
				System.out.print("Email: ");
				String email = lectura.nextLine();
				Estudiante estudianteNuevo = new Estudiante();

				estudianteNuevo.setNombre(nombre);
				estudianteNuevo.setApellido(apellido);
				estudianteNuevo.setTelefono(telefono);
				estudianteNuevo.setEmail(email);
				estudianteServicio.guardarEstudiante(estudianteNuevo);
				break;
			case 4:
				System.out.println("\tModificar Estudiante");
				System.out.print("Ingrese el id del estudiante: ");
				int idEstudianteBuscar = lectura.nextInt();
				lectura.nextLine();
				Estudiante estudiante1 = estudianteServicio.buscarEstudiantePorID(idEstudianteBuscar);
				if (estudiante1 != null){
					System.out.print("Nombre: ");
					String nombre1 = lectura.nextLine();
					System.out.print("Apellido: ");
					String apellido1 = lectura.nextLine();
					System.out.print("Telefono: ");
					String telefono1 = lectura.nextLine();
					System.out.print("Email: ");
					String email1 = lectura.nextLine();

					estudiante1.setNombre(nombre1);
					estudiante1.setApellido(apellido1);
					estudiante1.setTelefono(telefono1);
					estudiante1.setEmail(email1);
					estudianteServicio.guardarEstudiante(estudiante1);

					System.out.println("Estudiante modificado "+estudiante1);

				}else {
					System.out.println("Estudiante no encontrado");
				}
				break;
			case 5:
				System.out.println("\tEliminar Estudiante ");
				System.out.print("Ingrese el id del estudiante:");
				int buscarEstudiante = lectura.nextInt();
				Estudiante estudianteEliminar = estudianteServicio.buscarEstudiantePorID(buscarEstudiante);
				if (estudianteEliminar != null) {
					estudianteServicio.eliminarEstudiante(estudianteEliminar);
					System.out.println("Estudiante con ID: " + buscarEstudiante + " fue eliminado");
				}else {
					System.out.println("Estudiante no encontrado");
				}
				break;
			case 6:
				System.out.println("\tVuelve pronto");
				break;
			default:
				System.out.println("Opcion incorrecta, vuleve a intentar !!");





		}
	}
}
