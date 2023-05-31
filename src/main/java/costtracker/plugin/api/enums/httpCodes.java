package costtracker.plugin.api.enums;


public enum httpCodes {
    STATUS_OK(200),
    STATUS_CREATED(201),
    STATUS_BAD_REQUEST(400),
    STATUS_METHOD_NOT_ALLOWED(405);

    public final int code;

    httpCodes(int code) {
        this.code = code;
    }
}
