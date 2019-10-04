/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musiteka;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.*;

/**
 *
 * @author valer
 */
public class Artista {

    private String nombre;
    private String tipo;
    private String pais;

    public Artista(String nombre, String tipo, String pais) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.pais = pais;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPais() {
        return pais;
    }

    ///
    public static Artista[] artistas;
    public static Document dXML;
    public static EscuchadorSelección escuchadorS;
    public static String rutaFotos;

    public void mostrarFoto(JPanel pnl){
        String nombreArchivo =  rutaFotos + nombre+ ".jpg";
  
        Util.mostrarImagen(pnl, nombreArchivo);
    }
    
    public static void ObtenerArtistas(Document dXML) {
        //documento existe
        if (dXML != null) {
            NodeList nlArtistas = dXML.getElementsByTagName("Artista");
            artistas = new Artista[nlArtistas.getLength()];
            for (int i = 0; i < nlArtistas.getLength(); i++) {
                if (nlArtistas.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element nodo = (Element) nlArtistas.item(i);
                    NodeList nl = nodo.getElementsByTagName("Nombre");
                    String nombre = nl.item(0).getTextContent();

                    nl = nodo.getElementsByTagName("Tipo");
                    String tipo = nl.item(0).getTextContent();

                    nl = nodo.getElementsByTagName("Pais");
                    String pais = nl.item(0).getTextContent();

                    artistas[i] = new Artista(nombre, tipo, pais);
                }
            }
        } else {
            System.err.println("No existe");
        }
    }

    public static void mostrar(final JTable tbl, final JPanel pnl) {
        
        String[] encabezados = new String[]{"Nombre", "Tipo", "Pais"};
        //Contar las regiones del pais
        String[][] datos = new String[artistas.length][3];

        for (int i = 0; i < artistas.length; i++) {

            datos[i][0] = artistas[i].getNombre();
            datos[i][1] = artistas[i].getTipo();
            datos[i][2] = artistas[i].getPais();

        }
        tbl.setModel(new DefaultTableModel(datos, encabezados));

        escuchadorS = new EscuchadorSelección(tbl,pnl);
        
        tbl.getSelectionModel().addListSelectionListener(escuchadorS);

    }

}
