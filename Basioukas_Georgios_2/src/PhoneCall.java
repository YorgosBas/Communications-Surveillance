
public class PhoneCall extends Communication {

    int duration;

    public PhoneCall(String number1, String number2, int date, int month, int year, int duration){
        super(number1, number2, date, month, year);
        this.duration = duration;
    }

    public void printInfo(){
        System.out.println("This phone call has the following info \nBetween " + number1 + " --- " + number2 + "\non " + year + "/"+ month + "/" + date + "\nDuration: " + duration);
    }
}
