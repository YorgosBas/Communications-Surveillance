import java.util.ArrayList;

public class Suspect {
    ArrayList<String> numbers = new ArrayList<String>();
    ArrayList<Suspect> potentialPartner = new ArrayList<Suspect>();
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

    public boolean isConnectedTo(Suspect aSuspect){
        if(){

        }
        return true;
    }

    public void addPotentialPartner(Suspect aSuspect){
        potentialPartner.add(aSuspect);
    }

    public ArrayList<Suspect> getCommonPartners(Suspect aSuspect){
        return ArrayList<aSuspect> aSuspect;
    }

    public String getName(){
        return name;
    }

    public String getCodeName(){
        return codeName;
    }
}
