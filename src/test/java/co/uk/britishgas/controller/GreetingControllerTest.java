package co.uk.britishgas.controller;

import co.uk.britishgas.model.Message;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnHelloGreetingMessage() throws Exception{
        ResponseEntity<Message> responseEntity = restTemplate.getForEntity("/greetings", Message.class);
        assertEquals("Hello World", responseEntity.getBody().getDescription());
    }
    @Test
    public void shouldReturnHelloManagementMessageWithuserCredentials() throws Exception{
      ResponseEntity<Message> responseEntity  = restTemplate.exchange
                ("/internal-greetings", HttpMethod.GET, new HttpEntity<>(createHeaders("admin", "password")), Message.class);
      assertEquals("Hello Management", responseEntity.getBody().getDescription());
    }

    HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }

}
