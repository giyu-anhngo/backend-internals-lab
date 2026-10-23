package lab.payments.fx;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Source of exchange rates. Each entry maps a currency code to how many VND one unit of it buys.
 */
public interface FxRateClient {

    Map<String, BigDecimal> fetchRatesToVnd();
}
