package org.vt.kc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

    @GetMapping(path = "/customers")
    public String customers(Principal principal, Model model) {
        
        Iterable<Customer> customers = getCustomers();
        model.addAttribute("customers", customers);
        model.addAttribute("username", principal.getName());
        return "customers";
    }

    // add customers for demonstration
    public ArrayList<Customer> getCustomers() {

    	return Stream.of(Customer.builder().address("1111 foo blvd").name("Foo Industries").serviceRendered("Foo Services").build(),
    			Customer.builder().address("222 bar street").name("Bar LLP").serviceRendered("Bar Services").build(),
    			Customer.builder().address("33 main avenue").name("Big LLC").serviceRendered("BIG Services").build()
    			).collect(Collectors.toCollection(ArrayList::new));
    }
}
