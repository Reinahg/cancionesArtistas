/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musiteka;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author valer
 */
public class Canción {

    private String titulo;
    private String duracion;
    private String año;
    private String genero;

    public String getTitulo() {
        return titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getAño() {
        return año;
    }

    public String getGenero() {
        return genero;
    }

    public Canción(String titulo, String duracion, String año, String genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.año = año;
        this.genero = genero;
    }

    //////////////////////
    private static Canción[] canciones;

    public static void ObtenerCanciones(Document dXML, Artista A) {
        //documento existe

        canciones = null;
        if (dXML != null) {
            NodeList nlCancion = dXML.getElementsByTagName("Cancion");
            for (int i = 0; i < nlCancion.getLength(); i++) {
                Element nodoPadre = (Element) nlCancion.item(i).getParentNode();

                NodeList nl = nodoPadre.getElementsByTagName("Nombre");
                String nombre = nl.item(0).getTextContent();

                if (nombre.equals(A.getNombre())) {
                    Element nodo = (Element) nlCancion.item(i);
                    nl = nodo.getElementsByTagName("Titulo");
                    String titulo = nl.item(0).getTextContent();

                    nl = nodo.getElementsByTagName("Duracion");
                    String duracion = nl.item(0).getTextContent();

                    nl = nodo.getElementsByTagName("Año");
                    String año = nl.item(0).getTextContent();

                    nl = nodo.getElementsByTagName("Genero");
                    String genero = nl.item(0).getTextContent();
                    if (canciones == null) {
                        canciones = new Canción[1];
                    } else {
                        canciones = (Canción[]) Util.redimensionar(canciones, canciones.length + 1);
                    }

                    canciones[canciones.length - 1] = new Canción(titulo, duracion, año, genero);

                }

            }

        } else {
            System.err.println("No existe");
        }
    }

    public static void mostrar(JTable tbl) {
        if (canciones != null) {
            
            String[] encabezados = new String[]{"Titulo", "Duracion", "Año", "Genero"};
            //Contar las regiones del pais
            String[][] datos = new String[canciones.length][4];

            for (int i = 0; i < canciones.length; i++) {

                datos[i][0] = canciones[i].getTitulo();
                datos[i][1] = canciones[i].getDuracion();
                datos[i][2] = canciones[i].getAño();
                datos[i][3] = canciones[i].getGenero();
            }
            tbl.setModel(new DefaultTableModel(datos, encabezados));

            tbl.getSelectionModel().removeListSelectionListener(Artista.escuchadorS);
        } else {
            tbl.setModel(new DefaultTableModel());
            //JOptionPane.showMessageDialog(null, "No hay música para mostrar :(");
            //Artista.mostrar(tbl);
            
        }

    }

}
