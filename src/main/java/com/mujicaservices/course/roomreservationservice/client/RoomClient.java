package com.mujicaservices.course.roomreservationservice.client;

import com.mujicaservices.course.roomreservationservice.dto.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("roomservices")
public interface RoomClient {
    @RequestMapping(method = RequestMethod.GET, path = "/rooms", produces = "application/json")
    List<Room> getAllRooms();

    @RequestMapping(method = RequestMethod.GET, path = "/rooms/{id}", produces = "application/json")
    Room getRoomById(@PathVariable(name = "id") long id);
}
