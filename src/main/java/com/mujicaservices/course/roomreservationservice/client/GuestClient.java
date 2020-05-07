package com.mujicaservices.course.roomreservationservice.client;

import com.mujicaservices.course.roomreservationservice.dto.Guest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("guestservices")
public interface GuestClient {
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Iterable<Guest> getAllGuests();

    @RequestMapping(method = RequestMethod.GET, path = "/{guestId}", produces = "application/json")
    public Guest getById(@PathVariable(name = "guestId") long guestId);
}
