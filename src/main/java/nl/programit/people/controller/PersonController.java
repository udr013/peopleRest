package nl.programit.people.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import nl.programit.people.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.programit.people.domain.Person;

@Controller
public class PersonController {

	@Autowired
	PersonService personService;

	private List<Person> people = new ArrayList<>();

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody String list() {
		return personService.findAllPersons().toString();
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload some by posting to this url.";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void handleFileUpload(@RequestParam("name") String name,
			@RequestParam(required = false, value = "renderPresentationNotes") boolean renderPresentationNotes,
			@RequestParam("lastName") String lastName, HttpServletResponse response) {

		Person p = new Person();
		p.setFirstName(name);
		p.setLastName(lastName);

        personService.save(p);

		this.people.add(p);
	}
}
