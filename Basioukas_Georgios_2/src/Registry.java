import java.util.ArrayList;

public class Registry {
    ArrayList<Suspect> suspectList = new ArrayList<Suspect>();
    ArrayList<Communication> commList = new ArrayList<Communication>();
    public Suspect aSuspect;
    public PhoneCall aPhoneCall;
    private SMS aSMS;

    public Registry(){

    }

    public void addSuspect(Suspect aSuspect){
        suspectList.add(aSuspect);
    }

    public void addCommunication(Communication aCommunication){
        commList.add(aCommunication);
        updatePartners(aCommunication);
    }

    private void updatePartners(){
        if
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
