package planeModels.planes;

import planeModels.ClassificationLevel;
import planeModels.PlaneProducer;
import planeTypes.ExperimentalTypes;
import planeModels.Plane;

import java.util.Objects;

public class ExperimentalPlane extends Plane {

    private ExperimentalTypes experimentalType;
    private ClassificationLevel classificationLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, ExperimentalTypes experimentalType, ClassificationLevel classificationLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.experimentalType = experimentalType;
        this.classificationLevel = classificationLevel;
    }

    public ExperimentalPlane(PlaneProducer producer, ExperimentalTypes experimentalType, ClassificationLevel classificationLevel) {
        super(producer);
        this.experimentalType = experimentalType;
        this.classificationLevel = classificationLevel;
    }

    public ExperimentalTypes getExperimentalType() {
        return experimentalType;
    }

    public void setExperimentalType(ExperimentalTypes experimentalType) {
        this.experimentalType = experimentalType;
    }

    public ClassificationLevel getClassificationLevel() {
        return classificationLevel;
    }

    public void setClassificationLevel(ClassificationLevel classificationLevel) {
        this.classificationLevel = classificationLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExperimentalPlane that = (ExperimentalPlane) o;
        return experimentalType == that.experimentalType &&
                classificationLevel == that.classificationLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experimentalType, classificationLevel);
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", experimentalType=" + experimentalType +
                        ", classificationLevel=" + classificationLevel +
                        '}');
    }
}