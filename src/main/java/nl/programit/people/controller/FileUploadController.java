package nl.programit.people.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nl.programit.people.domain.Person;

@Controller
public class FileUploadController {
	
	private List<Person> people = new ArrayList<>();

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload some by posting to this url.";
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public @ResponseBody String list() {
		return this.people.toString();
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void handleFileUpload(@RequestParam("name") String name,
			@RequestParam(required = false, value = "renderPresentationNotes") boolean renderPresentationNotes,
			@RequestParam("lastName") String lastName, HttpServletResponse response) {
		
		Person p = new Person();
		p.setFirstName(name);
		p.setLastName(lastName);
		
		this.people.add(p);
	}
}
