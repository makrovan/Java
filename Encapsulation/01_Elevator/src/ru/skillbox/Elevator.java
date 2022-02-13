package ru.skillbox;

public class Elevator {
    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor){
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor(){ return currentFloor; }

    public void moveDown(){
        currentFloor = (currentFloor > minFloor) ? currentFloor - 1 : currentFloor;
    }

    public void moveUp(){
        currentFloor = (currentFloor < maxFloor) ? currentFloor + 1 : currentFloor;
    }

    public boolean move(int floor) {
        if ((floor > maxFloor)||(floor < minFloor)) {
            System.out.println("Номер этажа введен не верно.");
            return false;
        }

        System.out.println("Этаж № " + currentFloor);

        while (floor > currentFloor) {
            moveUp();
            System.out.println("Этаж № " + currentFloor);
        }

        while (floor < currentFloor) {
            moveDown();
            System.out.println("Этаж № " + currentFloor);
        }
        return true;
    }
}
