import planeModels.planes.ExperimentalPlane;
import planeTypes.MilitaryType;
import planeModels.planes.MilitaryPlane;
import planeModels.planes.PassengerPlane;
import planeModels.Plane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.1
 * @author Vitali Shulha
 */

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = new ArrayList<>(planes);
    }

    public List<? extends Plane> getPlanes() {
        return new ArrayList<>(planes);
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return getPlanesByInstance(PassengerPlane.class);
    }

    private <T extends Plane> List<T> getPlanesByInstance(Class<T> planeClass) {
        List<T> planesByInstance = new ArrayList<>();
        for (Plane plane : planes) {
            if (plane.getClass().getName().equals(planeClass.getName())) {
                planesByInstance.add((T)plane);
            }
        }
        return planesByInstance;
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return getPlanesByInstance(MilitaryPlane.class);
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return getPlanesByInstance(ExperimentalPlane.class);
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        List<PassengerPlane> passengerPlanes = getPassengerPlanes();
        return passengerPlanes.stream().max(Comparator.comparingInt(PassengerPlane::getPassengersCapacity)).get();
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return getMilitaryPlanesByType(MilitaryType.TRANSPORT);
    }

    private List<MilitaryPlane> getMilitaryPlanesByType (MilitaryType type){
        return getMilitaryPlanes().stream()
                .filter(o -> o.getMilitaryType() == type)
                .collect(Collectors.toList());
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return getMilitaryPlanesByType(MilitaryType.BOMBER);
    }

    public Airport sortByMaxFlightDistance() {
        planes.sort(Comparator.comparingInt(Plane::getMaxFlightDistance));
        return this;
    }

    public Airport sortByMaxSpeed() {
        planes.sort(Comparator.comparingInt(Plane::getMaxSpeed));
        return this;
    }

    public Airport sortByMaxLoadCapacity() {
        planes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
        return this;
    }

    private void print(Collection<? extends Plane> collection) {
        for (Plane plane : collection) {
            System.out.println(plane);
        }
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }
}