package ma.emsi.tp_jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.tp_jpa.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	public List<Patient> findByNomContains(String name);
	public List<Patient> findByMalade(boolean b);
	public List<Patient> findByNomContainsAndMalade(String nom,boolean b);
	
}
