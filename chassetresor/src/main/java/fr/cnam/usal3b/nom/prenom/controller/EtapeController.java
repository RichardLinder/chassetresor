package fr.cnam.usal3b.nom.prenom.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fr.cnam.usal3b.nom.prenom.form.EtapeForm;
import fr.cnam.usal3b.nom.prenom.model.Etape;
import fr.cnam.usal3b.nom.prenom.model.Scenario;
import fr.cnam.usal3b.nom.prenom.service.EtapeService;
import fr.cnam.usal3b.nom.prenom.service.ScenarioService;


@Controller
public class EtapeController {

	@Autowired
	private EtapeService EtapeService;
	@Autowired
	private ScenarioService ScenarioService;
	// Injectez (inject) via application.properties.
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = { "/etapeList" }, method = RequestMethod.GET)
	public String etapeList(Model model) {

		Iterable<Etape> etapesDb = EtapeService.getTout();
		model.addAttribute("etapes", etapesDb);

		return "etapeList";
	}

	@RequestMapping(value = { "/addEtape" }, method = RequestMethod.GET)
	public String showAddEtapePage(Model model) {

		EtapeForm etapeForm = new EtapeForm();
		model.addAttribute("etapeForm", etapeForm);
		Iterable<Scenario> listeScenarios = ScenarioService.getTout();
		model.addAttribute("scenarios", listeScenarios);

		return "addEtape";
	}

	@RequestMapping(value = { "/addEtape" }, method = RequestMethod.POST)
	public String saveEtape(Model model, @ModelAttribute("etapeForm") EtapeForm etapeForm) {

		String titre = etapeForm.getTitre();
		String description = etapeForm.getDescription();
		// Optionnal est une aide pour traiter la réponse à la requête. Si le scénario
		// qu'on cherche existe, alors isPresent sera à true. Sinon à false. Evite les
		// problème de NullPointerException. 
	}
		@RequestMapping(value = { "/Scenario" }, method= RequestMethod.GET)
		public @ResponseBody  ResponseEntity<Scenario> scenarioJson(@PathVariable("id") Integer id) {
		Optional<Scenario> scenario = ScenarioService.getUnObjet(etapeForm.getScenarioId());

		if (scenario.isPresent() && titre != null && titre.length() > 0 // TODO si vous vous ennuyez : chercher @Valid
				&& description != null && description.length() > 0) {
			Etape newEtape = new Etape(titre, description);
			// attention au .get() pour récupérer l'objet Scenario avec l'id remplie dans le
			// formulaire
			newEtape.setScenario(scenario.get());
			EtapeService.sauvegarder(newEtape);

			return "redirect:/etapeList";
		}
		

		model.addAttribute("errorMessage", errorMessage);
		return "addEtape";
	}

}
