package com.m2testingfranciscovilla.m2testingfranciscovilla;

import com.m2testingfranciscovilla.m2testingfranciscovilla.entities.Student;
import com.m2testingfranciscovilla.m2testingfranciscovilla.repository.StudentRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class M2TestingFranciscoVillaApplication  implements CommandLineRunner {

	@Autowired
	StudentRepositoryI studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(M2TestingFranciscoVillaApplication.class, args);
	}

	public void showMenu(){
		System.out.println("**********************************************************");
		System.out.println("GESTIÓN DE ALUMNOS");
		System.out.println("0 - Salir");
		System.out.println("1 - Ver todos los alumnos");
		System.out.println("2 - Ver un alumno por id");
		System.out.println("3 - Insertar un nuevo alumno");
		System.out.println("4 - Modificar/Actualizar un alumno existente");
		System.out.println("5 - Borrar un alumno existente por id");
		System.out.println("6 - Borrar todos los alumnos");
		System.out.println("7 - Ver numero de alumnos");
		System.out.println("**********************************************************");
		System.out.println("seleccione una opción:");
	}
	@Override
	public void run(String... args) throws Exception {

		//Creamos unos alumnos de ejemplo

		Student student1= new Student(null,"77777777M","Francisco Villa","Matematicas",8.6);
		Student student2= new Student(null,"99999999L","Maria Perez","Lenguaje",7.1);
		studentRepository.save(student1);
		studentRepository.save(student2);
		while (true) {
			Scanner scanner = new Scanner(System.in);
			showMenu();
			try { // pedir una opción por consola
				int opcion = scanner.nextInt();
				scanner.nextLine();

				if (opcion == 0) {
					System.out.println("Hasta la próxima");
					break;
				} else if (opcion == 1) {
					// Buscar todos los alumnos que hay en la base de datos
					List<Student> students = studentRepository.findAll();
					// comprobar si la longitud es 0,
					if (students.isEmpty()) {
						System.out.println("No hay alumnos disponibles.");
					} else {
						System.out.println(students);
					}

				} else if (opcion == 2) {
					System.out.println("Por favor, introduzca el id del alumnos que desea ver");
					Long id = scanner.nextLong();

					Optional<Student> studentOptional = studentRepository.findById(id);

					if (studentOptional.isPresent()) { // si hay laptop entonces lo recupero con get()
						Student student = studentOptional.get();
						System.out.println(student);
					} else {
						System.out.println("No existe el alumnor solicitado");
					}

				} else if (opcion == 3) {
					System.out.println("Introduce el dni:");
					String dni = scanner.nextLine();


					System.out.println("Introduce el nombre:");
					String nombre = scanner.nextLine();



					System.out.println("Introduce la asignatura:");
					String asignatura = scanner.nextLine();

					System.out.println("Introduce la nota:");
					Double nota = scanner.nextDouble();


					Student laptopToInsert = new Student(null, dni, nombre, asignatura,nota);


					studentRepository.save(laptopToInsert);
					System.out.println("Alumno creado correctamente");
				} else if (opcion == 4) {
					System.out.println("Por favor, introduzca el id del alumno que desea modificar");
					Long id = scanner.nextLong();
					scanner.nextLine();
					Optional<Student> studentOptional = studentRepository.findById(id);

					if (studentOptional.isEmpty()) {
						System.out.println("No existe el alumno solicitado");
						continue;
					}
					Student student = studentOptional.get();


					System.out.println("Introduce el dni (actual: " + student.getDni() + ")");
					String dni = scanner.nextLine();
					student.setDni(dni);


					System.out.println("Introduce el nombre: (actual: " + student.getNombre() + ")");
					String nombre = scanner.nextLine();
					student.setNombre(nombre);

					System.out.println("Introduce la asignatura : (actual: " + student.getAsignatura() + ")");
					String asignatura = scanner.nextLine();
					student.setAsignatura(asignatura);

					System.out.println("Introduce la nota (actual: " + student.getNota() + ")");
					Double nota = scanner.nextDouble();
					scanner.nextLine();
					student.setNota(nota);


					studentRepository.save(student);
					System.out.println("Alumno actualizado correctamente!");
				} else if (opcion == 5) {
					System.out.println("Introduzca el id del alumno que desea borrar:");
					Long id = scanner.nextLong();
					boolean exists = studentRepository.existsById(id);

					if (exists) {
						studentRepository.deleteById(id); // SQL: DELETE FROM laptop WHERE id = X
						System.out.println("Alumno borrado");
					} else {
						System.out.println("No existe el alumno solicitado");
					}


				} else if (opcion == 6) {

					System.out.println("Esto borrará todos los alumnos, ¿está seguro? (true o false)");
					boolean confirm = scanner.nextBoolean();

					if (!confirm) continue;

					studentRepository.deleteAll();
					System.out.println("Alumnos borrados correctamente");

				}else if(opcion ==7){
					System.out.println("El numero de alumnos es : " + studentRepository.count());


				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}

}
