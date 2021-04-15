package springbootapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springbootapplication.model.User;
import springbootapplication.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String printAllUsers(Model model) {
        model.addAttribute(userService.getAllUsers());
        model.addAttribute("currentUser", userService.findByEmail
                (SecurityContextHolder.getContext().getAuthentication().getName()));
        return "admin";
    }

    @GetMapping(value = "/addUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "userInfo";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user, @ModelAttribute("Active") String isActive,
                           @ModelAttribute("isUser") String isUser, @ModelAttribute("isAdmin") String isAdmin) {
        userService.setActiveAndRoles(user, isActive, isAdmin, isUser);
        userService.encodePassword(user);
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "/user-update";
    }

    @PutMapping("/user-update")
    public String updateUser(User user) {
        userService.update(user);
        return "redirect:/admin";
    }
}
