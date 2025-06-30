# Answer to the bonus question :

### 1. Is putting all functions inside the same Service class recommended?

**Answer:**  
Putting all functions inside the same Service class is acceptable for small, simple applications but in a real-world or production-level system, this is not recommended. It violates the Single Responsibility Principle from SOLID design principles.

**Better design:**  
Split the logic into dedicated services, for example:

- `UserService`: handles user-related logic (creation, balance, etc.)
- `RoomService`: manages rooms (creation, updating, availability)
- `BookingService`: handles booking logic, availability checks, and validations

This makes the system modular, testable, and easier to scale or refactor.

---

### 2. The `setRoom(...)` function should not impact previous bookings. What’s another way? What is your recommendation?

**Answer:**  
The current design snapshots the `Room` object during booking — meaning each booking stores a copy of the room’s type and price at the time of booking. That’s why changes to a room afterward (via `setRoom(...)`) do not impact previous bookings.

This approach ensures historical integrity, which is crucial in real systems: past bookings should not change if the price or room type changes later.

**Another way:**  
Instead of saving the full `Room` object in `Booking`, we could:

- Save the `roomId` and explicitly store `priceAtBooking` and `roomTypeAtBooking` as separate fields.

Example:

```java
class Booking {
    int roomId;
    String roomTypeAtBooking;
    int priceAtBooking;
    // other fields...
}
