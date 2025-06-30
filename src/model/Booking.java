package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    int userId;
    int roomNumber;
    Date checkIn;
    Date checkOut;
    Room roomSnapshot;
    User userSnapshot;

    public Booking(int userId, int roomNumber, Date checkIn, Date checkOut, Room roomSnapshot, User userSnapshot) {
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomSnapshot = roomSnapshot;
        this.userSnapshot = userSnapshot;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public Room getRoomSnapshot() {
        return roomSnapshot;
    }

    public void setRoomSnapshot(Room roomSnapshot) {
        this.roomSnapshot = roomSnapshot;
    }

    public User getUserSnapshot() {
        return userSnapshot;
    }

    public void setUserSnapshot(User userSnapshot) {
        this.userSnapshot = userSnapshot;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "model.Booking - model.User: " + userSnapshot + ", model.Room: " + roomSnapshot +
                ", From: " + sdf.format(checkIn) + " To: " + sdf.format(checkOut);
    }
}
