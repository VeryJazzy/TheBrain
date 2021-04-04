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
        model.addAttribute("dailysList", database.getDailysEntries());
        model.addAttribute("todoList", database.getEntries("todo"));
        model.addAttribute("shoppingList", database.getEntries("shopping"));
        model.addAttribute("quote", database.getRandomEntry("quotes"));
        model.addAttribute("pushups",PushUps.getDay());
        return "index";
    }

    @GetMapping("/add10")
    public String add10(Model model) {
        PushUps.addTen();
        return "redirect:/";
    }

    @GetMapping("/pushupsReset")
    public String pushupsReset(Model model) {
        PushUps.resetCurrent();
        return "redirect:/";
    }

    @GetMapping("/pushupsSetTarget")
    public String pushupsSetTarget(@PathVariable("target") int target, Model model) {
        PushUps.setTarget(target);
        return "redirect:/";
    }

    @PostMapping("/addToDb")
    public String handleForm(@RequestParam(name = "text") String text, @RequestParam(name = "table") String table) {
        Entry entry = new Entry.Builder(ID.createID())
                .withDate(Date.today())
                .withMessage(text)
                .build();
        database.add(entry, table);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String loadDelete(Model model) {
        model.addAttribute("dailysList", database.getEntries("dailys"));
        model.addAttribute("quotesList", database.getEntries("quotes"));
        return "Delete";
    }

    @GetMapping("/deleteEntry/id/{id}/table/{table}")
    public String deleteEntry(@PathVariable("id") String id, @PathVariable("table") String table) {
        database.delete(id, table);
        return "redirect:/";
    }

    @GetMapping("/clearMe/id/{id}")
    public String clearMeForTheDay(@PathVariable("id") String id) {
        database.clearDailyForTheDay(id);
        return "redirect:/";
    }






}
