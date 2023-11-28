package ae.company.banking.infrastructure.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

//Just a functional example for an entrypoint
@Component
public class TestHandler {
	public Mono<ServerResponse> hello(ServerRequest request) {
		return ServerResponse.ok().body( Mono.just("Hello, World!"), String.class);
	}
}
