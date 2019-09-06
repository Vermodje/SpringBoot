package base.service;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth20Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface OAuth20GoogleService {

    OAuth20Service getService();

    String getAuth(OAuth20Service service);

    OAuth2AccessToken getToken(String code, OAuth20Service service ) throws InterruptedException, ExecutionException, IOException;

    Response getResponse(OAuth20Service service, OAuthRequest request) throws InterruptedException, ExecutionException, IOException;

    Map convertJSONtoMap(String json) throws IOException;
}
