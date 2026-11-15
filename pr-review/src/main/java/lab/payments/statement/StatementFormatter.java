package lab.payments.statement;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

public class StatementFormatter {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static String formatLine(Transaction tx) {
        Date date = Date.from(tx.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
        return FORMAT.format(date) + "  " + tx.getDescription() + "  " + tx.getAmount();
    }
}
