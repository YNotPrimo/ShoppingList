package primo.shoppinglist.web;

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
import primo.shoppinglist.data.binding.UserRegisterBindingModel;
import primo.shoppinglist.data.service.UserServiceModel;
import primo.shoppinglist.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private ModelMapper mapper;

    @Autowired
    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid UserRegisterBindingModel model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
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
}
