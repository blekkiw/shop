package ee.blakcat.Services;

import java.util.Set;

public interface BaseService <ENT, ID> {
    ENT findById (ID id);
    ENT save (ENT ent);
    Set<ENT> findAll ();
}
