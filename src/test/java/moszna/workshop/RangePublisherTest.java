package moszna.workshop;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.tck.PublisherVerification;
import org.reactivestreams.tck.TestEnvironment;

public class RangePublisherTest extends PublisherVerification<Integer> {

    public RangePublisherTest() {
        super(new TestEnvironment(300), 300);
    }

    @Override
    public Publisher<Integer> createPublisher(long elements) {
        return new RangePublisher(1, elements);
    }

    @Override
    public Publisher<Integer> createErrorStatePublisher() {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> s) {
                s.onError(new RuntimeException("Can't subscribe subscriber: " + s + ", because of reasons."));
            }
        };
    }

    // ADDITIONAL CONFIGURATION

    @Override
    public long maxElementsFromPublisher() {
        return Long.MAX_VALUE - 1;
    }

    @Override
    public long boundedDepthOfOnNextAndRequestRecursion() {
        return 1;
    }
}