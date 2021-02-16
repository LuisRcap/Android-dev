package com.example.ordenamientopormezcla;

public class Mezcla{

    public void ordenar(int arr[], int izq, int der)
    {
        if(izq < der)
        {
            int med = (izq + der) / 2;
            ordenar(arr, izq, med);
            ordenar(arr, med + 1, der);

            mezclar(arr, izq, med, der);
        }

    }

    public void mezclar(int arr[], int izq, int med, int der)
    {
        int n1 = med - izq + 1;
        int n2 = der - med;

        int izqArr[] = new int[n1];
        int derArr[] = new int[n2];

        for(int i=0; i<n1; i++)
        {
            izqArr[i] = arr[izq+i];
        }

        for(int i=0; i<n2; i++)
        {
            derArr[i] = arr[med+i+1];
        }

        int i = 0, j = 0, k = izq;

        while(i<n1 && j<n2)
        {
            if(izqArr[i] <= derArr[j])
            {
                arr[k] = izqArr[i];
                i++;
            }
            else
            {
                arr[k] = derArr[j];
                j++;
            }
            k++;
        }

        while(i < n1)
        {
            arr[k] = izqArr[i];
            i++;
            k++;
        }

        while(j < n2)
        {
            arr[k] = derArr[j];
            j++;
            k++;
        }
    }

    public int[] darArreglo(int arr[])
    {
        return arr;
    }
}
