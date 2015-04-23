package net.volkno.archetype.epidata.argentarii.backend.repository;

import java.util.List;

import net.volkno.archetype.epidata.argentarii.backend.model.tipo.TipoMaterial;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TipoMaterialRepository {

    @Delete("DELETE FROM tipo_materiales WHERE id = #{tipoMaterial.id}")
    void delete(TipoMaterial tipoMaterial);

    @Select("SELECT * FROM tipo_materiales")
    List<TipoMaterial> findAll();

    @Insert("INSERT INTO tipo_materiales (codigo, valor) " + "VALUES (#{codigo}, #{valor})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(TipoMaterial tipoMaterial);

    @Select("SELECT * FROM tipo_materiales WHERE id = #{id}")
    TipoMaterial findOne(@Param("id") String id);

}
