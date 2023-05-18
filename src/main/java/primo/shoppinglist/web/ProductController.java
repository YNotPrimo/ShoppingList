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
import primo.shoppinglist.data.binding.ProductAddBindingModel;
import primo.shoppinglist.data.service.ProductServiceModel;
import primo.shoppinglist.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    private final ModelMapper mapper;
    @Autowired
    public ProductController(ProductService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    public String add(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/users/login";
        }
        if (!model.containsAttribute("model")) {
            model.addAttribute("model", new ProductAddBindingModel());
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addPost(@Valid ProductAddBindingModel model
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("model", model);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.model"
                    , bindingResult);

            return "redirect:add";
        }

        service.addProduct(mapper.map(model, ProductServiceModel.class));

        return "redirect:/";
    }
}
