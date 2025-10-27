package pro.Entidad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import pro.Informes.Fila;
import pro.Utilidades.ConexionSQL;

public class InformePagos {
    String fecha;
    String titulo;
    int idInforme;
    

    ArrayList<Fila> entradas;

    public InformePagos(String titulo) {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.fecha = fechaActual.format(formatoFecha);
        System.out.println(fecha);
        this.titulo = titulo;
        

        try {
            ResultSet resultado;
            resultado = ConexionSQL.consultar("SELECT MAX(id_informe) FROM INFORME;");
            resultado.next();
            // Obtiene el numero de informe
            idInforme = resultado.getInt(1)+1;
        } catch (SQLException e) {
            System.out.println("Error a obtener el id de informe");
        }
        entradas = new ArrayList<>();
    }

    public void agregarEntrada(Fila e) {
        entradas.add(e);
    }

    public ArrayList<Fila> obtenerEntradas(){
        return entradas;
    }

    public ArrayList<Fila> getEntradas() {
        return entradas;
    }

    public void setEntradas(ArrayList<Fila> entradas) {
        this.entradas = entradas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public int getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(int idInforme) {
        this.idInforme = idInforme;
    }

}
