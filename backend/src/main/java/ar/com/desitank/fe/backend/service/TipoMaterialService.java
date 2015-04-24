package ar.com.desitank.fe.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.desitank.fe.backend.model.tipo.TipoMaterial;
import ar.com.desitank.fe.backend.repository.TipoMaterialRepository;
import ar.com.desitank.fe.backend.service.exception.NotFoundException;

@Service
public class TipoMaterialService {

    @Autowired
    private TipoMaterialRepository tipoMaterialRepository;

    @Transactional
    public TipoMaterial createTipoMaterial(TipoMaterial tipoMaterial) {
        tipoMaterialRepository.create(tipoMaterial);
        return find(tipoMaterial.getId());
    }

    public List<TipoMaterial> getAll() {
        return tipoMaterialRepository.findAll();
    }

    @Transactional
    public TipoMaterial update(TipoMaterial tipoMaterial) {
        tipoMaterialRepository.update(tipoMaterial);
        return find(tipoMaterial.getId());
    }

    public TipoMaterial find(Long id) {
        TipoMaterial tipoMaterial = tipoMaterialRepository.findOne(id);
        if (tipoMaterial == null) {
            throw new NotFoundException();
        }
        return tipoMaterial;
    }

    public void delete(Long id) {
        TipoMaterial tipoMaterial = find(id);
        tipoMaterialRepository.delete(tipoMaterial);
    }

}
