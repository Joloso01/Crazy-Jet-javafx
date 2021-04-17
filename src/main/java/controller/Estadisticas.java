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

    //se crean las columnas de la table view
    TableColumn<Jugador, String> jugador = new TableColumn<Jugador, String>("nom");
    TableColumn<Jugador, Integer> puntos = new TableColumn<>("puntuacion");
    TableColumn<Jugador, Integer> tiempo = new TableColumn<Jugador, Integer>("tiempo");
    TableColumn<Jugador, String > fecha = new TableColumn<>("fecha");

    public Estadisticas() {
        try {
            jugadorTableView = FXMLLoader.load(getClass().getResource("/fxml/screens/estadisticas.fxml"));
            puntuacionesLista = jugadorTableView.getItems();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //se inicializan los valores de las columnas creadas antes
        jugador.setCellValueFactory(new PropertyValueFactory<>("nom"));
        puntos.setCellValueFactory(new PropertyValueFactory<>("puntuacion"));
        tiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }

    //este metodo sirve para crear un nuevo jugador a la lista
    public void addJugador(String nombre){
        puntuacionesLista.add(new Jugador(nombre));

    }

    //este metodo sirve para buscar el jugador al cual se le quiere añadir las estadisticas de la partida
    //lo busca y le pone la puntuacion, tiempo y fecha de la partida
    //en caso de que no lo encuentre lo crea y le añade las estadisticas
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
            jugador1.setFecha(LocalDate.now().toString());
        }

    }

    //este metodo sirve para saber si el jugador a buscar esta en la lista
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
