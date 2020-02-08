package ee.blakcat.Controllers;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Set;

public interface BaseController<T, ID> {
    String findById (ID id, Model model);
    String findAll (Model model);
}
