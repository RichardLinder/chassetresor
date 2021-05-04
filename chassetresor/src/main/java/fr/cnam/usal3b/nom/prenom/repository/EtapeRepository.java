package fr.cnam.usal3b.nom.prenom.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.cnam.usal3b.nom.prenom.model.Etape;
import fr.cnam.usal3b.nom.prenom.model.Scenario;

public interface EtapeRepository extends CrudRepository<Etape, Integer> {
	List<Etape> findByScenario(Scenario scenario);
}