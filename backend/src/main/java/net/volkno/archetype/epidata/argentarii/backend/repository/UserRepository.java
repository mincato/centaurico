package net.volkno.archetype.epidata.argentarii.backend.repository;

import net.volkno.archetype.epidata.argentarii.backend.model.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserRepository {

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT * FROM users WHERE userId = #{userId}")
    User findOne(@Param("userId") String userId);

    @Insert("INSERT INTO users (username, firstName, middleName, lastName, email) "
            + "VALUES (#{username}, #{firstName}, #{middleName}, #{lastName}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    User save(User user);

}
