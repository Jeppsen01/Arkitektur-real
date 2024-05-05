package guifx;

import application.controller.Controller;
import application.model.Company;
import application.model.Customer;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CustomerWindow extends Stage {
    private TextField txfName;

    private ComboBox<Company> cbbCompany;
    private Label lblError;

    private Customer customer;

    public CustomerWindow(String title, Customer customer) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.customer = customer;

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public CustomerWindow(String title) {
        this(title, null);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lblName = new Label("Name");
        pane.add(lblName, 0, 0);

        txfName = new TextField();
        pane.add(txfName, 0, 1);
        txfName.setPrefWidth(200);


        Button btnCancel = new Button("Cancel");
        pane.add(btnCancel, 0, 4);
        GridPane.setHalignment(btnCancel, HPos.LEFT);


        Button btnOK = new Button("OK");
        pane.add(btnOK, 0, 4);
        GridPane.setHalignment(btnOK, HPos.RIGHT);
        btnOK.setOnAction(event -> okAction());

        cbbCompany = new ComboBox<>();
        pane.add(cbbCompany, 0, 3);
        cbbCompany.getItems().addAll(Controller.getCompanies());
        cbbCompany.setDisable(false);


        lblError = new Label();
        pane.add(lblError, 0, 5);
        lblError.setStyle("-fx-text-fill: red");

        this.initControls();
    }

    private void initControls() {
        if (customer != null) {
            txfName.setText(customer.getName());
        } else {
            txfName.clear();
        }
    }

    private void okAction() {
        String name = txfName.getText();

        if (name.length() == 0) {
            lblError.setText("Name is empty");
        } else {
            customer = Controller.createCustomer(name);
            Controller.addCustomerToCompany(customer, cbbCompany.getValue());
        }

        hide();
    }
}
