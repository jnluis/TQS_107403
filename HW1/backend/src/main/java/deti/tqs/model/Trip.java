package deti.tqs.model;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Let JPA generate ID
    private int id;

    private int BusNumber;
    private String origin;
    private String destination;
    private String date;
    private String time;
    private double price;
}
