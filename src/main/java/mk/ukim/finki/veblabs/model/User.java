package mk.ukim.finki.veblabs.model;

import lombok.Data;
import mk.ukim.finki.veblabs.UserFullnameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
//    private String name;
//    private String surname;
    @Convert(converter = UserFullnameConverter.class)
    private UserFullname fullName;
    private String password;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;

    public User(String username, String name, String surname, String password,LocalDate dateOfBirth) {
        this.fullName = new UserFullname();
        this.username = username;
        this.fullName.setName(name);
        this.fullName.setSurname(surname);
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.carts = new ArrayList<>();
    }

    public User() {
    }
}
