package co.centauri.developer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import co.centauri.random.RandomInteger;

@Service
public class DeveloperRepository {

    private static Map<Integer, Developer> developers;

    private static final Logger LOGGER = Logger.getLogger(DeveloperRepository.class);

    static {
        Developer german = DeveloperBuilder.createGerman();
        Developer pablo = DeveloperBuilder.createPablo();
        Developer adrian = DeveloperBuilder.createAdrian();

        developers = new ConcurrentHashMap<Integer, Developer>();
        developers.put(german.getId(), german);
        developers.put(pablo.getId(), pablo);
        developers.put(adrian.getId(), adrian);

        LOGGER.info("============ " + developers.size() + " created");
    }

    public List<Developer> find() {
        return new ArrayList<>(developers.values());
    }

    public Developer save(Developer developer) {
        if (developer.getId() == null) {
            developer.setId(RandomInteger.getNext());
        }
        developers.put(developer.getId(), developer);
        return developer;
    }

    public Developer find(Integer id) {
        return developers.get(id);
    }

    public void delete(Integer id) {
        developers.remove(id);
    }

}
