package ee.blakcat.Controllers;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Set;

public interface BaseController<T, ID> {
    String findById (ID id, Model model, HttpSession httpSession);
    String findAll (Model model);
}
