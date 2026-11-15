package lab.payments.kyc;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Local implementation for development. The object storage client (KYC-14) replaces it in deployed environments.
 */
@Component
public class InMemoryStorageClient implements StorageClient {

    private final Map<String, byte[]> objects = new ConcurrentHashMap<>();

    @Override
    public void put(String path, byte[] content) {
        objects.put(path, content);
    }

    @Override
    public byte[] get(String path) {
        return objects.get(path);
    }
}
