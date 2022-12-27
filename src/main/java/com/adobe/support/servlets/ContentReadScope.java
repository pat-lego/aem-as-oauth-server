package com.adobe.support.servlets;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import org.apache.jackrabbit.api.security.user.User;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.oauth.server.Scope;
import com.adobe.granite.oauth.server.ScopeWithPrivileges;

@Component(service = Scope.class)
public class ContentReadScope implements ScopeWithPrivileges {
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static final String CONTENT_RESOURCE_URI = "/content";
    private static final String CONTENT_RESOURCE_READ_SCOPE_NAME = "content_read";

    public ContentReadScope() {
    }

    @Override
    public String getDescription(HttpServletRequest arg0) {
        return "Read Content";
    }

    @Override
    public String getEndpoint() {
        return null;
    }

    @Override
    public String getName() {
        return CONTENT_RESOURCE_READ_SCOPE_NAME;
    }

    @Override
    public String getResourcePath(User user) {
        try {
            logger.info("The user id is {}", user.getID());
        } catch (RepositoryException e) {
           logger.error("Failed to get the user id", e);
        }
        return CONTENT_RESOURCE_URI;
    }

    @Override
    public String[] getPrivileges() {
        return new String[] { "jcr:read" };
    }
}