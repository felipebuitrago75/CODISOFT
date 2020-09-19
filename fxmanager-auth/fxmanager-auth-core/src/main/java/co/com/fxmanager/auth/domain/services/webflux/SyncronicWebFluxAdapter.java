package co.com.fxmanager.auth.domain.services.webflux;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class SyncronicWebFluxAdapter {

	@Autowired
	private TaskExecutor taskExecutor;

	public <T> Flux<T> ofMany(Supplier<Collection<T>> supplier) {
		Flux<T> flux = Flux.create(sink -> {
			taskExecutor.execute(() -> {
				try {
					Collection<T> collection = supplier.get();
					collection.forEach(sink::next);
				} catch (Exception e) {
					sink.error(e);
				} finally {
					sink.complete();
				}

			});
		});
		return flux;
	}

	public <T> Flux<T> ofStream(Supplier<Stream<T>> supplier) {
		Flux<T> flux = Flux.create(sink -> {
			taskExecutor.execute(() -> {
				try {
					supplier.get().forEach(sink::next);
				} catch (Exception e) {
					sink.error(e);
				} finally {
					sink.complete();
				}

			});
		});
		return flux;
	}

	public <T> Mono<T> ofOne(Supplier<T> supplier) {
		Mono<T> flux = Mono.create(sink -> {
			taskExecutor.execute(() -> {
				try {
					sink.success(supplier.get());
				} catch (Exception e) {
					sink.error(e);
				}

			});
		});
		return flux;
	}

	public <T> Mono<T> ofVoid(Runnable runnable) {
		Mono<T> flux = Mono.create(sink -> {
			taskExecutor.execute(() -> {
				try {
					runnable.run();
					sink.success();
				} catch (Exception e) {
					sink.error(e);
				}

			});
		});
		return flux;
	}

	public <T> Flux<T> ofFlux(Supplier<Flux<T>> supplier) {
		Flux<T> flux = Flux.create(sink -> {
			taskExecutor.execute(() -> {
				Flux<T> newFlux = supplier.get();
				newFlux.subscribe(sink::next, sink::error, sink::complete);
			});
		});
		return flux;
	}

	public <T> Mono<T> ofMono(Supplier<Mono<T>> supplier) {
		Mono<T> flux = Mono.create(sink -> {
			taskExecutor.execute(() -> {
				Mono<T> mono = supplier.get();
				mono.subscribe(sink::success, sink::error);
			});
		});
		return flux;
	}

}
