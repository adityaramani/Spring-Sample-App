package com.sample.twitter.provider;




import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.google.api.Google;

@Configuration
@Scope(value = "request",  proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BaseProvider {
    private Google google;
    private  ConnectionRepository connectionRepository;


    public  BaseProvider(Google google,  ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
        this.google=google;
    }

    public ConnectionRepository getConnectionRepository() {
        return connectionRepository;
    }

    public void setConnectionRepository(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public Google getGoogle() {
        return google;
    }

    public void setGoogle(Google google) {
        this.google = google;
    }


}
