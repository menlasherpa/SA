package webshop.web;

public class CustomErrorType {
    private String errorMsg;

    public CustomErrorType(String msg) {
        this.errorMsg = msg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
