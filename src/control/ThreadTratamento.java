package control;

public class ThreadTratamento extends Thread {

    private final int idThread;
    public int[] vetor;

    public ThreadTratamento(int idThread, int[] vetor){
        this.idThread = idThread;
        this.vetor = vetor;
    }

    @Override
    public void run() {

        switch (idThread) {
            case 0:
                int[] quickVetor = vetor;

                long thread0Inicio = System.nanoTime();
                quickSort(quickVetor, 0, vetor.length - 1);
                long thread0Fim = System.nanoTime();

                //para imprimir o vetor, só tirar o comentário
                //System.out.println(Arrays.toString(quickVetor));

                long thread0Total = thread0Fim - thread0Inicio;
                System.out.println("Quick sort realizado em "+thread0Total/1e9 +" segundos");
                break;

            case 1:
                int[] mergeVetor = vetor;

                long thread1Inicio = System.nanoTime();
                mergeSort(mergeVetor);
                long thread1Fim = System.nanoTime();

                //para imprimir o vetor, só tirar o comentário
                //System.out.println(Arrays.toString(mergeVetor));

                long thread1Total = thread1Fim - thread1Inicio;
                System.out.println("Merge sort realizado em "+thread1Total/1e9 +" segundos");
                break;
        }
    }

    //quicksort recursivo, padrão
    private void quickSort(int[] vetorSort, int indexMenor, int indexMaior){

        if (indexMenor >= indexMaior){
            return;
        }

        int piv = vetorSort[indexMaior];
        int esquerda = indexMenor;
        int direita = indexMaior;

        while (esquerda < direita){

            while (vetor[esquerda] <= piv && esquerda < direita){
                esquerda++;
            }

            while (vetor[direita] >= piv && esquerda < direita){
                direita--;
            }

            mudarValor(vetor, esquerda, direita);
        }
        mudarValor(vetor, esquerda, indexMaior);

        quickSort(vetor, indexMenor,esquerda - 1);
        quickSort(vetor, esquerda + 1, indexMaior);
    }

    //troca os valores das variáveis
    private void mudarValor (int[] vetor, int valor1, int valor2) {
        int temp = vetor[valor1];
        vetor[valor1] = vetor[valor2];
        vetor[valor2] = temp;
    }


    //recursivo também, padrão
    private void mergeSort(int[] vetorSort){
        int vetorLenght = vetorSort.length;

        if (vetorLenght < 2){
            return;
        }

        int meio = vetorLenght / 2;
        int[] parteEsquerda = new int[meio];
        int[] parteDireita = new int[vetorLenght - meio];

        System.arraycopy(vetorSort, 0, parteEsquerda, 0, meio);
        if (vetorLenght - meio >= 0) System.arraycopy(vetorSort, meio, parteDireita, 0, vetorLenght - meio);

        mergeSort(parteEsquerda);
        mergeSort(parteDireita);

        merge(vetorSort,parteEsquerda,parteDireita);
    }

    private void merge(int[] vetorSort, int[] parteEsquerda, int[] parteDireita){
        int tamanhoEsquerda = parteEsquerda.length;
        int tamanhoDireita = parteDireita.length;

        int e = 0, d = 0, m = 0;

        while(e < tamanhoEsquerda && d < tamanhoDireita){
            if (parteEsquerda[e] <= parteDireita[d]){
                vetorSort[m] = parteEsquerda[e];
                e++;
            } else {
                vetorSort[m] = parteDireita[d];
                d++;
            }
            m++;
        }
        while(e < tamanhoEsquerda){
            vetorSort[m] = parteEsquerda[e];
            e++;
            m++;
        }
        while(d < tamanhoDireita){
            vetorSort[m] = parteDireita[d];
            d++;
            m++;
        }
    }

}
