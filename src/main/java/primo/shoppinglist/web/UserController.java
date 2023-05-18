package primo.shoppinglist.web;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import primo.shoppinglist.data.binding.UserLoginBindingModel;
import primo.shoppinglist.data.binding.UserRegisterBindingModel;
import primo.shoppinglist.data.service.UserServiceModel;
import primo.shoppinglist.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/register")

    public String register(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid UserRegisterBindingModel model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", model);

            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userRegisterBindingModel"
                    , bindingResult);

            return "redirect:register";
        }

        if (!model.getPassword().equals(model.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("passwordDiff", true);
            redirectAttributes.addFlashAttribute("passwordDiffMessage", "Mismatched passwords");
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", model);

            return "redirect:register";
        }

        userService.register(mapper.map(model, UserServiceModel.class));
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        if (!model.containsAttribute("userLoginBindingModel")) {
            model.addAttribute("userLoginBindingModel", new UserLoginBindingModel());
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(
            @Valid UserLoginBindingModel model
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
            , HttpSession httpSession
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", model);

            redirectAttributes.addFlashAttribute(
                    "errorMessages", userService.exportErrorMessages(bindingResult.getAllErrors())
            );

            return "redirect:login";
        }
        UserServiceModel serviceModel =
                userService.findByUsernameAndPassword(model.getUsername(), model.getPassword());
        if (serviceModel == null) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", model);
            redirectAttributes.addFlashAttribute("notFound", true);

            return "redirect:login";
        }

        httpSession.setAttribute("user", serviceModel.getUsername());
        return "redirect:/";
    }
}
