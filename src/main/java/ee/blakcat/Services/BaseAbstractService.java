package ee.blakcat.Services;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class BaseAbstractService <
        REPOSITORY extends CrudRepository <ENT, ID>
        , ENT, ID extends Serializable>
        implements BaseService <ENT, ID> {

protected REPOSITORY repository;


    @Override
    public ENT getByID(ID s) {
        ENT entity = repository.findById(s).get();
        if (Objects.isNull(entity)) {
            throw new RuntimeException("Not found!");
        }
        return entity;
    }

    @Override
    public ENT save(ENT ent) {
        return repository.save(ent);
    }
    @Override
    public Set<ENT> getAll() {
        Set<ENT> all = new HashSet<>();
        repository.findAll().forEach(all::add);
        return all;
    }
}
