package com.lean.corejava;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

public class ForkJoinTest {

	static class MyTask extends RecursiveAction {
		private static final long serialVersionUID = -6529346889997240841L;
		final AtomicLong counter;

		public MyTask() {
			counter = new AtomicLong(100);
		}

		public MyTask(AtomicLong counter) {
			this.counter = counter;
		}

		@Override
		protected void compute() {

			System.out.println(Thread.currentThread()
					+ " ForkJoinTest.basicExampleofForkJoin().new RecursiveAction() {...}.compute() :" + counter.get());

			if (counter.decrementAndGet() > 0)
				try {
					new MyTask(counter).fork().get();
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	};

	@Test
	public void basicExampleofForkJoin() throws Exception {
		ForkJoinPool fkPool = new ForkJoinPool(1);
		MyTask task = new MyTask();
		fkPool.execute(task);

		task.join();
		System.out.println("TASK :" + task.counter.get());
		fkPool.shutdownNow();
	}
}
