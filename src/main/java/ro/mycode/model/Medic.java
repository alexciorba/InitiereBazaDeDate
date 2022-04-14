package ro.mycode.model;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Medic {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
}
