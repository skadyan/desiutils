package com.lean.corejava;

import java.util.concurrent.Callable;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExchangeExample {

	public static void main(String[] args) throws Exception {
		ExchangeExample example = new ExchangeExample();

		example.doIt();

	}

	public void doIt() throws InterruptedException, ExecutionException {
		Exchanger<String> exchanger = new Exchanger<>();
		ExecutorService pool = Executors.newCachedThreadPool();
		Future<String> goodValueGetter = pool.submit(new GiveCashAndTakeGood(
				exchanger, "1000"));
		Future<String> moneyValueGetter = pool.submit(new GiveCashAndTakeGood(
				exchanger, "SofaSet"));
		Future<String> goodValueGetter2 = pool.submit(new GiveCashAndTakeGood(
				exchanger, "2000"));
		
		Future<String> moneyValueGetter2 = pool.submit(new GiveCashAndTakeGood(
				exchanger, "SofaSet2"));
		System.out.println("Good ->" + goodValueGetter.get());
		System.out.println("Good2 ->" + goodValueGetter2.get());
		System.out.println("Amount ->" + moneyValueGetter.get());
		System.out.println("Amount2 ->" + moneyValueGetter2.get());
		
		pool.shutdownNow();
	}

	public static class GiveCashAndTakeGood implements Callable<String> {

		private Exchanger<String> exchanger;
		private String amount;

		public GiveCashAndTakeGood(Exchanger<String> exchanger, String amount) {
			this.exchanger = exchanger;
			this.amount = amount;
		}

		@Override
		public String call() throws Exception {
			return exchanger.exchange(amount);
		}

	}

}
