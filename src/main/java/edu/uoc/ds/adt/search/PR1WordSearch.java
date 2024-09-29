package edu.uoc.ds.adt.search;

import edu.uoc.ds.adt.sequential.Set;
import edu.uoc.ds.adt.sequential.Stack;
import edu.uoc.ds.adt.sequential.StackArrayImpl;
import edu.uoc.ds.traversal.Iterator;

import java.util.ArrayList;
import java.util.List;

// Asignatura: Diseño de estructura de datos - UOC
// Autoría: Laia López Baca
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
        return this.searchByColumnsAndRows(words.values());
    }

    // Reemplazamos los espacios en blanco del principio para evitar errores y almacenar posiciones vacías en el array.
    private String getCleanBoard() {
        return this.board.replaceFirst("^\\s*", "");
    }


    private Stack<Result> searchByColumnsAndRows(Iterator<String> wordsToSearch) {
        // Separamos las filas por espaciados para poder buscar.
        String[] rows = this.getCleanBoard().split(" ");
        List<String> columns = new ArrayList<>();
        // Guardaremos las palabras encontradas.
        Stack<Result> matchedWords = new StackArrayImpl<>();

        // Por cada palabra...
        for (Iterator<String> wordIterator = wordsToSearch; wordIterator.hasNext(); ) {
            Boolean isWordAlreadyFound = false;
            String word = wordIterator.next();
            // Por cada fila/columna...
            for (int i = 0; i < rows.length; i++) {
                // Construimos un nuevo array con todas las columnas.
                StringBuilder column = new StringBuilder();
                // Formamos la columna por cada carácter de la fila.
                for (String s : rows) {
                    column.append(s.charAt(i));
                }

                // Añadimos la columna a la lista de columnas totales.
                columns.add(column.toString());

                // Si la palabra no se ha encontrado todavía...
                if (!isWordAlreadyFound) {
                    // Miramos si se encuentra en alguna fila.
                    if (rows[i].contains(word)) {
                        isWordAlreadyFound = true;
                        // Añadimos la palabra al stack.
                        // rows[i].indexOf(word) -> De la fila actual, mira en que columna está.
                        matchedWords.push(new Result(word, i, rows[i].indexOf(word), Direction.HORIZONTAL));
                    }

                    // Miramos si se encuentra en alguna columna.
                    if (columns.get(i).contains(word)) {
                        isWordAlreadyFound = true;
                        // Añadimos la palabra al stack.
                        // columns.get(i).indexOf(word) -> De la columna actual, mira en que fila está.
                        matchedWords.push(new Result(word, columns.get(i).indexOf(word), i, Direction.VERTICAL));
                    }
                }
            }
        }

        return matchedWords;
    }
}
