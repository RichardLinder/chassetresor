package fr.cnam.usal3b.nom.prenom.service;

import java.util.List;

import fr.cnam.usal3b.nom.prenom.model.Brique;
import fr.cnam.usal3b.nom.prenom.model.Plot;

public interface BriqueService<T extends Brique> extends ChasseTresorService<T> {

	List<T> getBriquesPourPlot(Plot plot);
}
