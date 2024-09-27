package io.pt.payment.client;

public class NoopException extends RuntimeException {

    private int code;
    private String message;

    public NoopException(final int code, final String message) {
        super(null, null, true, false);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
