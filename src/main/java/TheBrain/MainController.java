package TheBrain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    Database database;

    @GetMapping("/")
    public String getDay(Model model) {
        model.addAttribute("day", WeekDay.getWeekDay());
        model.addAttribute("temp", Weather.getTemp("Romford"));
        model.addAttribute("dailysList", database.getEntries());
        return "index";
    }


}
