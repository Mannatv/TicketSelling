package ca.sheridancollege.assignment3mannatverma.controller;

import ca.sheridancollege.assignment3mannatverma.Ticket.Ticket;
import ca.sheridancollege.assignment3mannatverma.TicketRepository.TicketRepository;
import ca.sheridancollege.assignment3mannatverma.User.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class SportController {
    private TicketRepository ticketRepo;

    @GetMapping("/")
    public String goHome() {
        return "home";
    }

    @GetMapping("/view")
    public String viewTicket(Model model) {

        model.addAttribute("tickets", ticketRepo.getickets());
        return "view";
    }

    @GetMapping("/addTicket")
    public String goAdd(Model model)                                       // This is to navigate to the add page
    {
        model.addAttribute("ticket", new Ticket());
        model.addAttribute("guestUsernames", ticketRepo.getGuestUsernames());
        return "addTicket.html";
    }

    @PostMapping("/add")
    private String processAdd(@ModelAttribute Ticket ticket, Model model) {
        ticketRepo.addTicket(ticket);
        return "redirect:/view";         //Sends a 302 status code
    }

    @GetMapping("/edit/{id}")
    public String goEdit(@PathVariable int id, Model model) {
        Ticket ticket = ticketRepo.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "editTicket.html";
    }

    @PostMapping("/editTicket")
    private String processEdit(@ModelAttribute Ticket ticket, Model model) {
        ticketRepo.editTicket(ticket);
        return "redirect:/view";
    }

    @GetMapping("/delete/{id}")
    public String goDelete(@PathVariable int id, Model model) {
        Ticket ticket = ticketRepo.getTicketById(id);
        model.addAttribute("ticket", ticket);
        ticketRepo.deleteTickets(ticket);
        return "redirect:/view";
    }

    @GetMapping("/accessDenied")
    public String goAccess() {
        return "accessDenied.html";
    }

    @GetMapping("/login")
    public String goLogin() {
        return "login.html";
    }

    @PostMapping("/login")
    public String goLogi() {
        return "redirect:/view";
    }

    @GetMapping("/registration")
    public String goRegister() {
        return "registration";
    }
    @PostMapping("/registration")
    public String processRegister(@RequestParam String username, @RequestParam String password){
        ticketRepo.addNewUser(username,password);
        User user = ticketRepo.getUserByUsername(username);
        ticketRepo.addNewRole(user.getUserId(),1);
        ticketRepo.addNewRole(user.getUserId(),2);
        return "redirect:/";
    }

}
