package lab.payments.fx;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Fixed rates used until the treasury rate API integration (PAY-150) is ready.
 */
@Component
public class StaticFxRateClient implements FxRateClient {

    @Override
    public Map<String, BigDecimal> fetchRatesToVnd() {
        return Map.of(
                "USD", new BigDecimal("25412.37"),
                "EUR", new BigDecimal("27301.15"));
    }
}
