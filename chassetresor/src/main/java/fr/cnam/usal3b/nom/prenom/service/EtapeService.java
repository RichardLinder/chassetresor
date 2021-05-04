package fr.cnam.usal3b.nom.prenom.service;

import java.util.List;

import fr.cnam.usal3b.nom.prenom.model.Etape;
import fr.cnam.usal3b.nom.prenom.model.Scenario;

public interface EtapeService extends ChasseTresorService<Etape> {

	List<Etape> getEtapesPourScenario(Scenario scenario);

}
