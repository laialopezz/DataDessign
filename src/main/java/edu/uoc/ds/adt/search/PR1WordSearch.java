package edu.uoc.ds.adt.search;

import edu.uoc.ds.adt.sequential.Set;
import edu.uoc.ds.adt.sequential.Stack;
import edu.uoc.ds.adt.sequential.StackArrayImpl;
import edu.uoc.ds.traversal.Iterator;

import java.util.ArrayList;
import java.util.List;

public class PR1WordSearch {
    private final String board;


    public static class Result {
        String word;
        Direction direcction;
        int row;
        int col;

        public Result(String word, int row, int col, Direction direcction) {
            this.word = word;
            this.row = row;
            this.col = col;
            this.direcction = direcction;
        }
    }

    enum Direction {
        VERTICAL,
        HORIZONTAL
    }

    public PR1WordSearch(String newBoard) {
        board = newBoard;
    }

    public Stack<Result> search(Set<String> words) {
        return this.searchByColumnsAndWords(words.values());
    }


    private String getCleanBoard() {
        return this.board.replaceFirst("^\\s*", "");
    }


    private Stack<Result> searchByColumnsAndWords(Iterator<String> wordsToSearch) {
        // Separamos las filas por espaciados.
        String[] rows = this.getCleanBoard().split(" ");
        List<String> columnToSearch = new ArrayList<>();
        Stack<Result> matchedWords = new StackArrayImpl<>();

        // Por cada palabra...
        for (Iterator<String> wordIterator = wordsToSearch; wordIterator.hasNext(); ) {
            Boolean isWordAlreadyFound = false;
            String word = wordIterator.next();

            for (int i = 0; i < rows[0].length(); i++) {
                // Construimos un nuevo array: cada valor en vez de ser una fila, será una columna.
                StringBuilder column = new StringBuilder();
                // Por cada fila...
                for (String s : rows) {
                    column.append(s.charAt(i));
                }

                // Añadimos la columna a la lista.
                columnToSearch.add(column.toString());

                if (!isWordAlreadyFound) {
                    if (rows[i].contains(word)) {
                        isWordAlreadyFound = true;
                        // Añadimos la palabra al stack.
                        matchedWords.push(new Result(word, i, rows[i].indexOf(word), Direction.HORIZONTAL));
                    }

                    if (columnToSearch.get(i).contains(word)) {
                        isWordAlreadyFound = true;
                        // Añadimos la palabra al stack.
                        matchedWords.push(new Result(word, columnToSearch.get(i).indexOf(word), i, Direction.VERTICAL));
                    }
                }
            }
        }

        return matchedWords;
    }
}
