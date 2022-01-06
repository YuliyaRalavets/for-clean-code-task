import planeModels.PlaneProducer;
import planeTypes.MilitaryType;
import planeModels.planes.MilitaryPlane;
import planeModels.planes.PassengerPlane;
import planeModels.Plane;

import java.util.Arrays;
import java.util.List;

public class Runner {
    static List<Plane> planes = Arrays.asList(
            new PassengerPlane(PlaneProducer.BOEING_737, 164),
            new PassengerPlane(PlaneProducer.BOEING_737_800, 192),
            new PassengerPlane(PlaneProducer.BOEING_747, 242),
            new PassengerPlane(PlaneProducer.AIRBUS_A320, 188),
            new PassengerPlane(PlaneProducer.AIRBUS_A330, 222),
            new PassengerPlane(PlaneProducer.EMBRAER_190, 64),
            new PassengerPlane(PlaneProducer.SUKHOI_SUPERJET_100, 140),
            new PassengerPlane(PlaneProducer.BOMBARDIER_CS300, 196),
            new MilitaryPlane(PlaneProducer.B_1B_LANCER, MilitaryType.BOMBER),
            new MilitaryPlane(PlaneProducer.B_2_SPIRIT, MilitaryType.BOMBER),
            new MilitaryPlane(PlaneProducer.B_52_STRATOFORTRESS, MilitaryType.BOMBER),
            new MilitaryPlane(PlaneProducer.F_15, MilitaryType.FIGHTER),
            new MilitaryPlane(PlaneProducer.F_22, MilitaryType.FIGHTER),
            new MilitaryPlane(PlaneProducer.C_130_HERCULES, MilitaryType.TRANSPORT)
    );

    public static void main(String[] args) {
        Airport airport = new Airport(planes);
        Airport militaryAirport = new Airport(airport.getMilitaryPlanes());
        Airport passengerAirport = new Airport(airport.getPassengerPlanes());
        System.out.println("Military airport sorted by max distance: " + militaryAirport
                .sortByMaxFlightDistance()
                .toString());
        System.out.println("Passenger airport sorted by max speed: " + passengerAirport
                .sortByMaxSpeed()
                .toString());
        System.out.println("Plane with max passenger capacity: " + passengerAirport.getPassengerPlaneWithMaxPassengersCapacity());
    }
}
