package TheBrain;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String getDay(Model model) {
        model.addAttribute("day", WeekDay.getWeekDay());
        model.addAttribute("temp", Weather.getTemp("Romford"));
        return "index";
    }


}
