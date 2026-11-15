package lab.payments.wallet;

public final class AccountId {

    private final String value;

    public AccountId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountId)) return false;
        return value.equals(((AccountId) o).value);
    }

    @Override
    public String toString() {
        return value;
    }
}
