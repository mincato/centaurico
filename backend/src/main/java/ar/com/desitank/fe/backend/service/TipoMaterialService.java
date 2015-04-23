package ar.com.desitank.fe.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.desitank.fe.backend.model.tipo.TipoMaterial;
import ar.com.desitank.fe.backend.repository.TipoMaterialRepository;

@Service
public class TipoMaterialService {

    @Autowired
    private TipoMaterialRepository tipoMaterialRepository;

    @Transactional
    public void createTipoMaterial(TipoMaterial tipoMaterial) {
        tipoMaterialRepository.save(tipoMaterial);
    }

    public List<TipoMaterial> getAll() {
        return tipoMaterialRepository.findAll();
    }

    @Transactional
    public void update(TipoMaterial tipoMaterial) {
        tipoMaterialRepository.save(tipoMaterial);
    }

    public TipoMaterial find(String id) {
        return tipoMaterialRepository.findOne(id);
    }

    public void delete(TipoMaterial tipoMaterial) {
        tipoMaterialRepository.delete(tipoMaterial);
    }

}
