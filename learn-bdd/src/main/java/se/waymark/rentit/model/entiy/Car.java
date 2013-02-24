
package se.waymark.rentit.model.entiy;

public class Car {
    private boolean rented;

    public void rent() {
        rented = true;
    }

    public boolean isRented() {
        return rented;
    }
}