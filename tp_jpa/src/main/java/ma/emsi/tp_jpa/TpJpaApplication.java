package ma.emsi.tp_jpa;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ma.emsi.tp_jpa.entities.Patient;
import ma.emsi.tp_jpa.repositories.PatientRepository;

@SpringBootApplication
public class TpJpaApplication implements CommandLineRunner {
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(TpJpaApplication.class, args);
	} 
	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null, "Kawtar", new Date(), 40000, false));
		patientRepository.save(new Patient(null, "Kevkava", new Date(), 8764, false));
		patientRepository.save(new Patient(null, "Ismail", new Date(), 980, true));
		patientRepository.save(new Patient(null, "Amine", new Date(), 5835, false));

		patientRepository.findAll().forEach(p -> {
			System.out.println(p.toString());
		});
		System.out.println("--------------------------------");
		Patient patient = patientRepository.findById(4L).get();
		System.out.println(patient.getNom());

		System.out.println("----------------Recherche par Nom----------------");
		List<Patient> patients = patientRepository.findByNomContains("K");
		patients.forEach(p -> {
			System.out.println(p.toString());
		});

		System.out.println("------------Recherche par Malade--------------------");
		List<Patient> patientsMal = patientRepository.findByMalade(true);
		patientsMal.forEach(p -> {
			System.out.println(p.toString());
		});
		
		System.out.println("--------------Recherche par Nom et Malade------------------");
		List<Patient> patientsNomMal = patientRepository.findByNomContainsAndMalade("a",true);
		patientsNomMal.forEach(p -> {
			System.out.println(p.toString());
		});
		
		patientRepository.deleteById(3L);
		System.out.println("--------------Suppression------------------");
		List<Patient> patientsAll = patientRepository.findAll();
		patientsAll.forEach(p -> {
			System.out.println(p.toString());
		});
		
		System.out.println("--------------Pagination------------------");
		Page<Patient> pagePatients=patientRepository.findAll(PageRequest.of(1 , 2));
 		System.out.println("Nombre de pages : "+pagePatients.getTotalPages());
		List<Patient> listePatients = pagePatients.getContent();
		listePatients.forEach(p -> {
			System.out.println(p.toString());
		});
		
	}

}
