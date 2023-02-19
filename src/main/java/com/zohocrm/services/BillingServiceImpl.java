package com.zohocrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zohocrm.entities.Billing;
import com.zohocrm.repository.BillingRepository;
@Service
public class BillingServiceImpl implements BillingService {
@Autowired
BillingRepository billingRepo;

@Override
public Billing generateBill(Billing bill) {
		return billingRepo.save(bill);
}
	
	
}