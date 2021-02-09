package fr.cnam.usal3b.nom.prenom.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Plot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String titre;
	private String description;
//	@OneToMany(mappedBy = "plot")
//	private List<Etape> etape;
	
	@ManyToOne
	@JoinColumn(name = "etape_id", nullable = false)
	private Etape etape;

	
	
	public Etape getEtape() {
		return etape;
	}

	public void setEtape(Etape etape) {
		this.etape = etape;
	}

	public TypePlotEnum getTypePlot() {
		return typePlot;
	}

	public void setTypePlot(TypePlotEnum typePlot) {
		this.typePlot = typePlot;
	}

//	public void setBrique(List<Brique> brique) {
//		this.brique = brique;
//	}

	@Enumerated(EnumType.STRING)
	private TypePlotEnum typePlot;
	

	public Plot() {
	}

	public Plot(String titre, String description) {
		this.titre = titre;
		this.description = description;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
//
//	public List<Brique> getBrique() {
//		return brique;
//	}
//
//	public void setBrique(List<Brique> brique) {
//		this.brique = brique;
//	}
}
