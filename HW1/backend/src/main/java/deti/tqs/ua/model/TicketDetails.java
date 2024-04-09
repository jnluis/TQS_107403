package deti.tqs.ua.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("LastName")
    private String lastName;
    private String city;
    private String email;

}