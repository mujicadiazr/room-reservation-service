package com.mujicaservices.course.roomreservationservice.controller;

import com.mujicaservices.course.roomreservationservice.client.GuestClient;
import com.mujicaservices.course.roomreservationservice.client.ReservationsClient;
import com.mujicaservices.course.roomreservationservice.client.RoomClient;
import com.mujicaservices.course.roomreservationservice.dto.Guest;
import com.mujicaservices.course.roomreservationservice.dto.Reservation;
import com.mujicaservices.course.roomreservationservice.dto.Room;
import com.mujicaservices.course.roomreservationservice.dto.RoomReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.jws.Oneway;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/room-reservations")
public class RoomReservationController {
    private final RoomClient roomClient;
    private final GuestClient guestClient;
    private final ReservationsClient reservationsClient;

    @Autowired
    public RoomReservationController(RoomClient roomClient, GuestClient guestClient, ReservationsClient reservationsClient) {
        this.roomClient = roomClient;
        this.guestClient = guestClient;
        this.reservationsClient = reservationsClient;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<RoomReservation> getRoomReservations(){
        List<Room> rooms = roomClient.getAllRooms();
        List<RoomReservation> roomReservations = new ArrayList<>();

        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());;
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservations.add(roomReservation);
        });

        return roomReservations;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{date}", produces = "application/json")
    public Iterable<RoomReservation> getReservationsByDate(@PathVariable(name = "date")String stringDate) throws ParseException {
        Date date = toDate(stringDate);
        Iterable<Reservation> reservations = reservationsClient.getByDate(new java.sql.Date(date.getTime()));
        List<RoomReservation> roomReservations = new ArrayList<>();

        reservations.forEach(reservation -> {
            Room room = roomClient.getRoomById(reservation.getRoomId());
            Guest guest = guestClient.getById(reservation.getGuestId());

            RoomReservation roomReservation = new RoomReservation();

            roomReservation.setRoomId(room.getId());;
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getRoomNumber());

            roomReservation.setDate(date);

            roomReservation.setGuestId(guest.getId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());

            roomReservations.add(roomReservation);
        });

        return roomReservations;
    }

    private Date toDate(String stringDate) throws ParseException {
        final Date from;
        try {
            from = new SimpleDateFormat("YYYY-MM-DD")
                    .parse(stringDate);
        } catch (ParseException e) {
            throw new ParseException("Date is in a wrong format", 0);
        }
        return from;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParseException.class)
    private String return404(ParseException ex){
        return ex.getMessage();
    }
}
