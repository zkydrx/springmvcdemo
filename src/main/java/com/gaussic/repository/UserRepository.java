package com.gaussic.repository;

import com.gaussic.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ZKY on 2017-07-26 21:51.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>
{
    @Modifying      //说明该方法是修改操作
    @Transactional  //说明该方法是事务性操作
    @Query("update UserEntity us set us.nickName=:qNickName,us.firstName=:qFirstName,us.lastName=:aLastName,us.password=:qPassword where us.id=:qId")
    public void updateUser(@Param("qNickName") String nickName,@Param("qFirstName") String firstName,
                           @Param("aLastName") String qLastName,@Param("qPassword") String password,
                           @Param("qId") Integer id);
}
