import java.util.ArrayList;

/*Registry Class:
* contains all the suspects and their communications*/
public class Registry {

    ArrayList<Suspect> suspectList = new ArrayList<>();
    ArrayList<Communication> commList = new ArrayList<>();
    ArrayList<ArrayList<String>> suspectNumbers = new ArrayList<>();

    public Registry(){

    }

    public void addSuspect(Suspect aSuspect){
        suspectList.add(aSuspect);
        suspectNumbers.add(aSuspect.getNumbers()); //we get the active numbers
    }

    public void addCommunication(Communication aCommunication){
        commList.add(aCommunication);
        establishCommunication(aCommunication);
    }

    //Algorithm to find connections
    private void establishCommunication(Communication aCommunication){
        iterateViaNumber1(aCommunication);  //we first search the suspect of Number1 and add the suspect of number2 as a partner
        iterateViaNumber2(aCommunication);  //then by Number2, reason being someone might have called a number but not necessarily that number calling back. Otherwise we lose a connection from suspect of number2
    }

    //find which suspect belongs to number1
    private void iterateViaNumber1(Communication aCommunication){
        for(int i=0; i<suspectNumbers.size(); i++){   // # of rows                                                                                                  //suspectNumbers| #1    #2   #3  ... <- j
            for(int j=0; j<suspectNumbers.get(i).size(); j++){  // # of specific columns                                                                            // i->  s1      | n1    n2         (j = 1)
                if(aCommunication.number1.contains(suspectNumbers.get(i).get(j))){ // if number1 is the same as an element in the array                             //      s2      | n1               (j = 0)
                    suspectList.get(i).addPotentialPartner(findSuspectOfNumber2(aCommunication)); //add the suspect of number2 as a partner to suspect of number1   //      s3      | n1    n2   n3    (j = 2)
                }                                                                                                                                                   //      ...     | ...   ...  ...     ...
            }                                                                                                                                                       // aComm.number1(00496955444444) == n1,n2,...
        }
    }

    //find which suspect belongs to number2 so we can add him as a partner
    private Suspect findSuspectOfNumber2(Communication aCommunication){
        for(int i=0; i<suspectNumbers.size(); i++){   // # of rows
            for(int j=0; j<suspectNumbers.get(i).size(); j++){  // # of specific columns
                if(aCommunication.number2.contains(suspectNumbers.get(i).get(j))){  // if number2 is the same as an element in the array
                    return suspectList.get(i);  //return the suspect to add him as a partner
                }
            }
        }
        return null;
    }

    //find which suspect belongs to number2
    private void iterateViaNumber2(Communication aCommunication){
        for(int i=0; i<suspectNumbers.size(); i++){   // # of rows
            for(int j=0; j<suspectNumbers.get(i).size(); j++){  // # of specific columns
                if(aCommunication.number2.contains(suspectNumbers.get(i).get(j))){ // if number2 is the same as an element in the array
                    suspectList.get(i).addPotentialPartner(findSuspectOfNumber1(aCommunication));
                }
            }
        }
    }

    //find which suspect belongs to number1 so we can add him as a partner
    private Suspect findSuspectOfNumber1(Communication aCommunication){
        for(int i=0; i<suspectNumbers.size(); i++){   // # of rows
            for(int j=0; j<suspectNumbers.get(i).size(); j++){  // # of specific columns
                if(aCommunication.number1.contains(suspectNumbers.get(i).get(j))){  // if number1 is the same as an element in the array
                    return suspectList.get(i);  //return the suspect to add him as a partner
                }
            }
        }
        return null;
    }

    public Suspect getSuspectWithMostPartners(){
        Suspect topSuspect = suspectList.get(0);                //initialize the topSuspect with the first suspect
        int max = suspectList.get(0).potentialPartners.size();  //get the # of partners of that suspect
        for(int i=1; i<suspectList.size(); i++){                //iterate for all suspects
            int j = suspectList.get(i).potentialPartners.size();//get the # of partners of that suspect
            if(max <= j){                                        //if that exceeds the max # of partners
                max = j;                                        //update max value
                topSuspect = suspectList.get(i);                //update topSuspect
            }
        }
        return topSuspect;
    }

    public PhoneCall getLongestPhoneCallBetween(String number1, String number2){
        //take the elements of PhoneCalls, that equals to the numbers given, from the communications and add them to an array
        ArrayList<PhoneCall> phoneCalls = new ArrayList<>();
        for(Communication comm : commList){  //iterate communications
            if(comm instanceof PhoneCall && ((comm.number1.equals(number1) && comm.number2.equals(number2)) || (comm.number1.equals(number2) && comm.number2.equals(number1)))){
                phoneCalls.add((PhoneCall) comm);
            }
        }

        PhoneCall longestCall = phoneCalls.get(0);              //initialize the longest call made with the first Phone call
        int max = phoneCalls.get(0).duration;                   //get the duration of that phone call
        for(int i=1; i<phoneCalls.size(); i++){                 //iterate all phone calls
            int j = phoneCalls.get(i).duration;                 //get the duration of that phone call
            if(max < j){                                        //if the exceeds the max duration
                max = j;                                        //update max value
                longestCall = phoneCalls.get(i);                //update longest call
            }
        }
        return longestCall;
    }

    public ArrayList<SMS> getMessagesBetween(String number1, String number2){
        //take the elements of SMS, that equals to the numbers given, from the communications and add them to an array
        ArrayList<SMS> sms = new ArrayList<>();
        for(Communication comm : commList){ //iterate communications
            if(comm instanceof SMS && ((comm.number1.equals(number1) && comm.number2.equals(number2)) || (comm.number1.equals(number2) && comm.number2.equals(number1)))){
                sms.add((SMS) comm);
            }
        }

        //remove sms not containing the given keywords
        sms.removeIf(aSms -> !(aSms.message.contains("Bomb") | aSms.message.contains("Attack") | aSms.message.contains("Explosives") | aSms.message.contains("Gun")));
        return sms; //return the remaining
    }

    public void printSuspectsFromCountry(String aCountry) {
        System.out.println("Suspects coming from " + aCountry);
        for(Suspect aSuspect: getSuspectsFromCountry(aCountry)) {                   //iterate all suspects
            aSuspect.printSuspects();
        }
    }

    private ArrayList<Suspect> getSuspectsFromCountry(String aCountry){
        ArrayList<Suspect> suspectsWithSameCountry = suspectList;                               //make a temp array so as to not alter the original
        suspectsWithSameCountry.removeIf(suspect -> !suspect.originCountry.equals(aCountry));   //remove those suspects, not equal to the country given
        return suspectsWithSameCountry;                                                         //return the remaining suspects
    }
}
