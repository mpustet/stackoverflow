package de.mle.stackoverflow;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class RequestScopeBean {
    public RequestScopeBean(HttpServletRequest request) {
    }
}
