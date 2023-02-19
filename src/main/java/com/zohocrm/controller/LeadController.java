package com.zohocrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.sym.Name;
import com.zohocrm.entities.Contact;
import com.zohocrm.entities.Lead;
import com.zohocrm.services.ContactService;
import com.zohocrm.services.LeadService;

@Controller
public class LeadController {
	
	@Autowired
	private LeadService leadservice;
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(value="/createLead",method = RequestMethod.GET)
	public String viewCreateLeadForm() {
		return "create_lead";
	}
	@RequestMapping(value="/saveLead",method =RequestMethod.POST)
	public String saveLead(@ModelAttribute Lead lead,Model model) {
		
		leadservice.saveOneLead(lead);
		
		model.addAttribute("lead", lead);
		return "lead_info"; 
	}
	@RequestMapping("/convertLead")
	public String convertLead(@RequestParam("id")long id,Model model) {
		Lead lead = leadservice.getLeadById(id);
	Contact contact=new Contact();
	contact.setFirstName(lead.getFirstName());
	contact.setLastName(lead.getLastName());
	contact.setEmail(lead.getEmail());
	contact.setMobile(lead.getMobile());
	contact.setSource(lead.getSource());
	
	contactService.saveContact(contact);
	
	leadservice.deleteLeadById(id);
	
	List<Contact> contacts = contactService.getContacts();
	model.addAttribute("contacts",contacts);
	return "list_contacts";
	}
	@RequestMapping("/listleads")
	public String listLeads(Model model) {
		List<Lead> leads = leadservice.getAllLeads();
		model.addAttribute("leads",leads);
		return "list_leads";
	}
	@RequestMapping("leadInfo")
	public String leadInfo(@RequestParam("id") long id,Model model) {
		Lead lead = leadservice.getLeadById(id);
		model.addAttribute("lead", lead);
		return "lead_info";
	
	}
}
