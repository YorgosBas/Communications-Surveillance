import java.util.ArrayList;

/*Suspect class:
* contains each suspect and their information
* */
public class Suspect {

    ArrayList<String> numbers = new ArrayList<>();
    ArrayList<Suspect> potentialPartners = new ArrayList<>();
    ArrayList<Suspect> sameCountryPartners = new ArrayList<>();
    String name;
    String codeName;
    String originCountry;
    String activeCountry;

    public Suspect(String name, String codeName, String originCountry, String activeCountry){
        this.name = name;
        this.codeName = codeName;
        this.originCountry = originCountry;
        this.activeCountry = activeCountry;
    }

    public void addNumber(String number){
        numbers.add(number);
    }

    public void addPotentialPartner(Suspect aSuspect){
        if(!(potentialPartners.contains(aSuspect)) && aSuspect != null){    //if the suspect is not already in the partner list
            potentialPartners.add(aSuspect);                                //add him
        }
    }

    public boolean isConnectedTo(Suspect aSuspect){
        return potentialPartners.contains(aSuspect);                        //search if the partner is in the list of a suspect
    }

    public ArrayList<Suspect> getCommonPartners(Suspect aSuspect){
        ArrayList<Suspect> commonPartners = new ArrayList<>();
        for(Suspect suspect : potentialPartners) {                          //iterate all partners of the suspect called   (s1.getC... the s1)
            if(aSuspect.getPotentialPartners().contains(suspect))           //if the given suspect has the same partner with the called suspect
                commonPartners.add(suspect);                                //add them on a list
        }
        return commonPartners;
    }

    public ArrayList<Suspect> getSuggestedPartners(){
        ArrayList<Suspect> suggestedPartners = new ArrayList<>();                   //if A connects with B and C connects with B then under triadic closure A is likely to connect with C
        for(Suspect s1 : potentialPartners){                                        //So A has a partner B and B has a partner C. So from A perspective, C is a partner of his partner B .
            ArrayList<Suspect> commonPotentialPartners = getCommonPartners(s1);     //With that in mind, for each partner of a suspect A we find the common suspects with each of their partners
            commonPotentialPartners.add(this);                                      //We add these to the array list
            for(Suspect s2 : s1.potentialPartners){                                 //For each partner of the partners of suspect A we check if they are in the list
                if(!(commonPotentialPartners.contains(s2))){                        //If they are not, then it is a sign of a suggested partner.
                    suggestedPartners.add(s2);
                }
            }
        }
        return suggestedPartners;
    }

    //partners from the same country
    public ArrayList<Suspect> sameCountryPartners() {
        for (Suspect potentialPartner : potentialPartners)
            if (potentialPartner.originCountry.equals(originCountry)) {
                sameCountryPartners.add(potentialPartner);
            }
        return sameCountryPartners;
    }
    public void printSuspects(){
        System.out.println(name + " (" + codeName + ")");
    }

    //getters
    public String getName(){
        return name;
    }

    public String getCodeName(){
        return codeName;
    }

    public ArrayList<String> getNumbers() {
        return numbers;
    }

    public ArrayList<Suspect> getPotentialPartners(){
        return potentialPartners;
    }
}
