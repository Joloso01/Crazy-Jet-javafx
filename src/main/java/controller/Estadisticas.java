package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Jugador;

import java.io.IOException;
import java.time.LocalDate;

public class Estadisticas {

    private String playerName;
    private TableView<Jugador> jugadorTableView= null;
    private ObservableList<Jugador> puntuacionesLista;
    private boolean encontrado;


    TableColumn<Jugador, String> jugador = new TableColumn<Jugador, String>("nom");
    TableColumn<Jugador, Integer> puntos = new TableColumn<>("puntuacion");
    TableColumn<Jugador, Integer> tiempo = new TableColumn<Jugador, Integer>("tiempo");
    TableColumn<Jugador, String > fecha = new TableColumn<>("fecha");

    public Estadisticas() {
        try {
            jugadorTableView = FXMLLoader.load(getClass().getResource("/fxml/estadisticas.fxml"));
            puntuacionesLista = jugadorTableView.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jugador.setCellValueFactory(new PropertyValueFactory<>("nom"));
        puntos.setCellValueFactory(new PropertyValueFactory<>("puntuacion"));
        tiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }

    public void addJugador(String nombre){
        puntuacionesLista.add(new Jugador(nombre));

    }

    public void statsJugador(String nombre, int tiempo, int puntos){
        Jugador jugador= buscarJugador(nombre);
        if (encontrado){
            jugador.setPuntuacion(puntos);
            jugador.setTiempo(tiempo);
            jugador.setFecha(LocalDate.now().toString());
        }else {
            addJugador(nombre);
            Jugador jugador1 = buscarJugador(nombre);
            jugador1.setTiempo(tiempo);
            jugador1.setPuntuacion(puntos);
            jugador.setFecha(LocalDate.now().toString());
        }
        playerName="";

    }

    public Jugador buscarJugador(String nombre){
        for (Jugador j:puntuacionesLista){
            if (j.getNom().equals(nombre)){
                encontrado = true;
                return j;
            }else {
                encontrado = false;
            }
        }
        return null;
    }

    public ObservableList<Jugador> getPuntuacionesLista() {
        return puntuacionesLista;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
