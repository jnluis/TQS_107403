package deti.tqs.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ticket")
public class TicketDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Let JPA generate ID
    @GenericGenerator(name = "system-uuid")
    private String id;
    private int tripID; //it's a integer!
    private String price;
    private String FirstName;
    private String LastName;
    private String city;
    private String email;

}
