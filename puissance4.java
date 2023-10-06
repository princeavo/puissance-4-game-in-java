
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class puissance4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] tab = new char[6][7] /*, tab1 = new char[6][7]*/;
        int col, prince;
        String ga = new String(" ");
        boolean sm, vr = false;
        String ma = new String(" ");
        do {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    tab[i][j] = ' ';
                }
            }
            afficher(tab);
            do {
                do {
                    do {
                        do {
                            col = 0;
                            System.out.println("Entrez la colonne :");
                            String sa = sc.next().trim();
                            if (sa.equalsIgnoreCase("s")) {
                                ma = "true";
                            } else if (sa.equalsIgnoreCase("c")) {
                                ma = "false";
                            } else {
                                try {
                                    col = Integer.parseInt(sa);
                                    ma = "vrai";
                                } catch (NumberFormatException e) {
                                    System.err.println("Colonne invalide ");
                                    ma = "faux";
                                    col = 0;
                                }
                            }
                            if (ma.equals("true") || ma.equals("false")) {
                                break;
                            }
                        } while ((col < 1) || (col > 7));
                        if (ma.equals("true") || ma.equals("false")) {
                            break;
                        }
                        sm = (Integer.parseInt(possible(tab, col - 1, 1, 'O')[0]) == 0);
                        System.out.println(sm);
                        if (sm) {
                            System.err.println("Colonne pleine !");
                        }
                    } while (sm);
                    /* lecture(tab);*/
                    if (ma == "vrai") {
                        ga = "pa";
                        tab[Integer.parseInt(possible(tab, col - 1, 1, 'O')[2])][col - 1] = 'O';
                    } else {
                        if (ma == "true") {
                            System.out.println("Essai d'enregistrement dans un fichier\nEntrez le nom du fichier ");
                            File file
                                    = new File(
                                            ""
                                            + sc.next()
                                            + ".txt"
                                    );
                            try {
                                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                                for (int i = 0; i < 6; i++) {
                                    for (int j = 0; j < 7; j++) {
                                        char q = tab[i][j];
                                        bw.write(q);
                                        bw.newLine();
                                    }
                                }
                                bw.close();
                            } catch (IOException e) {
                                System.err.println("Impossible d'enregistrer!");
                                ga = "true";
                            }
                        } else {
                            System.out.println("Essai d'ouverture du fichier\nEntrez le nom du fichier :");
                            File file
                                    = new File(
                                            ""
                                            + sc.next()
                                            + ".txt"
                                    );
                            if (!file.exists()) {
                                System.err.println("Le fichier n'existe pas. Veuillez continuer le jeu ");
                                ga = "true";
                            } else {
                                try {
                                    BufferedReader br = new BufferedReader(new FileReader(file));
                                    for (int i = 0; i < 6; i++) {
                                        for (int j = 0; j < 7; j++) {
                                            tab[i][j] = br.readLine().charAt(0);
                                        }
                                    }
                                    br.close();
                                    afficher(tab);
                                } catch (IOException e) {
                                    System.err.println("Impossible de lire le contenu du fichier !");
                                    ga = "true";
                                    System.err.println("Veillez continuer le jeu ");
                                }
                            }
                        }
                    }
                } while ((ma == "false") || (ma == "true" && ga == "true"));
                if (ma == "true" && ga != "true") {
                    break;
                }
                afficher(tab);
                if (gagne(tab, 'O') == false) {
                    if (gain(tab, 'O', 0, 3) == 0) {
                        if (gain(tab, 'X', 0, 3) == 0) {
                            if (gain(tab, 'X', 0, 2) == 0) {
                                if (gain(tab, 'O', 0, 2) == 0) {
                                    // System.out.println("Désolé");
                                    gain(tab, 'O', 0, 1);
                                    tab = pro(tab);
                                    afficher(tab);
                                } else {
                                    // System.out.println("J'ai trouvé 2O");
                                    tab = pro(tab);
                                    afficher(tab);
                                }
                            } else { // System.out.println("J'ai trouvé 2X");
                                tab = pro(tab);
                                afficher(tab);
                            }
                        } else { // System.out.println("J'ai trouvé 3X");
                            tab = pro(tab);
                            afficher(tab);
                        }
                    } else { // System.out.println("J'ai trouvé 3O");
                        tab = pro(tab);
                        afficher(tab);
                    }
                }
                for (int go1 = 0; go1 < 6; go1++) {
                    for (int go2 = 0; go2 < 7; go2++) {
                        if (tab[go1][go2] != ' ') {
                            vr = true;
                        }
                    }
                }
            } while ((gagne(tab, 'O') == false) && (gagne(tab, 'X') == false) && (vr == true));
            if (ma != "true" || ga == "true") {
                if (gagne(tab, 'O') == true) {
                    System.out.println("Vous avez gagn茅 馃馃馃");
                } else if (gagne(tab, 'X') == true) {
                    System.out.println("Vous avez perdu 馃様馃様馃様");
                } else {
                    System.out.println("Match nul 鉁ㄢ湪鉁�");
                }
            }
            while (true) {
                /*   System.out.print("\033[H\033[2J");
     System.out.flush();*/
                System.out.println("Voulez vous rejouer? 1-oui 2-non ");
                String das = sc.next();
                try {
                    prince = Integer.parseInt(das);
                    if (prince != 1 && prince != 2) {
                        prince = 0;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Entrée invalide !");
                    prince = 0;
                }
                if (prince == 1 || prince == 2) {
                    break;
                }
            }
        } while (prince == 1);
        sc.close();
    }

    private static void afficher(char[][] tab) {
        char[][] tab1 = new char[13][15];
        int a = 1;
        System.out.println("    1     2     3     4     5     6     7   ");
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 15; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        tab1[i][j] = '+';
                    } else {
                        tab1[i][j] = '-';
                    }
                } else {
                    if (j % 2 == 0) {
                        tab1[i][j] = '|';
                    } else {
                        tab1[i][j] = tab[i / 2][j / 2];
                    }
                }
                String c = "";
                if ((j == 0) && (i % 2 == 1)) {
                    c = "" + a + "";
                    a++;
                } else {
                    c = " ";
                }
                System.out.print(c + " " + tab1[i][j]);
            }
            System.out.println();
        }
    }

    private static String[] possible(char[][] tab, int col, int lig, char c) {
        String[] tab1 = {" ", " ", "false"};
        int retour = 10, g = 0, h = 0;
        for (int i = 5; i > -1; i--) {
            retour = (tab[i][col] != ' ') ? i : retour;
            if ((tab[i][col] == ' ') && (g == 0)) {
                h = i;
                g++;
            }
            tab1[0] = retour + "";
        }
        if (c == 'X') {
            if (h == lig) { // ||(h>lig)||((h<lig)&&(h!=(lig-1)))){
                tab1[1] = "true";
            } /*else if (h > lig) {
          if (lig != h - 1) tab1[1] = "true";
        } */ else {
                tab1[1] = "false";
            }
        }
        tab1[2] = h + "";
        return tab1;
    }

    private static int rep(char[][] tab, int i, int j, int a) {
        if ((i > -1) && (i < 6) && (j < 7) && (j > -1)) {
            if (tab[i][j] == ' ') {
                if ((a != 1) && (possible(tab, j, i, 'X')[1].equals("true"))) {
                    a = 1;
                    tab[Integer.parseInt(possible(tab, j, i, 'X')[2])][j] = 'X';
                }
            }
        }
        pro(tab);
        return a;
    }

    private static boolean gagne(char[][] tab, char c) {
        boolean pour = false;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if ((j < 4) && (pour == false)) {
                    pour
                            = ((tab[i][j] == tab[i][j + 1])
                            && (tab[i][j + 1] == tab[i][j + 2])
                            && (tab[i][j + 2] == tab[i][j + 3])
                            && (tab[i][j] == c));
                }
                if ((i < 3) && (pour == false)) {
                    pour
                            = ((tab[i][j] == tab[i + 1][j])
                            && (tab[i + 1][j] == tab[i + 2][j])
                            && (tab[i + 2][j] == tab[i + 3][j])
                            && (tab[i][j] == c));
                }
                if ((i < 3) && (j < 4) && (pour == false)) {
                    pour
                            = ((tab[i][j] == tab[i + 1][j + 1])
                            && (tab[i + 1][j + 1] == tab[i + 2][j + 2])
                            && (tab[i + 2][j + 2] == tab[i + 3][j + 3])
                            && (tab[i][j] == c));
                }
                if ((i >= 3) && (j < 4) && (pour == false)) {
                    pour
                            = ((tab[i][j] == tab[i - 1][j + 1])
                            && (tab[i - 1][j + 1] == tab[i - 2][j + 2])
                            && (tab[i - 2][j + 2] == tab[i - 3][j + 3])
                            && (tab[i][j] == c));
                }
            }
        }
        return pour;
    }

    private static char[][] pro(char[][] tab) {
        return tab;
    }

    private static int gain(char[][] tab, char c, int a, int b) {

        boolean pour1, pour2, pour3, pour4, pour5, pour6, pour7, pour8;
        int p = -1, m = -1;
        pour1 = pour2 = pour3 = pour4 = pour5 = pour6 = pour7 = pour8 = false;
        if (a == 0) {
            if (b == 3) {
                //  System.out.println("b==3 et a=" + a);
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++) {
                        if (j < 4) {
                            pour1
                                    = ((tab[i][j] == tab[i][j + 1])
                                    && (tab[i][j] == c)
                                    && (tab[i][j] == tab[i][j + 3]));
                            pour2
                                    = ((tab[i][j] == tab[i][j + 2])
                                    && (tab[i][j] == tab[i][j + 3])
                                    && (tab[i][j] == c));
                        }

                        pour4
                                = (j < 5)
                                        ? ((tab[i][j] == tab[i][j + 1])
                                        && (tab[i][j] == tab[i][j + 2])
                                        && (tab[i][j] == c))
                                        : pour4;
                        if (i < 5) {
                            pour5
                                    = (i < 4)
                                            ? ((tab[i][j] == tab[i + 1][j])
                                            && (tab[i][j] == tab[i + 2][j])
                                            && (tab[i][j] == c))
                                            : pour5;
                        }
                        if ((i < 5) && (j < 6)) {
                            pour6
                                    = ((i < 4) && (j < 5))
                                            ? ((tab[i][j] == tab[i + 1][j + 1])
                                            && (tab[i][j] == tab[i + 2][j + 2])
                                            && (tab[i][j] == c))
                                            : pour6;
                        }
                        pour8
                                = ((i > 1) && (j < 5))
                                        ? ((tab[i][j] == tab[i - 1][j + 1])
                                        && (tab[i - 1][j + 1] == tab[i - 2][j + 2])
                                        && (tab[i][j] == c))
                                        : pour8;

                        if (i >= 3 && j < 4) {
                            pour3
                                    = ((tab[i][j] == tab[i - 3][j + 3])
                                    && (tab[i - 2][j + 2] == tab[i][j])
                                    && (tab[i][j] == c));
                            pour7
                                    = ((tab[i][j] == tab[i - 3][j + 3])
                                    && (tab[i - 1][j + 1] == tab[i][j])
                                    && (tab[i][j] == c));
                        }
                        if (pour1) {
                            a = rep(tab, i, j + 2, a);
                            tab = pro(tab);
                        }
                        if (pour2) {
                            a = rep(tab, i, j + 1, a);
                            tab = pro(tab);
                        }
                        if (pour3) {
                            a = rep(tab, i - 1, j + 1, a);
                            tab = pro(tab);
                        }
                        if (pour7) {
                            a = rep(tab, i - 2, j + 2, a);
                            tab = pro(tab);
                        }
                        if (pour4) {
                            // System.out.println("Prince4");
                            p = i;
                            m = j;
                            // System.out.println(a + " " + 4);
                            a = rep(tab, p, m - 1, a);
                            tab = pro(tab);
                            a = rep(tab, p, m + 3, a);
                            tab = pro(tab);
                            // System.out.println(a + " " + 4);
                        }
                        if (pour5) {
                            //  System.out.println("Prince5");
                            // System.out.println(a + " " + 5);
                            p = i;
                            m = j;
                            a = rep(tab, p - 1, m, a);
                            tab = pro(tab);
                            a = rep(tab, p + 3, m, a);
                            tab = pro(tab);
                            // System.out.println(a + " " + 5);
                        }
                        if (pour6) {
                            // System.out.println("Prince6");
                            p = i;
                            m = j;
                            // System.out.println(a + " " + 6);
                            a = rep(tab, p - 1, m - 1, a);
                            tab = pro(tab);
                            a = rep(tab, p + 3, m + 3, a);
                            tab = pro(tab);
                            // System.out.println(a + " " + 6);
                        }
                        if (pour8) {
                            // System.out.println("Prince8");
                            // System.out.println(a + " " + 8);
                            p = i;
                            m = j;
                            a = rep(tab, p + 1, m - 1, a);
                            tab = pro(tab);
                            a = rep(tab, p - 3, m + 3, a);
                            tab = pro(tab);
                            // System.out.println(a + " " + 8);
                        }
                        if (i < 3 && j < 4) {
                            pour5
                                    = ((tab[i][j] == tab[i + 1][j + 1])
                                    && (tab[i][j] == tab[i + 3][j + 3])
                                    && (tab[i][j] == c));
                            pour6
                                    = ((tab[i][j] == tab[i + 2][j + 2])
                                    && (tab[i][j] == tab[i + 3][j + 3])
                                    && (tab[i][j] == c));
                        }
                        if (pour5) {
                            a = rep(tab, i + 2, j + 2, a);
                            tab = pro(tab);
                        }
                        if (pour6) {
                            a = rep(tab, i + 1, j + 1, a);
                            tab = pro(tab);
                        }
                    }
                }
            } else if (b == 2) {
                //  System.out.println("b==2 et a=" + a);
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++) {
                        if (j < 6) {
                            pour1 = ((tab[i][j] == tab[i][j + 1]) && (tab[i][j] == c));
                        }
                        if (i < 5) {
                            pour2 = ((tab[i][j] == tab[i + 1][j]) && (tab[i][j] == c));
                        }
                        if ((i < 5) && (j < 6)) {
                            pour3 = ((tab[i][j] == tab[i + 1][j + 1]) && (tab[i][j] == c));
                        }
                        if ((i > 0) && (j < 6)) {
                            pour7 = ((tab[i][j] == tab[i - 1][j + 1]) && (tab[i][j] == c));
                            pour8
                                    = ((i > 1) && (j < 5))
                                            ? ((tab[i][j] == tab[i - 1][j + 1])
                                            && (tab[i - 1][j + 1] == tab[i - 2][j + 2])
                                            && (tab[i][j] == c))
                                            : pour8;
                        }
                        if (pour7) {
                            // System.out.println("Prince7");
                            p = i;
                            m = j;
                            // System.out.println(a + " " + 7);
                            // System.out.println(p + ".     " + m);
                            a = rep(tab, p + 1, m - 1, a);

                            tab = pro(tab);
                            a = rep(tab, p - 2, m + 2, a);
                            tab = pro(tab);
                            // System.out.println(a + " " + 7);
                        }
                        if (pour3) {
                            //  System.out.println("Prince3");
                            p = i;
                            m = j;
                            // System.out.println(a + " " + 3);
                            a = rep(tab, p - 1, m - 1, a);
                            tab = pro(tab);
                            a = rep(tab, p + 2, m + 2, a);
                            tab = pro(tab);
                            // System.out.println(a + " " + 3);
                        }
                        if (pour2) {
                            // System.out.println("Prince2");
                            p = i;
                            m = j;
                            // System.out.println(a + " " + 2);
                            a = rep(tab, p - 1, m, a);
                            tab = pro(tab);
                            a = rep(tab, p + 2, m, a);
                            tab = pro(tab);
                            //    System.out.println(a + " " + 2);
                        }
                        if (pour1) {
                            // System.out.println("Prince1 " + c);
                            p = i;
                            m = j;
                            //  System.out.println(a + " " + 1);
                            a = rep(tab, p, m - 1, a);
                            tab = pro(tab);
                            a = rep(tab, p, m + 2, a);
                            tab = pro(tab);
                            // System.out.println(a + " " + 1);
                        }
                        pour4 = (j < 5) ? ((tab[i][j] == tab[i][j + 2]) && (tab[i][j] == c)) : pour4;
                        if (pour4) {
                            a = rep(tab, i, j + 1, a);
                            tab = pro(tab);
                        }
                        if (i < 4 && j < 5) {
                            pour5 = ((tab[i][j] == tab[i + 2][j + 2]) && (tab[i][j] == c));
                        }
                        if (pour5) {
                            a = rep(tab, i + 1, j + 1, a);
                            tab = pro(tab);
                        }
                        if (i >= 2 && j < 5) {
                            pour6 = ((tab[i][j] == tab[i - 2][j + 2]) && (tab[i][j] == c));
                        }
                        if (pour6) {
                            a = rep(tab, i - 1, j + 1, a);
                            tab = pro(tab);
                        }
                    }
                }
                // System.out.println("b==2 et a=" + a);
            } else {

                // System.out.println("Boris");
                while (a == 0) {
                    for (int w = 0; w < 6; w++) {
                        for (int x = 0; x < 7; x++) {
                            if ((tab[w][x] == 'O') && (a == 0)) {
                                a = rep(tab, w - 1, x, a);
                                tab = pro(tab);
                                a = rep(tab, w + 1, x, a);
                                tab = pro(tab);
                                a = rep(tab, w, x - 1, a);
                                tab = pro(tab);
                                a = rep(tab, w, x + 1, a);
                                tab = pro(tab);
                            }
                        }
                    }
                }
                // System.out.println(a + " " + 0);
            }
        } // afficher(tab);
        pro(tab);
        return a;
    }

    public static boolean equal(char[][] tab, char[][] tab1) {
        boolean b = true;
        /*  if((tab[0]. length !=tab1[0].length) ||(tab[1]. length!=tab1[1]. length)){
      return false ;
    }else{*/
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (tab[i][j] != tab1[i][j]) {
                    b = b && false;
                }
            }
        }
        System.out.println("egal " + b);
        return b;
        // }
    }
} // un secret 1 2 2 4 3 4 3 2 4
