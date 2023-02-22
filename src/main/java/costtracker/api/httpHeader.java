package costtracker.api;


public enum httpHeader {
    HEADER_ALLOW("Allow"),
    HEADER_CONTENT_TYPE("Content-Type"),
    METHOD_GET("GET"),
    METHOD_POST("POST"),
    ALLOWED_METHODS("GET, POST");

    public final String headerData;

    httpHeader(String headerData) {
        this.headerData = headerData;
    }

}
