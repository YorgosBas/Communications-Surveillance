import java.util.ArrayList;

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
        if(!(potentialPartners.contains(aSuspect)) && aSuspect != null){
            potentialPartners.add(aSuspect);
        }
    }

    public boolean isConnectedTo(Suspect aSuspect){
        return potentialPartners.contains(aSuspect);
    }

    public ArrayList<Suspect> getCommonPartners(Suspect aSuspect){
        ArrayList<Suspect> commonPartners = new ArrayList<>();
        for(Suspect suspect : potentialPartners) {
            if(aSuspect.getPotentialPartners().contains(suspect))
                commonPartners.add(suspect);
        }
        return commonPartners;
    }

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
