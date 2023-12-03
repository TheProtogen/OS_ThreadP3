package view;

import control.ThreadTratamento;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int[] vetor = new int[100000];
        int vetorLength = vetor.length;

        //declara tudo, com números de 1 á 1000
        for (int i = 0; i < vetorLength; i++) {
            vetor[i] = random.nextInt(1000) + 1;
        }
        
        System.out.println("Array que vai receber os sorts: \n"+ Arrays.toString(vetor)+"\n");

        //manda o vetor para ser tratado
        //se mandar o vetor sem o .clone(), o vetor será modificado pelas duas threads ao mesmo tempo
        //e isso seria muito ruim
        for (int i = 0; i < 2; i++) {
            Thread thread = new ThreadTratamento(i,vetor.clone());
            thread.start();
        }


    }
}