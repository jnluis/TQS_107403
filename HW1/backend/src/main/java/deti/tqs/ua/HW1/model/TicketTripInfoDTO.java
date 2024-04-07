package deti.tqs.ua.HW1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketTripInfoDTO {
    private String id;
    private String price;
    private int tripID;
    private int busID;
    private String busNumber;
    private String origin;
    private String destination;
    private String date;
    private String time;
    private String FirstName;
    private String LastName;
    private String email;
}
