import org.testng.annotations.BeforeMethod;
import planemodels.planes.ExperimentalPlane;
import planemodels.ClassificationLevel;
import planetypes.ExperimentalTypes;
import planetypes.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.Test;
import planemodels.planes.MilitaryPlane;
import planemodels.planes.PassengerPlane;
import planemodels.Plane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AirportTest {
    private Airport airport;
    private static Plane[] arrayOfPlanes = {
            new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
            new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
            new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
            new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
            new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
            new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
            new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
            new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
            new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
            new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
            new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
            new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
            new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
            new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
    };

    @BeforeMethod
    public void setUp() {
        List<Plane> planes = Arrays.asList(arrayOfPlanes);
        airport = new Airport(planes);
    }

    @Test
    public void testGetTransportMilitaryPlanes() {
        List<MilitaryPlane> expectedTransportMilitaryPlanes = airport.getTransportMilitaryPlanes();
        List<MilitaryPlane> actualTransportMilitaryPlanes = new ArrayList<>();
        actualTransportMilitaryPlanes.add(new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT));
        Assert.assertEquals(actualTransportMilitaryPlanes, expectedTransportMilitaryPlanes);
    }

    @Test
    public void testGetPassengerPlaneWithMaxPassengerCapacity() {
        PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = airport.getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertTrue(planeWithMaxPassengerCapacity.equals(expectedPlaneWithMaxPassengersCapacity));
    }

    @Test
    public void testIfPlanesSortedByMaxLoadCapacity() {
        Assert.assertEquals(actualSortByMaxLoadCapacity().getPlanes(), airport.sortByMaxLoadCapacity().getPlanes());
    }

    private Airport actualSortByMaxLoadCapacity() {
        Airport actualAirport;
        Plane[] actualArrayOfPlanes = {
                new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
                new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
                new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
                new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
                new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
                new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
                new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
                new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196),
                new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
                new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
                new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
                new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
                new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
                new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT),
                new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
                new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
        };
        List<Plane> planes = Arrays.asList(actualArrayOfPlanes);
        actualAirport = new Airport(planes.stream()
                .sorted(Comparator.comparingInt(Plane::getMaxLoadCapacity))
                .collect(Collectors.toList()));
        return actualAirport;
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        List<MilitaryPlane> expectedBomberMilitaryPlanes = airport.getBomberMilitaryPlanes();
        Assert.assertNotNull(expectedBomberMilitaryPlanes);
    }

    @Test
    public void testExperimentalPlanesHasClassificationLevelHigherThanUnclassified() {
        Assert.assertTrue(getExperimentalPlanesWithClassificationLevelUnclassified().isEmpty());
    }

    private List<ExperimentalPlane> getExperimentalPlanesWithClassificationLevelUnclassified() {
        return airport.getExperimentalPlanes().stream()
                .filter(e -> e.getClassificationLevel() == ClassificationLevel.UNCLASSIFIED)
                .collect(Collectors.toList());
    }
}