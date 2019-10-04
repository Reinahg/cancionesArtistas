/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musiteka;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.*;
import static musiteka.Artista.*;

/**
 *
 * @author valer
 */
public class EscuchadorSelección implements ListSelectionListener {

    private JTable tbl;
    private JPanel pnl;

    EscuchadorSelección(JTable tbl, JPanel pnl) {
        this.tbl = tbl;   
        this.pnl = pnl; 
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int fila = e.getFirstIndex();
        Artista a = artistas[fila];
        a.mostrarFoto(pnl);
        Canción.ObtenerCanciones(dXML, a);
        Canción.mostrar(tbl);
    }

}
