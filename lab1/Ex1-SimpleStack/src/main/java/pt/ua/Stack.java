package pt.ua;

public interface Stack<T> {

        T pop();

        int size();

        T peek();

        void push(T elem);

        boolean isEmpty();

        void push(T elem, int boundary);
    }
