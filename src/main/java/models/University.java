package models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();
}
