package fr.cnam.usal3b.nom.prenom.model;

import javax.persistence.Entity;

@Entity
public class BriqueTexte extends Brique {

	private String contenuHtml;

	public String getContenuHtml() {
		return contenuHtml;
	}

	public void setContenuHtml(String contenuHtml) {
		this.contenuHtml = contenuHtml;
	}

}
