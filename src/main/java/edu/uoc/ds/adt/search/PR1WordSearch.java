package edu.uoc.ds.adt.search;

import edu.uoc.ds.adt.sequential.Set;
import edu.uoc.ds.adt.sequential.Stack;
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
        Iterator<Result> matchedWordsByRows = this.searchByRows(words.values()).values();
        Iterator<Result> matchedWordsByColumns = this.searchByColumns(words.values()).values();

        return this.getAllMatchedWords(matchedWordsByRows, matchedWordsByColumns);
    }

    private Stack<Result> getAllMatchedWords(Iterator<Result> matchedWordsByRows, Iterator<Result> matchedWordsByColumns) {
        Stack<Result> allMatchedWords = null;
        for (Iterator<Result> wordIterator = matchedWordsByRows; wordIterator.hasNext(); ) {
            allMatchedWords.push(wordIterator.next());
        }
        for (Iterator<Result> wordIterator = matchedWordsByColumns; wordIterator.hasNext(); ) {
            allMatchedWords.push(wordIterator.next());
        }

        return allMatchedWords;
    }

    private String getCleanBoard() {
        return this.board.replaceFirst("^\\s*", "");
    }

    private Stack<Result> searchByRows(Iterator<String> wordsToSearch) {
        Stack<Result> matchedWordsByRow = null;
        // Separamos las filas por espaciados.
        String[] rows = this.getCleanBoard().split(" ");
        // Por cada una de las filas...
        for (int i = 0; i < rows.length; i++) {
            for (Iterator<String> wordIterator = wordsToSearch; wordIterator.hasNext(); ) {
                String word = wordIterator.next();
                // Miramos si la fila contiene la palabra que buscamos.
                if (rows[i].contains(word)) {
                    // Añadimos la palabra al stack.
                    matchedWordsByRow.push(new Result(word, i, rows[i].indexOf(word), Direction.HORIZONTAL));
                }
            }
        }
        return matchedWordsByRow;
    }

    private Stack<Result> searchByColumns(Iterator<String> wordsToSearch) {
        String[] rows = this.getCleanBoard().split(" ");
        List<String> columnToSearch = new ArrayList<>();
        Stack<Result> matchedWordsByColumn = null;

        for (int col = 0; col < rows[0].length(); col++) {
            // Construimos un nuevo array: cada valor en vez de ser una fila, será una columna.
            StringBuilder column = new StringBuilder();
            // Por cada fila...
            for (String s : rows) {
                column.append(s.charAt(col));
            }
            // Añadimos la columna a la lista.
            columnToSearch.add(column.toString());


            // Por cada palabra...
            for (Iterator<String> wordIterator = wordsToSearch; wordIterator.hasNext(); ) {
                String word = wordIterator.next();
                if (columnToSearch.get(col).contains(word)) {
                    // Añadimos la palabra al stack.
                    matchedWordsByColumn.push(new Result(word, col, rows[col].indexOf(word), Direction.VERTICAL));
                }
            }
        }

        return matchedWordsByColumn;
    }
}
