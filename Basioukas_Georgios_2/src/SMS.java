public class SMS extends Communication {

    String message;

    public SMS(String number1, String number2, int date, int month, int year, String message){
        super(number1,number2,date,month,year);
        this.message = message;
    }

    public void printInfo(){

    }
}
