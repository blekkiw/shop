package ee.blakcat.Controllers;

import ee.blakcat.Models.Category;
import ee.blakcat.Models.Product;
import ee.blakcat.Services.ProductService;
import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class ProductControllerToWeb implements ProductController {

public ProductService productService;
@Autowired
    public ProductControllerToWeb(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping ("/product/find")
    @Override
    public String findById(@RequestParam ("id") String s, Model model) {
        Product product = productService.findById(s);
        model.addAttribute("product", product);
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
    public String save (@RequestParam ("name") String name, @RequestParam ("price") double price, @RequestParam ("category")Category category, @RequestParam ("description") String description, Model model) {
    Product product = new Product(name, price, category, description);
    productService.save(product);
    return findById(product.getId(), model);
    }


}
