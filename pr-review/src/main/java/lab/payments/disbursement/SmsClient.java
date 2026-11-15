package lab.payments.disbursement;

public interface SmsClient {

    void send(String phoneNumber, String message);
}
