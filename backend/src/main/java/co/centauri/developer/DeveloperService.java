package co.centauri.developer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.centauri.exception.NotFoundException;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository repository;

    public List<Developer> find() {
        List<Developer> developers = repository.find();
        return developers;
    }

    public Developer find(Integer id) {
        Developer developer = repository.find(id);
        if (developer == null) {
            throw new NotFoundException();
        }
        return developer;
    }

    public Developer save(Developer newDeveloper) {
        Developer developerSaved = repository.save(newDeveloper);
        return developerSaved;
    }

    public Developer update(Developer developer) {
        return save(developer);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }

}
