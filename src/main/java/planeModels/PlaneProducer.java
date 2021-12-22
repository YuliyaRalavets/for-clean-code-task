package planeModels;

public enum PlaneProducer {
    BOEING_737("Boeing-737", 900, 12000, 60500),
    BOEING_737_800("Boeing-737-800", 940, 12300, 63800),
    BOEING_747("Boeing-747", 980, 16100, 70500),
    AIRBUS_A320("Airbus A320", 930, 11100, 65500),
    AIRBUS_A330("Airbus A330", 990, 14800, 80500),
    EMBRAER_190("Embraer 190", 870, 8100, 30800),
    SUKHOI_SUPERJET_100("Sukhoi Superjet 100", 870, 11500, 50500),
    BOMBARDIER_CS300("Bombardier CS300", 920, 11000, 60700),
    B_1B_LANCER("B-1B Lancer", 1050, 2100, 80000),
    B_2_SPIRIT("B-2 Spirit", 1030, 22000, 70000),
    B_52_STRATOFORTRESS("B-52 Stratofortress", 1000, 20000, 80000),
    F_15("F-15", 1500, 12000, 10000),
    F_22("F-22", 1550, 13000, 11000),
    C_130_HERCULES("C-130 Hercules", 650, 5000, 110000),
    Bell_X_14("Bell X-14", 277, 482, 500),
    Ryan_X_13_VERTIJET("Ryan X-13 Vertijet", 560, 307, 500);

    private String model;
    private int maxSpeed;
    private int maxFlightDistance;
    private int maxLoadCapacity;

    PlaneProducer(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.maxFlightDistance = maxFlightDistance;
        this.maxLoadCapacity = maxLoadCapacity;
    }

    public String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxFlightDistance() {
        return maxFlightDistance;
    }

    public int getMaxLoadCapacity() {
        return maxLoadCapacity;
    }
}
