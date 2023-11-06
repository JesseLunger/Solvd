package location;

public class Floor {
    /*Decription: Floor class that represents a single in a department. The purpose
     * of the class if for staff evenly distribute patients to nurses on given floors
     *
     * */
    private Integer numNurses = 0;
    private Integer numPatients = 0;


    //Getter/Setter functions


    public Integer getNumNurses() {
        return this.numNurses;
    }

    public Integer getNumPatients() {
        return this.numPatients;
    }

    public void addNurse() {
        this.numNurses += 1;
    }

    public void removeNurse() {
        this.numNurses -= 1;
    }

    public void addPatient() {
        this.numPatients += 1;
    }

    public void removePatient() {
        this.numPatients -= 1;
    }
}
