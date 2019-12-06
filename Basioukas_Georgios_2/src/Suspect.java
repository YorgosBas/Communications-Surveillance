import java.util.ArrayList;

public class Suspect {
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

    }

    public boolean isConnectedTo(Suspect aSuspect){
        return true;
    }

    public ArrayList<Suspect> getCommonPartners(Suspect aSuspect){
        return aSuspect;
    }

    public String getName(){
        return name;
    }

    public String getCodeName(){
        return codeName;
    }
}
