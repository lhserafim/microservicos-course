package com.alvorada.tec.hruser.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    // Como o meu modelo não pode ter repetições (o mesmo usuário ter a mesma role + do que 1 vez), ao invés do List
    // estou utilizando o Set, pois ele é uma COLEÇÃO QUE REPRESENTA UM CONJUNTO e não aceita repetições
    @ManyToMany(fetch = FetchType.EAGER) // Coloquei como eager para já retornar usuário e perfis
    // @JoinTable vai criar a tabela no BD para associar User e Role
    @JoinTable(name = "tb_user_role", // nome da tabela
                joinColumns = @JoinColumn(name = "user_id"), // nome da coluna que é FK da tabela TB_USER
                inverseJoinColumns = @JoinColumn(name = "role_id") )// nome da coluna que é FK da tabela TB_ROLE
    private Set<Role> roles = new HashSet<>(); // Uso o HashSet pq o Set é uma interface e p/ instanciar tem que ser a classe

    public User() {
    }

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
