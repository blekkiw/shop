package ee.blakcat.Controllers;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Set;

public interface BaseController<T, ID> {
    String getByID (ID id, Model model);
    String getAll (Model model);
}
