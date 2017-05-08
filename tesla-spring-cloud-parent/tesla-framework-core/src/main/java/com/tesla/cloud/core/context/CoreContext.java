package com.tesla.cloud.core.context;


import javax.servlet.http.HttpSession;

public class CoreContext {

    private HttpSession session;
    private String clientIp;

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

}
