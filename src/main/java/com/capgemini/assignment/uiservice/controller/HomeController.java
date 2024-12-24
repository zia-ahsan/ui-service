package com.capgemini.assignment.uiservice.controller;

import com.capgemini.assignment.uiservice.model.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {

    @Value("${account.service.url}")
    private String accountServiceUrl;

    private final RestTemplate restTemplate;

    public HomeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/view-accounts")
    public String viewAccounts(Model model) {
        // Fetch accounts with transactions from account-service
        String url = accountServiceUrl + "/accounts";
        Account[] accounts = restTemplate.getForObject(url, Account[].class);

        model.addAttribute("accounts", accounts != null ? Arrays.asList(accounts) : List.of());
        return "view-accounts";
    }


    @PostMapping("/open-account")
    public String openAccount(@RequestParam("initialCredit") BigDecimal initialCredit) {
        // Generate random customerId as a Long
        long customerId = Math.abs(new java.util.Random().nextLong());

        // Send request to account-service
        String url = accountServiceUrl + "/accounts/create?customerId=" + customerId + "&initialCredit=" + initialCredit;
        restTemplate.postForLocation(url, null);

        return "redirect:/";
    }
}
