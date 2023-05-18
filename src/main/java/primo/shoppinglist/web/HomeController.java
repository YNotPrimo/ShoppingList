package primo.shoppinglist.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import primo.shoppinglist.data.entity.enumeration.CategoryEnum;
import primo.shoppinglist.service.ProductService;

@Controller
public class HomeController {

    private final ProductService productService;

    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }
        model.addAttribute("totalPrice", productService.getTotalSum());
        model.addAttribute("food", productService.getProductsByCategory(CategoryEnum.FOOD));
        model.addAttribute("drink", productService.getProductsByCategory(CategoryEnum.DRINK));
        model.addAttribute("household", productService.getProductsByCategory(CategoryEnum.HOUSEHOLD));
        model.addAttribute("other", productService.getProductsByCategory(CategoryEnum.OTHER));
        return "home";
    }
}
