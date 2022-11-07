package com.function;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {

    private final AtomicInteger counter = new AtomicInteger();
	private List<User> u = new ArrayList<User>();

    @FunctionName("newuser")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        final String name_query = request.getQueryParameters().get("name");
        final String name = request.getBody().orElse(name_query);
        final String city_query = request.getQueryParameters().get("city");
        final String city = request.getBody().orElse(city_query);

		int id = counter.incrementAndGet();
		u.add(new User(id, name, city));
		return request.createResponseBuilder(HttpStatus.OK).body(u.get(id-1)).build();
    }

    @FunctionName("getuser")
    public HttpResponseMessage getuser(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
        final String id_query = request.getQueryParameters().get("id");
        final String id = request.getBody().orElse(id_query);

		return request.createResponseBuilder(HttpStatus.OK).body(u.get(Integer.parseInt(id)-1)).build();
    }
}
