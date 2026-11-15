package lab.payments.statement;

public interface EmailClient {

    void send(String to, String subject, String body);
}
