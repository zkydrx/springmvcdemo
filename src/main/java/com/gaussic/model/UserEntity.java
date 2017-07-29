package com.gaussic.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by ZKY on 2017-07-28 00:29.
 */
@Entity
@Table(name = "user", schema = "test", catalog = "")
public class UserEntity
{
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String nickName;
    private Collection<BlogEntity> blogsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 50)
    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 60)
    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 70)
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Basic
    @Column(name = "nick_name", nullable = true, length = 70)
    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id)
            return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null)
            return false;
        if (password != null ? !password.equals(that.password) : that.password != null)
            return false;
        if (nickName != null ? !nickName.equals(that.nickName) : that.nickName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<BlogEntity> getBlogsById()
    {
        return blogsById;
    }

    public void setBlogsById(Collection<BlogEntity> blogsById)
    {
        this.blogsById = blogsById;
    }
}
