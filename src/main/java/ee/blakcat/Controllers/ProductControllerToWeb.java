package ee.blakcat.Controllers;

import ee.blakcat.Models.Category;
import ee.blakcat.Models.Product;
import ee.blakcat.Models.User;
import ee.blakcat.Models.UserRole;
import ee.blakcat.Services.ProductService;
import ee.blakcat.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Set;

@Controller
public class ProductControllerToWeb implements ProductController {

public ProductService productService;
public UserService userService;
@Autowired
    public ProductControllerToWeb(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }




    @GetMapping ("/product/find")
    @Override
    public String findById(@RequestParam ("id") String s, Model model) {
        Product product = productService.findById(s);
        model.addAttribute("product", product);
        model.addAttribute("backlink", "/");
        return "product/one";
    }

    @Override
    public String findAll(Model model) {
        return null;
    }

    @GetMapping ("/product/all")
    public String findAll(Model model, HttpSession httpSession) {

    httpSession.setAttribute("sessionid","12345");
    final Set<Product> products = productService.findAll();
    model.addAttribute("products", products);
        return "product/all";
    }

    @PostMapping ("/product/save")
    public String save (@RequestParam ("name") String name, @RequestParam ("price") double price,
                        @RequestParam ("category")Category category, @RequestParam ("description") String description,
                        Model model, @RequestParam ("count") int count) {
    Product product = new Product(name, price, category, description,count);
    productService.save(product);
    return findById(product.getId(), model);
    }

    @GetMapping ("/product/add")
    public String add (Model model, HttpSession httpSession) {
        User user = userService.findBySession(httpSession.getId());
        if (user==null||user.getUserRole()!= UserRole.ADMINISTRATOR) {
            model.addAttribute("error","You dont have permission for this");
            return "/error";
        }
        model.addAttribute("categories", Category.values());
        System.out.println(Category.values()[0].name);
    return "/product/add";
    }


}
