public class SMS extends Communication {

    String message;

    public SMS(String number1, String number2, int date, int month, int year, String message){
        super(number1, number2, date, month, year);
        this.message = message;
    }

    public void printInfo(){
        System.out.println("This SMS has the following info \nBetween " + number1 + " --- " + number2 + "\non " + year + "/"+ month + "/" + date + "\nText: " + message);
    }
}
