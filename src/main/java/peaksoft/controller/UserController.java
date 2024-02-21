package peaksoft.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.User;
import peaksoft.service.FollowerService;
import peaksoft.service.PostService;
import peaksoft.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FollowerService followerService;
    private final PostService postService;


    @GetMapping("/home")
    public String mainPage(Model model, HttpSession session, @RequestParam(name = "search") String query) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        List<User> userList;
        if (query != null && !query.isEmpty()) {
            userList = followerService.search(query);
        } else {
            userList = followerService.search("");
        }
        userList = userList.stream().filter(u -> !u.getId().equals(user.getId())).collect(Collectors.toList());
        model.addAttribute("userList", userList);
        model.addAttribute("postList", postService.getAllPostsOfSubsAndOwnByUserId(user.getId()));
        return "main-page";
    }

    @GetMapping
    public String signInPage() {
        return "sign-in";
    }

    @PostMapping
    public String signIn(@RequestParam("emailOrUserName") String emailOrUserName, @RequestParam("password") String password, Model model, HttpSession session) {
        User user = userService.findByEmailOrUserName(emailOrUserName);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return "redirect:/home?search=";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "sign-in";
        }
    }


    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("newUser", new User());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(@Validated @ModelAttribute("newUser") User user, BindingResult bindingResult, HttpSession session) {
        if (userService.isUsernameOrEmailUnique(user.getUsername(), user.getEmail()) && user.getPhoneNumber().contains("+996")) {
            userService.save(user);
            session.setAttribute("user", user);
            return "redirect:/home?search=";
        } else {
            if (!user.getPhoneNumber().contains("+996")) {
                bindingResult.rejectValue("phoneNumber", "error.user", "Phone number don't contains +996");
            } else {
                bindingResult.rejectValue("username", "error.user", "Username or email already exists");
            }
            return "sign-up";
        }
    }
}
