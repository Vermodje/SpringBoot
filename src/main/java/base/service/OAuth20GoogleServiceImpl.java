package base.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class OAuth20GoogleServiceImpl implements OAuth20GoogleService {

    private OAuth20Service service;
    private final String API_KEY = "765433196356-9huilh6mtmtavb3dhqmf3h1937gpm7dd.apps.googleusercontent.com";
    private final String API_SECRET = "Y0lufJDcJeVU7FQSiwCkg98S";
    private final String SCOPE = "profile email";
    private final String CALLBACK = "http://localhost:8081/callback";


    public OAuth20GoogleServiceImpl() {
        this.service = new ServiceBuilder(API_KEY)
                .apiSecret(API_SECRET)
                .defaultScope(SCOPE)
                .callback(CALLBACK)
                .build(GoogleApi20.instance());
    }
    @Override
    public OAuth20Service getService() {
        return service;
    }
    @Override
    public  String getAuth(OAuth20Service service){
        return service.getAuthorizationUrl();
    }
    @Override
    public OAuth2AccessToken getToken(String code, OAuth20Service service ) throws InterruptedException, ExecutionException, IOException {
        return service.getAccessToken(code);
    }
    @Override
    public Response getResponse(OAuth20Service service, OAuthRequest request) throws InterruptedException, ExecutionException, IOException {
            return service.execute(request);
    }

    @Override
    public Map convertJSONtoMap(String json) throws IOException {
        return new ObjectMapper().readValue(json, Map.class);
    }
}
