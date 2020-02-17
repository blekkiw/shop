package ee.blakcat;

import ee.blakcat.Models.Category;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice("ee.blakcat.Controllers")
public class Advice {


        @ModelAttribute("categories")
        public Category[] populateCustomers() {
            return Category.values();
        }
    }

