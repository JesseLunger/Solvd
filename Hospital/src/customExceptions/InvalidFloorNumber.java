package customExceptions;

import java.security.PublicKey;

public class InvalidFloorNumber extends Exception{
    public InvalidFloorNumber(String error){
        super(error);
    }
    public InvalidFloorNumber(){
        super();
    }
}
