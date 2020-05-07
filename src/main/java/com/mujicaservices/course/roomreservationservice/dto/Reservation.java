package com.mujicaservices.course.roomreservationservice.dto;

import java.util.Date;

public class Reservation {
    private long Id;
    private long roomId;
    private long guestId;
    private Date date;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
