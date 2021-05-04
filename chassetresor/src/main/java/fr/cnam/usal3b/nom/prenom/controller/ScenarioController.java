package fr.cnam.usal3b.nom.prenom.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.cnam.usal3b.nom.prenom.form.ScenarioForm;
import fr.cnam.usal3b.nom.prenom.model.Scenario;
import fr.cnam.usal3b.nom.prenom.repository.ScenarioRepository;
import fr.cnam.usal3b.nom.prenom.service.EtapeService;
import fr.cnam.usal3b.nom.prenom.service.ScenarioService;

@Controller
public class ScenarioController {

	@Autowired
	private ScenarioService scenarioService;
	@Autowired
	private EtapeService etapeService;
	//@Autowired
	//private ScenarioRepository scenarioRepository;

	// Injectez (inject) via application.properties.
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}

	@RequestMapping(value = { "/scenarioList" }, method = RequestMethod.GET)
	public String scenarioList(Model model) {

		Iterable<Scenario> scenariosDb = ScenarioService.getTout();
		model.addAttribute("scenarios", scenariosDb);

		return "scenarioList";
	}

	@RequestMapping(value = { "/addScenario" }, method = RequestMethod.GET)
	public String showAddScenarioPage(Model model) {

		ScenarioForm scenarioForm = new ScenarioForm();
		model.addAttribute("scenarioForm", scenarioForm);
		model.addAttribute("typeAction", "Créer");

		return "addScenario";
	}

	@RequestMapping(value = { "/addScenario" }, method = RequestMethod.POST)
	public String saveScenario(Model model, @ModelAttribute("scenarioForm") ScenarioForm scenarioForm) {

		String titre = scenarioForm.getTitre();
		String description = scenarioForm.getDescription();

		if (titre != null && titre.length() > 0 //
				&& description != null && description.length() > 0) {
			Scenario newScenario = new Scenario(titre, description);
			if (scenarioForm.getId() != null)
				newScenario.setId(scenarioForm.getId());
			scenarioService.save(newScenario);

			return "redirect:/scenarioList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "addScenario";
	}

	@RequestMapping(value = { "/updatescenario/{id}" }, method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Optional<Scenario> scenario = scenarioService.getTout();
		ScenarioForm scenarioForm = new ScenarioForm();
		if (scenario.isPresent()) {
			scenarioForm.setId(scenario.get().getId());
			scenarioForm.setTitre(scenario.get().getTitre());
			scenarioForm.setDescription(scenario.get().getDescription());
			model.addAttribute("etapes", etapeService.getEtapesPourScenario(scenario.get()));
			model.addAttribute("scenarioForm", scenarioForm);
			model.addAttribute("typeAction", "Modifier");
			return "addScenario";
		}
		return showAddScenarioPage(model);
	}

}
