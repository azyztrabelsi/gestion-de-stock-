package com.example.gestion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.events.MouseEvent;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ArticleController implements Initializable {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField textFieldCodeArticle;

    @FXML
    private TextField textFieldLibelle;

    @FXML
    private TextField textFieldQte;

    @FXML
    private TextField textFieldPrixHT;

    @FXML
    private TableColumn<Article, String> colcode;

    @FXML
    private TableColumn<Article, Integer> colid;

    @FXML
    private TableColumn<Article, String> collibelle;

    @FXML
    private TableColumn<Article, Double> colprixht;

    @FXML
    private TableColumn<Article, Integer> colqte;


    @FXML
    private TableView<Article> tableview;

    int id = 0;

    @Override
    public  void initialize(URL url, ResourceBundle resourceBundle){
        showArticle();
        btnSave.setOnAction(this::createArticle);
        btnUpdate.setOnAction(this::updateArticle);
        btnDelete.setOnAction(this::deleteArticle);
        btnClear.setOnAction(this::clearField);

    }

    public ObservableList<Article> getArticle(){
        ObservableList<Article> article = FXCollections.observableArrayList();

        String query = "select * FROM article";
        con = DBConnexion.getCon();
        try {
            st = con.prepareStatement(query);
            rs = st.executeQuery();
            while (rs.next()){
                Article article1 = new Article();
                article1.setId(rs.getInt("id"));
                article1.setCodeArticle(rs.getString("CodeArticle"));
                article1.setLibelle(rs.getString("Libelle"));
                article1.setQte(rs.getInt("Qte"));
                article1.setPrixHt(rs.getDouble("PrixHT"));
                article.add(article1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return article;
    }

    public void showArticle(){
        ObservableList<Article> list = getArticle();
        tableview.setItems(list);
        colid.setCellValueFactory(new PropertyValueFactory<Article, Integer>("id"));
        colcode.setCellValueFactory(new PropertyValueFactory<Article, String>("CodeArticle"));
        collibelle.setCellValueFactory(new PropertyValueFactory<Article, String>("Libelle"));
        colqte.setCellValueFactory(new PropertyValueFactory<Article, Integer>("Qte"));
        colprixht.setCellValueFactory(new PropertyValueFactory<Article, Double>("prixHT"));
    }

    @FXML
    void clearField(ActionEvent event){
        clear();
    }

    @FXML
    void createArticle(ActionEvent event){

        String insert = "insert into article(CodeArticle,Libelle,Qte,PrixHT) values (?,?,?,?)";
        con = DBConnexion.getCon();
        try{
            st = con.prepareStatement(insert);
            st.setString(1,textFieldCodeArticle.getText());
            st.setString(2,textFieldLibelle.getText());
            st.setInt(3,Integer.parseInt(textFieldQte.getText()));
            st.setDouble(4, Double.parseDouble(textFieldPrixHT.getText()));
            st.executeUpdate();
            clear();
            showArticle();
            System.out.println("Article created!");
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }




    @FXML
    void deleteArticle(ActionEvent event){
        String delete = "delete from article where id = ?";
        con = DBConnexion.getCon();
        try{
            st = con.prepareStatement(delete);
            st.setInt(1,id);
            st.executeUpdate();
            showArticle();
            clear();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void updateArticle(ActionEvent event){
        String update = "update article set CodeArticle = ?, Libelle = ?, Qte = ?, PrixHT = ? where id = ?";
        con = DBConnexion.getCon();
        try{
            st = con.prepareStatement(update);
            st.setString(1,textFieldCodeArticle.getText());
            st.setString(2,textFieldLibelle.getText());
            st.setInt(3,Integer.parseInt(textFieldQte.getText()));
            st.setDouble(4, Double.parseDouble(textFieldPrixHT.getText()));
            st.setInt(5,id);
            st.executeUpdate();
            showArticle();
            clear();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


    public void getData(javafx.scene.input.MouseEvent mouseEvent) {
        Article article2 = tableview.getSelectionModel().getSelectedItem();
        id = article2.getId();
        textFieldCodeArticle.setText(article2.getCodeArticle());
        textFieldLibelle.setText(article2.getLibelle());
        textFieldQte.setText(String.valueOf(article2.getQte()));  // Conversion de int en String
        textFieldPrixHT.setText(String.valueOf(article2.getPrixHT()));  // Conversion de double en String
        btnSave.setDisable(true);
        System.out.println("Data fetched!");
    }

    void clear(){
        textFieldCodeArticle.setText(null);
        textFieldLibelle.setText(null);
        textFieldQte.setText(null);
        textFieldPrixHT.setText(null);
        btnSave.setDisable(false);

    }
}
