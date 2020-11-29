package com.souhail.personnes.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.souhail.personnes.entities.Personne;
import com.souhail.personnes.services.PersonneService;

@Controller
public class PersonneController {
@Autowired
PersonneService personneService;
@RequestMapping("/showCreate")
public String showCreate(ModelMap modelMap)
{
modelMap.addAttribute("personne", new Personne());
modelMap.addAttribute("mode", "new");
return "formPersonne";
}
@RequestMapping("/savePersonne")
public String saveProduit(@Valid Personne personne,
						  BindingResult bindingResult)
{
if (bindingResult.hasErrors()) return "formPersonne";

personneService.savePersonne(personne);
return "formPersonne";
}
@RequestMapping("/modifierPersonne")
public String editerProduit(@RequestParam("id") Long id,ModelMap modelMap)
{
Personne p= personneService.getPersonne(id);
modelMap.addAttribute("personne", p);
modelMap.addAttribute("mode", "edit");
return "formPersonne";
}

@RequestMapping("/listePersonnes")
public String listePersonnes(ModelMap modelMap,
@RequestParam (name="page",defaultValue = "0") int page,
@RequestParam (name="size", defaultValue = "2") int size)
{
Page<Personne> press = personneService.getAllPersonnesParPage(page, size);
modelMap.addAttribute("personnes", press);
 modelMap.addAttribute("pages", new int[press.getTotalPages()]);
modelMap.addAttribute("currentPage", page);
return "listePersonnes";
}

@RequestMapping("/supprimerPersonne")
public String supprimerPersonne(@RequestParam("id") Long id,
ModelMap modelMap,
@RequestParam (name="page",defaultValue = "0") int page,
@RequestParam (name="size", defaultValue = "2") int size)
{
personneService.deletePersonneById(id);
Page<Personne> press = personneService.getAllPersonnesParPage(page,
size);
modelMap.addAttribute("personnes", press);
modelMap.addAttribute("pages", new int[press.getTotalPages()]);
modelMap.addAttribute("currentPage", page);
modelMap.addAttribute("size", size);
return "listePersonnes";
}

@RequestMapping("/updatePersonne")
public String updateProduit(@ModelAttribute("personne") Personne personne,
                            @RequestParam("date") String date,
                            ModelMap modelMap) throws ParseException 
{
	//conversion de la date
	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 Date dateEnregistrement = dateformat.parse(String.valueOf(date));
	 personne.setDateEnregistrement(dateEnregistrement);
	 personneService.updatePersonne(personne);
	 List<Personne> press = personneService.getAllPersonnes();
	 modelMap.addAttribute("personnes", press);
	return "listePersonnes";
	}
}