public class PhoneCall extends Communication {

    String number1;
    String number2;
    int date;
    int month;
    int year;
    int duration;

    public PhoneCall(String number1, String number2, int date, int month, int year, int duration){
        this.number1 = number1;
        this.number2 = number2;
        this.date = date;
        this.month = month;
        this.year = year;
        this.duration = duration;
    }

    public void printInfo(){

    }
}
