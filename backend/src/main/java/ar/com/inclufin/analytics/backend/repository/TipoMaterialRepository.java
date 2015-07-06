package ar.com.inclufin.analytics.backend.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import ar.com.inclufin.analytics.backend.model.tipo.TipoMaterial;

public interface TipoMaterialRepository {

    @Delete("DELETE FROM tipo_materiales WHERE id = #{id}")
    void delete(TipoMaterial tipoMaterial);

    @Select("SELECT * FROM tipo_materiales")
    List<TipoMaterial> findAll();

    @Insert("INSERT INTO tipo_materiales (codigo, valor) VALUES (#{codigo}, #{valor})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void create(TipoMaterial tipoMaterial);

    @Select("SELECT * FROM tipo_materiales WHERE id = #{id}")
    TipoMaterial findOne(@Param("id") Long id);

    @Insert("UPDATE tipo_materiales SET codigo = #{codigo}, valor = #{valor} WHERE id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void update(TipoMaterial tipoMaterial);

}
