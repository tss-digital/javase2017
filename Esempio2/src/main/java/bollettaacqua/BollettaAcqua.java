
package bollettaacqua;

import javax.swing.JOptionPane;

/**
 *
 * @author tss
 */
public class BollettaAcqua {

    /**
     * @param args the command line arguments
     */
    static double consTotMetri;
    static double consTotEuro;
    static double consEuro;
    static double consMcubi;
    static double consPrec;
    static double consAttuale;
    static double consCortile;
    static String tx_numero;
    static String numInquilini[];
    static int inquilini;
    static String nomiInquilini[];
    static double[] consReale;

    public static void main(String[] args) {
        // TODO code application logic here

        //immetto dei dati, richiesti all'utente 
        tx_numero = JOptionPane.showInputDialog("Quanti metri cubi di consumo ci sono in bolletta?");

        consTotMetri = Double.parseDouble(tx_numero);

        tx_numero = JOptionPane.showInputDialog("Quanti Euro di consumo ci sono in bolletta?");

        consTotEuro = Double.parseDouble(tx_numero);

        consEuro = consTotEuro / consTotMetri;

        JOptionPane.showMessageDialog(null, "Un metro cubo vale " + consEuro + " Euro");

        tx_numero = JOptionPane.showInputDialog("Quanti sono i condomini?");

        inquilini = Integer.parseInt(tx_numero);

        numInquilini = new String[inquilini];

        for (int i = 0; i < numInquilini.length; i++) {

            //Quanti sono gli inquilini e loro nomi.
            //Quanto hanno consumato Cinzia, Lina, Nonna, Rosa, Marco nella bolletta precedente in metri cubi
            //quanto hanno consumato nella bolletta attuale, fai conteggio per consumo di ognuno.
            //chiedere il consumo per ogni inquilino
            //consMcubi = somma consumi dichiarati dagli inquilini letti 
            //avendo trovato il valore unitario di un metro cubo lo moltiplico per i consumi
            //di ogni inquilino e trovo il costo a testa.
            //rimarranno sicuramente dei metri cubi eccedenti, quelli sono da attribuire al consumo esterno
            //del fabbricato chiamato "cortile", i mcubi del cortile verranno suddivisi per persona in bolletta
            //e aggiunti al consumo.
            //consCortile = consumo tot in bolletta - consumo totale dichiaro
            //consCortile/numero degli inquilini=aggiunta cortile per ogni inquilino
            nomiInquilini = new String [inquilini];  
            
            nomiInquilini[i] = JOptionPane.showInputDialog("Dammi i nomi decondomini:");

            tx_numero = JOptionPane.showInputDialog("Dammi la lettura precedente del contatore");
            consPrec = Double.parseDouble(tx_numero);

            tx_numero = JOptionPane.showInputDialog("Dammi la lettura attuale del contatore");
            consAttuale = Double.parseDouble(tx_numero);

            double[] consReale = new double[numInquilini.length];

            consReale[i] = consAttuale - consPrec;

        }
        for (int i = 0; i < numInquilini.length; i++) {
            String foglio;
            foglio = "Ci sono " + numInquilini + "inquilini\n";
            foglio += nomiInquilini[i] + " ha consumato" + consReale[i] + " metri cubi\n";
            

            JOptionPane.showMessageDialog(null, foglio);

        }

    }

}
