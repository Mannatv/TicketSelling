package ca.sheridancollege.assignment3mannatverma.Ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {
    private int id;
    private String name;
    private double price;
    private String seatNumber;
    private LocalDate date;
    private LocalTime time;
}
