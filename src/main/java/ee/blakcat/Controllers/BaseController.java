package ee.blakcat.Controllers;

import java.util.ArrayList;
import java.util.Set;

public interface BaseController<T, ID> {
    T getByID (ID id);
    T save (T ent);
    Set<T> getAll ();
}
