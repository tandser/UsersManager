package ru.tandser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tandser.domain.User;
import ru.tandser.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model, Integer offset, Integer maxResults) {
        model.addAttribute("user", new User());
        model.addAttribute("list", userService.list(offset, maxResults));
        model.addAttribute("count", userService.count());
        model.addAttribute("offset", offset);
        model.addAttribute("template", new User());

        return "/users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (user.getId() == 0) {
                userService.add(user);
            } else {
                userService.update(user);
            }
        }

        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable("id") int id) {
        userService.remove(id);

        return "redirect:/users";
    }

    @RequestMapping("/update/{id}/offset/{offset}")
    public String update(@PathVariable("id") int id, @PathVariable("offset") Integer offset, Model model){
        model.addAttribute("user", userService.get(id));
        model.addAttribute("list", userService.list(offset, null));
        model.addAttribute("count", userService.count());
        model.addAttribute("template", new User());

        return "/users";
    }

    @RequestMapping(value = "/users/selection", method = RequestMethod.POST)
    public String selection(@ModelAttribute("template") User template, Model model) {
        model.addAttribute("name", template.getName());
        model.addAttribute("selection", userService.selection(template.getName()));

        return "/selection";
    }

    /* Test method */

    @RequestMapping(value = "/generate")
    public String generate() {
        userService.generate();

        return "redirect:/";
    }
}