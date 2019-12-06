import java.util.ArrayList;

public class Registry {

    public Suspect aSuspect;
    public PhoneCall aPhoneCall;
    private SMS aSMS;
    public Registry(){

    }

    public void addSuspect(Suspect aSuspect){

    }

    public void addCommunication(Communication aCommunication){

    }

    public Suspect getSuspectWithMostPartners(){
        return aSuspect;
    }

    public PhoneCall getLongestPhoneCallBetween(String number1, String number2){
        return aPhoneCall;
    }

    public ArrayList<SMS> getMessagesBetween(String number1, String number2){
        return aSMS;
    }

    public void printSuspectsFromCountry(String aCountry){

    }
}
