package enums;

public enum AmbulancesModel {

    FORD("Ford"),
    CHEVY("Chevy"),
    HONDA("Honda"),
    SUBARU("Subaru");

    private final String model;

    AmbulancesModel(String model) {
        this.model = model;
    }

    public String getModelName() {
        return model;
    }
}
