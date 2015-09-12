package net.xangelcorp.util.flow;

public interface TakesResult<T> {
	void receive(T result);
}
