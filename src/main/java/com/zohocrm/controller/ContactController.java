package com.zohocrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zohocrm.entities.Billing;
import com.zohocrm.entities.Contact;
import com.zohocrm.services.BillingService;
import com.zohocrm.services.ContactService;

@Controller
public class ContactController {
	@Autowired
	private ContactService contactService;

	@Autowired
	private BillingService billService;

	@RequestMapping("/listcontacts")
	public String listContact(Model model) {
		List<Contact> contacts = contactService.getContacts();
		model.addAttribute("contacts", contacts);
		return "list_contacts";

	}

	@RequestMapping("/createBill")
	public String createBill(@RequestParam("id") long id, Model model) {
		Contact contact = contactService.getContactById(id);
		model.addAttribute("contact", contact);
		return "generate_bill";
	}

//	@RequestMapping("/saveBill")
//	public String saveBill(Billing bill) {
//		billingService.generateBill(bill);
//		return "list_bills";
//	}

	@RequestMapping("/saveBill")
	public String generateBill(@ModelAttribute Billing billing, Model model) {
		Billing bills = billService.generateBill(billing);
		model.addAttribute("bill", bills);
		return "final_bills";

	}

}
