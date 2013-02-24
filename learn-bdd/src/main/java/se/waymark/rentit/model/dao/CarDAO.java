
package se.waymark.rentit.model.dao;

import se.waymark.rentit.model.entiy.Car;

public interface CarDAO {

    public void add(Car car);

    Car findAvailableCar();

    int getNumberOfAvailableCars();
}