package com.fitnessclub.client_managment_system.Controller;

import com.fitnessclub.client_managment_system.Entity.Client;
import com.fitnessclub.client_managment_system.Service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//TEST UI
@Controller
@RequestMapping("/ui")
public class ClientUiController {

    private final ClientService service;

    public ClientUiController(ClientService service) {
        this.service = service;
    }

    // Главная страница со всеми клиентами
    @GetMapping("/clients")
    public String listClients(Model model) {
        model.addAttribute("clients", service.getAllClient()); //
        return "clients";
    }

    // Форма создания нового клиента
    @GetMapping("/clients/new")
    public String createForm(Model model) {
        return "create-client";
    }

    // Обработка создания
    @PostMapping("/clients")
    public String saveClient(@ModelAttribute Client client) {
        service.createClient(client); //
        return "redirect:/ui/clients";
    }

    // Кнопка подтверждения оплаты
    @PostMapping("/clients/{id}/approve")
    public String approve(@PathVariable Long id) {
        service.approvePaymentById(id); //
        return "redirect:/ui/clients";
    }

    // Кнопка отмены
    @PostMapping("/clients/{id}/cancel")
    public String cancel(@PathVariable Long id) {
        service.cancelClientPayment(id); //
        return "redirect:/ui/clients";
    }
}