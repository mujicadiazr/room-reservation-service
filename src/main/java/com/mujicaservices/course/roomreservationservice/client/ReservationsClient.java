package com.mujicaservices.course.roomreservationservice.client;

import com.mujicaservices.course.roomreservationservice.dto.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.NoSuchElementException;

@FeignClient("reservationservices")
public interface ReservationsClient {
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Iterable<Reservation> getAll();

    @RequestMapping(method = RequestMethod.GET, path = "/{reservationId}", produces = "application/json")
    public Reservation getById(@PathVariable(name = "reservationId") long reservationId);

    @RequestMapping(method = RequestMethod.GET, path = "/{date}", produces = "application/json")
    public Iterable<Reservation> getByDate(@PathVariable(name = "date") Date date);
}
