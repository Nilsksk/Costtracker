package costtracker.api.enums;


public enum httpHeader {
    HEADER_ALLOW("Allow"),
    HEADER_CONTENT_TYPE("Content-Type"),
    METHOD_GET("GET"),
    METHOD_POST("POST"),
    METHOD_PUT("PUT"),
    METHOD_DELETE("DELETE"),
    ALLOWED_METHODS("GET, POST, PUT, DELETE");

    public final String headerData;

    httpHeader(String headerData) {
        this.headerData = headerData;
    }

}
