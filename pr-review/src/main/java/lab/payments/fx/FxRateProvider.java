package lab.payments.fx;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
public class FxRateProvider {

    private final FxRateClient client;

    private Map<String, BigDecimal> rates = Map.of();

    public FxRateProvider(FxRateClient client) {
        this.client = client;
    }

    @Scheduled(fixedDelayString = "${payments.fx.refresh-interval-ms}")
    public void refresh() {
        try {
            rates = Map.copyOf(client.fetchRatesToVnd());
        } catch (Exception e) {
            // keep the previous rates
        }
    }

    public Optional<BigDecimal> findRate(String currency) {
        return Optional.ofNullable(rates.get(currency));
    }
}
