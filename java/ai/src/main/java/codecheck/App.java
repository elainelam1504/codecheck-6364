package codecheck;
import java.io.IOException;
import java.util.*;
import java.lang.String;

public class App {
	public static void main(String[] args) {
		Digraph graph = new Digraph(args);
		graph.Play();
	}
}
