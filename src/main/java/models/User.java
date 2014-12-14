package models;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table
public class User extends AbstractModel {

    @Column
    public String username;

    @Column
    public String password;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column
    public String email;

    @Column(name = "avatar_url")
    public String avatarUrl;

    @Column(name = "is_admin")
    public boolean isAdmin;

}
