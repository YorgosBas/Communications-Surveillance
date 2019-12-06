public class SMS extends Communication {

    String number1;
    String number2;
    int date;
    int month;
    int year;
    String message;

    public SMS(String number1, String number2, int date, int month, int year, String message){
        this.number1 = number1;
        this.number2 = number2;
        this. date = date;
        this.month = month;
        this.year = year;
        this.message = message;
    }

    public void printInfo(){

    }
}
