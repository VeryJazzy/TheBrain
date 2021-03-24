package TheBrain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private Database database;

    @GetMapping("/")
    public String getInfo(Model model) {
        model.addAttribute("day", WeekDay.getWeekDay());
        model.addAttribute("temp", Weather.getTemp("Romford"));
        model.addAttribute("dailysList", database.getEntries("dailytasks"));
        return "index";
    }

    @PostMapping("/addToDb")
    public String handleForm(@RequestParam(name = "text") String text) {

        Entry entry = new Entry.Builder(ID.createID())
                .withDate(Date.today())
                .withMessage(text)
                .build();
        database.add(entry, "dailytasks"); //only works for dailytasks atm
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String getEntries(Model model) {
        model.addAttribute("dailysList", database.getEntries("dailytasks")); //only works for dailytasks atm
        return "delete";
    }

    @GetMapping("/deleteEntry/{id}")
    public String deleteEntry(@PathVariable("id") String id) {
        database.delete(id, "dailytasks");  //only works for dailytasks atm
        return "redirect:/";
    }




}
