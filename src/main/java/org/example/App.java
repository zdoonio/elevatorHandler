package org.example;

import org.example.handler.QueueHandler;
import org.example.model.ElevatorTrigger;

import java.util.function.BiFunction;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        QueueHandler system = new QueueHandler(4);

        // Symulacja żądań
        system.addTrigger(new ElevatorTrigger(5));
        system.addTrigger(new ElevatorTrigger(2));
        system.addTrigger(new ElevatorTrigger(7));

        // Aktualizacja stanu
        system.updateElevators();
        system.printStatus();

        // Sprawdzenie zmiany kierunku
        System.out.println("Czy jakakolwiek winda zmieniła kierunek? " +
                system.isAnyDirectionChanged());
    }
}
