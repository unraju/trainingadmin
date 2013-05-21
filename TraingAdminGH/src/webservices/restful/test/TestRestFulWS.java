package webservices.restful.test;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

public class TestRestFulWS {

	static ClientConfig config = new DefaultClientConfig();
	static Client client = Client.create(config);
	static WebResource service = client.resource(getBaseURI());

	public static void main(String[] args) {

		// testHelloRWS();
		testBestPlayersRWS();

	}

	private static void testBestPlayersRWS() {

		// Fluent interfaces
		System.out.println("### 1 ###"+service.path("rest").path("getBestPlayers").accept(MediaType.TEXT_PLAIN).get(ClientResponse.class)
				.toString());
		// Get plain text
		System.out.println("### 2 ###"+service.path("rest").path("getBestPlayers").accept(MediaType.TEXT_PLAIN).get(String.class));
		// Get XML
		System.out.println("### 3 ###"+service.path("rest").path("getBestPlayers").accept(MediaType.TEXT_XML).get(String.class));
		// The HTML
		System.out.println("### 4 ###"+service.path("rest").path("getBestPlayers").accept(MediaType.TEXT_HTML).get(String.class));

		

	}

	private static void testHelloRWS() {

		// Fluent interfaces
		System.out.println(service.path("rest").path("hello").accept(MediaType.TEXT_PLAIN).get(ClientResponse.class)
				.toString());
		// Get plain text
		System.out.println(service.path("rest").path("hello").accept(MediaType.TEXT_PLAIN).get(String.class));
		// Get XML
		System.out.println(service.path("rest").path("hello").accept(MediaType.TEXT_XML).get(String.class));
		// The HTML
		System.out.println(service.path("rest").path("hello").accept(MediaType.TEXT_HTML).get(String.class));

		// The HTML with Input name
		// Create a Todo
		Form form = new Form();
		form.add("name", " Raju ");

		ClientResponse response = service.path("rest").path("hello").type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);
		System.out.println("Form response " + response.getEntity(String.class));

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/myapp").build();
	}

}
