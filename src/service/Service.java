package service;

import model.Booking;
import model.Room;
import model.RoomType;
import model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Service {
    List<Room> rooms = new ArrayList<>();
    List<User> users = new ArrayList<>();
    List<Booking> bookings = new ArrayList<>();

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setType(roomType);
                room.setPricePerNight(roomPricePerNight);
                return;
            }
        }
        rooms.add(0, new Room(roomNumber, roomType, roomPricePerNight));
    }

    public void setUser(int userId, int balance) {
        for (User user : users) {
            if (user.getUserId() == userId) return;
        }
        users.add(0, new User(userId, balance));
    }

    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        if (checkOut.before(checkIn)) {
            System.out.println("Invalid booking: check-out is before check-in.");
            return;
        }

        Room room = null;
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) {
                room = r;
                break;
            }
        }
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        User user = null;
        for (User u : users) {
            if (u.getUserId() == userId) {
                user = u;
                break;
            }
        }
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        for (Booking b : bookings) {
            if (b.getRoomNumber() == roomNumber && !(
                    checkOut.compareTo(b.getCheckIn()) <= 0 || checkIn.compareTo(b.getCheckOut()) >= 0)) {
                System.out.println("Room not available during the selected dates.");
                return;
            }
        }

        long nights = (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24);
        int totalCost = (int) nights * room.getPricePerNight();
        if (user.getBalance() < totalCost) {
            System.out.println("User does not have enough balance.");
            return;
        }

        user.setBalance(user.getBalance() - totalCost);
        bookings.add(0, new Booking(userId, roomNumber, checkIn, checkOut,
                new Room(room.getRoomNumber(), room.getType(), room.getPricePerNight()),
                new User(user.getUserId(), user.getBalance() + totalCost))); // snapshot
    }

    public void printAll() {
        System.out.println("--- All Rooms ---");
        for (Room r : rooms) {
            System.out.println(r);
        }
        System.out.println("--- All Bookings ---");
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    public void printAllUsers() {
        System.out.println("--- All Users ---");
        for (User u : users) {
            System.out.println(u);
        }
    }
}
