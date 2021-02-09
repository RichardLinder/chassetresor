package fr.cnam.usal3b.nom.prenom.model;

import javax.persistence.Entity;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Etape {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String titre;
	private String description;
	

	@OneToMany(mappedBy = "etape")
	private List<Plot> plot;

	@ManyToOne
	@JoinColumn(name = "scenario_id", nullable = false)
	private Scenario scenario;

	public Scenario getScenario() {
		return scenario;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	public Etape() {
	}

	public Etape(String titre, String description) {
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

	public List<Plot> getPlot() {
		return plot;
	}

	public void setPlot(List<Plot> plot) {
		this.plot = plot;
	}
	
	
	}