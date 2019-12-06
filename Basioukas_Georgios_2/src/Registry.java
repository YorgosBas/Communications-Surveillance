import java.util.ArrayList;

public class Registry {

    ArrayList<Suspect> suspectList = new ArrayList<>();
    ArrayList<Communication> commList = new ArrayList<>();
    ArrayList<ArrayList<String>> suspectNumbers = new ArrayList<>();

    public Registry(){

    }

    public void addSuspect(Suspect aSuspect){
        suspectList.add(aSuspect);
        suspectNumbers.add(aSuspect.getNumbers());
    }

    public void addCommunication(Communication aCommunication){
        commList.add(aCommunication);
        establishCommunication(aCommunication);
    }

    //Algorithm
    public void establishCommunication(Communication aCommunication){
        iterateViaNumber1(aCommunication);
        iterateViaNumber2(aCommunication);
    }

    public void iterateViaNumber1(Communication aCommunication){
        for(int i=0; i<suspectNumbers.size(); i++){
            for(int j=0; j<suspectNumbers.get(i).size(); j++){
                if(aCommunication.number1.contains(suspectNumbers.get(i).get(j))){
                    suspectList.get(i).addPotentialPartner(findSuspectOfNumber2(aCommunication));
                }
            }
        }
    }

    public Suspect findSuspectOfNumber2(Communication aCommunication){
        for(int i=0; i<suspectNumbers.size(); i++){
            for(int j=0; j<suspectNumbers.get(i).size(); j++){
                if(aCommunication.number2.contains(suspectNumbers.get(i).get(j))){
                    return suspectList.get(i);
                }
            }
        }
        return null;
    }

    public void iterateViaNumber2(Communication aCommunication){
        for(int i=0; i<suspectNumbers.size(); i++){
            for(int j=0; j<suspectNumbers.get(i).size(); j++){
                if(aCommunication.number2.contains(suspectNumbers.get(i).get(j))){
                    suspectList.get(i).addPotentialPartner(findSuspectOfNumber1(aCommunication));
                }
            }
        }
    }

    public Suspect findSuspectOfNumber1(Communication aCommunication){
        for(int i=0; i<suspectNumbers.size(); i++){
            for(int j=0; j<suspectNumbers.get(i).size(); j++){
                if(aCommunication.number1.contains(suspectNumbers.get(i).get(j))){
                    return suspectList.get(i);
                }
            }
        }
        return null;
    }

    public Suspect getSuspectWithMostPartners(){
        Suspect topSuspect = suspectList.get(0);
        int max = suspectList.get(0).potentialPartners.size();
        for(int i=1; i<suspectList.size(); i++){
            int j = suspectList.get(i).potentialPartners.size();
            if(max < j){
                max = j;
                topSuspect = suspectList.get(i);
            }
        }
        return topSuspect;
    }

    public PhoneCall getLongestPhoneCallBetween(String number1, String number2){
        ArrayList<PhoneCall> phoneCalls = new ArrayList<>();
        for(Communication comm : commList){
            if(comm instanceof PhoneCall && ((comm.number1.equals(number1) && comm.number2.equals(number2)) || (comm.number1.equals(number2) && comm.number2.equals(number1)))){
                phoneCalls.add((PhoneCall) comm);
            }
        }

        PhoneCall longestCall = phoneCalls.get(0);
        int max = phoneCalls.get(0).duration;
        for(int i=1; i<phoneCalls.size(); i++){
            int j = phoneCalls.get(i).duration;
            if(max < j){
                max = j;
                longestCall = phoneCalls.get(i);
            }
        }
        return longestCall;
    }

    public ArrayList<SMS> getMessagesBetween(String number1, String number2){
        ArrayList<SMS> sms = new ArrayList<>();
        for(Communication comm : commList){
            if(comm instanceof SMS && ((comm.number1.equals(number1) && comm.number2.equals(number2)) || (comm.number1.equals(number2) && comm.number2.equals(number1)))){
                sms.add((SMS) comm);
            }
        }
        sms.removeIf(aSms -> !(aSms.message.contains("Bomb") | aSms.message.contains("Attack") | aSms.message.contains("Explosives") | aSms.message.contains("Gun")));
        return sms;
    }

    public void printSuspectsFromCountry(String aCountry) {
        System.out.println("Suspects coming from " + aCountry);
        for(Suspect aSuspect: getSuspectsFromCountry(aCountry)) {
            aSuspect.printSuspects();
        }
    }

    public ArrayList<Suspect> getSuspectsFromCountry(String aCountry){
        ArrayList<Suspect> suspectsWithSameCountry = suspectList;
        suspectsWithSameCountry.removeIf(suspect -> !suspect.originCountry.equals(aCountry));
        return suspectsWithSameCountry;
    }
}
