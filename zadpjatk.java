import java.util.Arrays;
public class NumberComparasion {




    static String przytnijDlugosc(String s, int n) {

        if (s.length() - n > 0) {

            return s.substring(0, n);

        } 

        return s;

    }



    private static int[] odejmij(int[] T1, int[] T2, int podstawa) {



        int przeniesienie = 0;



        int[] wynik = new int[T1.length];



        for (int i = T1.length - 1; i >= 0; i--) {



            wynik[i] = T1[i] - T2[i] + przeniesienie;



            if (i > 0) {

                if (wynik[i] < 0) {

                    wynik[i] += podstawa;

                    przeniesienie = -1;

                } else {

                    przeniesienie = 0;

                }

            }

        }



        return wynik;

    }



    static int policzJedynki(String s) {



        int licznik = 0;



        for (char c : s.toCharArray()) {



            if (c == '1') {

                licznik++;

            }

        }



        return licznik;

    }



    static boolean pierwszaTabWieksza(int[] tab1, int[] tab2) {

        int n = tab1.length;

        for (int i = 0; i < n; i++) {

            if (tab1[i] != tab2[i]) {

                return tab1[i] > tab2[i];

            }

        }

        return true;

    }



    public static void main(String[] args) {

       

        Scanner sc = new Scanner();



        int k = sc.nextInt();

        int podstawa = (int) Math.pow(2, k);



        int n1 = sc.nextInt();



        int[] tabA = new int[n1];



        for (int i = 0; i < n1; i++) {



            tabA[i] = sc.nextInt();

        }



        int n2 = sc.nextInt();

        int[] tabB = new int[n2];



        for (int i = 0; i < n2; i++) {



            tabB[i] = sc.nextInt();

        }



        if (n1 > n2) {



            int[] tabB2 = new int[n1];



            int d = n1 - n2;



            for (int i = 0; i < d; i++) {



                tabB2[i] = 0;

            }



            System.arraycopy(tabB, 0, tabB2, d, n2);



            tabB = tabB2;

        } else if (n2 > n1) {



            int[] tabA2 = new int[n2];



            int d = n2 - n1;



            for (int i = 0; i < d; i++) {



                tabA2[i] = 0;

            }



            System.arraycopy(tabA, 0, tabA2, d, n1);



            tabA = tabA2;

        }



        int[] tabC = odejmij(tabA, tabB, podstawa);

        int[] tabD = odejmij(tabB, tabA, podstawa);

       

        int p = 0;

       

        int n = Math.max(n1, n2);

       

        while (p < n - 1 && tabC[p] == 0 && tabD[p] == 0) {

            p++;

        }

       

       

       

        tabC = Arrays.copyOfRange(tabC, p, tabC.length);

        tabD = Arrays.copyOfRange(tabD, p, tabD.length);

       

       

        if (tabC.length == 1 && tabC[0] == 0) {

            System.out.println("2 0");

            System.out.println("1 0");

            return;

        }



        boolean Awieksza = pierwszaTabWieksza(tabA, tabB);



        int C_ile1 = 0;

        int C_ile0 = 0;



        int D_ile1 = 0;

        int D_ile0 = 0;



        if (Awieksza) {



            C_ile0 = 1;

        } else {



            D_ile0 = 1;

        }



        for (int i = 0; i < tabC.length; i++) {



            String strC, strD;



            if (i > 0) {



                strC = przytnijDlugosc(Integer.toBinaryString(tabC[i]), k);

                strD = przytnijDlugosc(Integer.toBinaryString(tabD[i]), k);

            } else {

               

               

                strC = Integer.toBinaryString(tabC[i]);

                strD = Integer.toBinaryString(tabD[i]);

            }

           

            int c1 = policzJedynki(strC);

            int d1 = policzJedynki(strD);



            C_ile1 += c1;

            D_ile1 += d1;



            if (i > 0) {

                C_ile0 += k - c1;

                D_ile0 += k - d1;

            } else {

                C_ile0 += strC.length() - c1;

                D_ile0 += strD.length() - d1;

            }

        }

2,25

        if (Awieksza) {



            D_ile1 -= ((D_ile0 + D_ile1) - (C_ile0 + C_ile1));

        } else {

            C_ile1 -= ((C_ile0 + C_ile1) - (D_ile0 + D_ile1));

        }



        System.out.println(C_ile0 + " " + C_ile1);

        System.out.println(D_ile0 + " " + D_ile1);

    }  

}

/*Niech A oraz B będą dwoma liczbami naturalnymi zapisanymi w systemie pozycyjnym o podstawie 2^k. Dalej liczby Coraz D są postaci odpowiednio:

 - C=A-B,

 - D=B-A.

Podaj liczbę symboli 0 oraz 1 niezbędnych do zakodowania liczb C, D w zapisach binarnych znak moduł oraz uzupełnień do 2 tak, że:

 - jeżeli A>=B, to liczba C kodowana jest w reprezentacji znak moduł, a liczba D kodowana jest w reprezentacji uzupełnień do 2,

 - jeżeli A<B, to liczba C kodowana jest w reprezentacji uzupełnień do 2, a liczba D kodowana jest w reprezentacji znak moduł,

jednocześnie zakładając redukcję długości otrzymanych słów do możliwego minimum (uwzględniając bit odpowiedzialny za zakodowanie znaku liczby).

 

Uwag! W przypadku kodowania liczby 0 w zapisie znak moduł za obowiązujący uznajemy jedynie wariant zapisu tej liczby w sposób „dodatni”, tj. poprzedzony 0.

 

WEJŚCIE

Wiersz zawierający liczbę k zakończony znakiem nowej linii (kod ASCII 10). Dalej wiersz definiujący liczbę A postaci:

 - liczba określająca długość pozycyjną (liczbę „cyfr”) liczby A zakończona znakiem odstępu (kod ASCII 32),

 - ciąg kolejnych wartości pozycyjnych („cyfr”) liczby A oddzielonych znakiem odstępu,

 - znak nowej linii,

oraz wiersze definiujący liczbę B postaci:

 - liczba określająca długość pozycyjną liczby B zakończona znakiem odstępu,

 - ciąg kolejnych wartości pozycyjnych liczby B oddzielonych znakiem odstępu,

 - znak nowej linii.

 

WYJŚCIE

Wiersz zawierający dwie liczby będące rozwiązaniem postawionego problemu oddzielone znakiem odstępu i zakończony znakiem nowej linii, gdzie:

 - pierwsza liczba określa liczbę symboli 0 w wybranej reprezentacji liczby C,

 - druga liczba określa liczbę symboli 1 w wybranej reprezentacji liczby C,

oraz wiersz zawierający dwie liczby będące rozwiązaniem postawionego problemu oddzielone znakiem odstępu i zakończony znakiem nowej linii, gdzie:

 - pierwsza liczba określa liczbę symboli 0 w wybranej reprezentacji liczby D,

 - druga liczba określa liczbę symboli 1 w wybranej reprezentacji liczby D.

 

OGRANICZENIA

Wartość liczby k dodatnia i ograniczona od góry przez 30. Długości pozycyjne liczby A oraz B zawarte w przedziale [1,10^7].

 

LIMITY

Zależy nam na możliwe szybkim rozwiązaniu przy jednoczesnej minimalizacji użycia zasobów pamięciowych. J

 

PRZYKŁAD 1

wejście:

4
2 6 15
2 2 7

wyjście:

6 2


4 4

/* KOMENTARZ DO ROZWIĄZANIA







Zgodnie z danymi wejściowymi k=4, tym samym podstawa systemu operacyjnego ustalona dla liczb podanych na wejściu to 2^4=16. Stąd:







 - liczba A jest liczbą o długości pozycyjnej 2 w systemie o podstawie 16,




 - liczba B jest liczbą o długości pozycyjnej 2 w systemie o podstawie 16,







kolejno:






 - liczba A = 6 15 = 6*16^1 + 15*16^0 = 96 + 15 = 111 w systemie decymalnym,




 - liczba B = 2 7 = 2*16^1 + 7*16^0 = 32 + 7 = 39 w systemie decymalnym.







Ponieważ A>=B, to interesuje nas zapis odpowiednio:







 - liczby C = A – B = 111 – 39 = 72 w reprezentacji znak moduł,




 - liczby D = B – A = 39 – 111 = -72 w reprezentacji uzupełnień do 2.







Dalej:







 - liczba C = 72 w reprezentacji znak moduł zredukowanej do słowa o minimalnej możliwej długości to odpowiednio 01001000, tj. 6 symboli 0 oraz 2 symbole 1,




 - liczba D = -72 w reprezentacji uzupełnień do 2 zredukowanej do słowa o minimalnej możliwej długości to odpowiednio 10111000, tj. 4 symbole 0 oraz 4 symbole 1.







Stąd odpowiedź stanowi czwórka liczb:




6 2




4 4 
 

PRZYKŁAD 2

wejście:

3


4 2 3 0 0


5 7 6 2 0 7

wyjście:

10 6


5 11

 

PRZYKŁAD 3

wejście:

8


20 199 84 173 154 135 138 91 192 190 32 61 185 82 23 242 190 245 237 56 182


16 119 200 157 236 208 228 83 241 77 250 163 248 35 164 218 29

wyjście:

79 82


81 80*/

