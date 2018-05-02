package Domini;

import java.util.UUID;
import java.util.Vector;

public class TableroCuadrado extends Mapa {

    /**
     * Funcion creadora de TableroCuadrado
     * @param filas numero de filas del hidato
     * @param columnas numero de columnas del hidato
     */
    public TableroCuadrado(int filas, int columnas, String[][] tab){
        this.matrix = tab;
        this.filas = filas;
        this.columnas = columnas;
        this.interrogants = 0;
        this.ID = UUID.randomUUID().toString();
        this.teSolucio = false;
        instances.add(this.ID);
    }

    /**
     * Calcula para una casilla "?" a rellenar del hidato, si se le puede poner el numero restante: toInsert
     * @param x,y fila y columna de la casilla "?"
     * @param A El hidato a comprobar
     * @param toInsert El numero restante a comprobar
     * @param v El vector de numeros restantes
     * @return Boolean indicando si se puede poner el numero o no en la casilla.
     */
    @Override
    protected boolean posicioCorrecte(int x, int y, String[][] A, int toInsert, Vector<Integer> v){
        boolean adjacentPetit = false;
        boolean adjacentGran = false;
        boolean adjacentInterrogant = false;
        for(int i=0; i<4; ++i){
            int xx = x;
            int yy = y;
            switch (i){
                case 0:
                    xx=x;
                    yy=y-1;
                    break;
                case 1:
                    xx=x-1;
                    yy=y;
                    break;
                case 2:
                    xx=x;
                    yy=y+1;
                    break;
                case 3:
                    xx=x+1;
                    yy=y;
                    break;
            }
            if(xx>=0 && yy>=0 && xx<A.length && yy<A[0].length) {

                if (isInteger(A[xx][yy])) {
                    int tableValue = Integer.parseInt(A[xx][yy]);

                    if (tableValue == toInsert - 1) adjacentPetit = true;
                    if (tableValue == toInsert + 1) adjacentGran = true;
                } else if (A[xx][yy].equals("?")) adjacentInterrogant = true;
            }
        }

        if(toInsert == 1 && adjacentGran) return true;
        if(toInsert == 1 && adjacentInterrogant) if(v.contains(toInsert+1)) return true;

        if(adjacentGran && adjacentPetit) return true;
        if(adjacentGran && adjacentInterrogant) if(v.contains(toInsert-1))return true;
        if(adjacentPetit && adjacentInterrogant) if(v.contains(toInsert+1)) return true;

        if(adjacentPetit && v.size() == 1 && toInsert == interrogants+numeros) return true;
        //if(adjacentGran && v.size() == 1) return true;

    return false;
    }

    /**
     * Comprueba si el hidato (matrix) ya resuelto está bien resuelto o no.
     * @return Boolean indicando si esta bien resuelto o no.
     */
    @Override
    public boolean matriuCorrecte(){
        numerosExistents();
        int x = 0;
        int y = 0;
        boolean trobat = false;
        for(int y1 = 0; y1 < filas && !trobat; y1++){
            for (int x1 = 0; x1 < columnas; x1++){
                if(matrix[y1][x1].equals("1")){
                    trobat = true;
                    y = y1;
                    x = x1;
                }
            }
        }
        if(!trobat) return false;

        boolean correcte = true;
        int buscar = 2;
        int interr = interrogants + numeros -1;

        Integer[] pos = new Integer[2];
        Integer[] posant = new Integer[2];
        posant[0] = y;
        posant[1] = x;
        while(interr != 0 && correcte){
            trobat = false;
            for(int i = 0; (i <= 3) && !trobat; i++){
                pos = siguienteCasilla(posant,i);
                if ((pos[1] >= 0) && (pos[1] <= columnas -1) && (pos[0] >= 0) && (pos[0] <= filas -1) && matrix[pos[0]][pos[1]].equals(Integer.toString(buscar))){
                    interr--;
                    buscar++;
                    trobat = true;
                    posant[0] = pos[0];
                    posant[1] = pos[1];
                }

            }
            if (!trobat) correcte = false;
            else correcte = true;
        }
        return correcte;
    }


}
