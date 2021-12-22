package TPAPI.tp5.controller;
import TPAPI.tp5.model.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchAddress {
    @GetMapping("/search_address")
    public String address(@RequestParam() Object address, Model model) {
        model.addAttribute("address", address);
        return "search_address";
    }
}

