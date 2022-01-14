import org.testng.annotations.BeforeMethod;
import planemodels.PlaneProducer;
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
            new MilitaryPlane(PlaneProducer.C_130_HERCULES, MilitaryType.TRANSPORT),
            new ExperimentalPlane(PlaneProducer.Bell_X_14, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
            new ExperimentalPlane(PlaneProducer.Ryan_X_13_VERTIJET, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
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
        actualTransportMilitaryPlanes.add(new MilitaryPlane(PlaneProducer.C_130_HERCULES, MilitaryType.TRANSPORT));
        Assert.assertEquals(actualTransportMilitaryPlanes, expectedTransportMilitaryPlanes);
    }

    @Test
    public void testGetPassengerPlaneWithMaxPassengerCapacity() {
        PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane(PlaneProducer.BOEING_747, 242);
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
                new MilitaryPlane(PlaneProducer.C_130_HERCULES, MilitaryType.TRANSPORT),
                new ExperimentalPlane(PlaneProducer.Bell_X_14, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
                new ExperimentalPlane(PlaneProducer.Ryan_X_13_VERTIJET, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
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