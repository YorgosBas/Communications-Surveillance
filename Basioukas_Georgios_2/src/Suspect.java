import java.util.ArrayList;

/*Suspect class:
* contains each suspect and their information
* */
public class Suspect {

    ArrayList<String> numbers = new ArrayList<>();
    ArrayList<Suspect> potentialPartners = new ArrayList<>();
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

    //partners from the same country
    public void printPotentialPartners(){
        System.out.println("\nPotential Partners of Suspect: " + name);
        for(Suspect potentialPartner : potentialPartners)
            if(potentialPartner.originCountry.equals(originCountry)){
                System.out.println(potentialPartner.name + " (" + potentialPartner.codeName + ")*");
            }else{
                System.out.println(potentialPartner.name + " (" + potentialPartner.codeName + ")");
            }

        System.out.println("Asterisk is from the same country");
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
