package com.dmitrenko.leetcode.pricethrottler;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PriceThrottler implements PriceProcessor, AutoCloseable {

    private final Map<PriceProcessor, Map<String, Double>> listeners = new ConcurrentHashMap<>();
    private final Map<String, Double> prices = new ConcurrentHashMap<>();

    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    @Override
    public void onPrice(String ccyPair, double rate) {
        if (listeners.size() == 1) {
            CompletableFuture.runAsync(getMainPrice(ccyPair, rate), threadPool);
        }

        if (listeners.size() != 1) {
            prices.put(ccyPair, rate);
            listeners.keySet().forEach(o -> o.onPrice(ccyPair, rate));
        }
    }

    private Runnable getMainPrice(String ccyPair, double rate) {
        return () -> {
            var mainPrices = this.listeners
                    .values()
                    .stream()
                    .findFirst()
                    .get();

            this.prices.put(ccyPair, mainPrices.get(ccyPair) == null ? rate : mainPrices.get(ccyPair));
        };
    }

    @Override
    public void subscribe(PriceProcessor priceProcessor) {
        if (listeners.get(priceProcessor) == null) {
            listeners.put(priceProcessor, prices);
            priceProcessor.subscribe(this);
        }
    }

    @Override
    public void unsubscribe(PriceProcessor priceProcessor) {
        if (listeners.get(priceProcessor) != null) {
            listeners.remove(priceProcessor);
            priceProcessor.unsubscribe(this);
            if (listeners.size() == 0) close();
        }
    }

    @Override
    public void close() {
        threadPool.shutdown();
    }
}
