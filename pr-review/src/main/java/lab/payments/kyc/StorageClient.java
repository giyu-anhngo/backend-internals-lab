package lab.payments.kyc;

public interface StorageClient {

    void put(String path, byte[] content);

    byte[] get(String path);
}
