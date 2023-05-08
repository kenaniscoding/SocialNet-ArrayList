package com.example.socialnet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Controller {

    public TextField nameField;
    public Label statusLabel;
    public TextField statusChangeLabel;
    public TextField pictureLabel;
    public TextField friendLabel;
    public TextField unfriendLabel;
    public TextField quoteLabel;
    public ImageView pictureView;
    public ListView listView;
    //ADDED
    public Label name;
    public Label quote;
    public Label status;
    public ProfileData profileData;
    private ArrayList<ProfileData> profileList= new ArrayList<>();
    public ArrayList<String> profileNameList = new ArrayList<>();
    ObservableList<String> friendList;
    //IF ERROR THEN REMOVE TEXT NOTES BELOW
    //TODO TOP RIGHT HARD
    @FXML
    private  void initialize(){
        friendList = FXCollections.observableArrayList();
        listView.setItems(friendList);
    }
    public void handleAdd(ActionEvent actionEvent) {
        if (!nameField.getText().trim().isEmpty()) {
            statusLabel.setText(nameField.getText() + " added");
            //ADD PROFILE/NAME
            profileData = new ProfileData();
            profileData.setName(nameField.getText());
            //label name
            name.setText(nameField.getText());
            profileList.add(profileData);
            profileNameList.add(nameField.getText());
            System.out.println("ADDED PROFILE "+nameField.getText());
            friendList.clear();
            statusChangeLabel.setText("");
            pictureLabel.setText("");
            friendLabel.setText("");
            unfriendLabel.setText("");
            quoteLabel.setText("");
            pictureView.setImage(null);
            status.setText("");
            quote.setText("");
            //REMOVES TEXT
            nameField.setText("");
        }
    }

    public void handleDelete(ActionEvent actionEvent) {
        if (!nameField.getText().trim().isEmpty()) {
            statusLabel.setText(nameField.getText() + " deleted");
            profileNameList.remove(nameField.getText());
            System.out.println("REMOVED PROFILE "+nameField.getText());
            //REMOVES TEXT
            nameField.setText("");
        }
    }

    public void handleLookup(ActionEvent actionEvent) {
        if (!nameField.getText().trim().isEmpty()) {
            statusLabel.setText(nameField.getText() + " lookup");
            //SEARCH FOR PROFILE/NAME
//            System.out.println(profileList.toString());
            if(profileNameList.contains(nameField.getText())){
                System.out.println("PROFILE PRESENT");
                //get the index of the arraylist profile with the data type ProfileData
                int index=profileNameList.indexOf(nameField.getText());
                //change name, quote, status, and picture
                name.setText(nameField.getText());
                quote.setText(profileList.get(index).getQuote());
                status.setText(profileList.get(index).getStatus());
                //TODO MAKE SURE TO ALWAYS PUT THESE 4
                pictureView.setImage(profileList.get(index).getImage());
                //clear the previous profile's friend's list
                //TODO CHECK THIS
                friendList.clear();
                for(int i=0; i<profileList.get(index).getFriendSize(); i++){
                    friendList.add(profileList.get(index).getFriend().get(i));
                }
                //REMOVES TEXT
                nameField.setText("");
            } else {
                System.out.println("PROFILE DOESN'T EXIST");
                //REMOVES TEXT
                nameField.setText("");
            }
        }
    }
    //TODO MIDDLE LEFT SIDE WORKS?? not sure if it stores it on each profile
    public void handleStatus(ActionEvent actionEvent) {
        if (!statusChangeLabel.getText().trim().isEmpty()) {
            statusLabel.setText("status changed to " + statusChangeLabel.getText());
            System.out.println("STATUS: "+statusChangeLabel.getText());
            //CHANGE STATUS works
            profileData.setStatus(statusChangeLabel.getText());
            status.setText(statusChangeLabel.getText());
            //REMOVE TEXT
            statusChangeLabel.setText("");
        }
    }

    public void handlePicture(ActionEvent actionEvent) {
        if (!pictureLabel.getText().trim().isEmpty()) {
            //change this?
            pictureView.setImage(new Image(pictureLabel.getText()));
            statusLabel.setText("picture changed to " + pictureLabel.getText());
            //CHANGE PICTURE works
            profileData.setPicture(pictureLabel.getText());
            //REMOVE TEXT
            pictureLabel.setText("");
        }
    }

    public void handleAddFriend(ActionEvent actionEvent) {
        if (!friendLabel.getText().trim().isEmpty()) {
            friendList.add(friendLabel.getText());
            //TODO
            profileData.addFriend(friendLabel.getText());
            statusLabel.setText(friendLabel.getText() + " added as friend");
            //REMOVE TEXT
            friendLabel.setText("");
        }
    }

    public void handleUnfriend(ActionEvent actionEvent) {
        //TODO DOES NOT DELETE WHEN LOOKED
        if (!unfriendLabel.getText().trim().isEmpty()) {
            friendList.remove(unfriendLabel.getText());
            //TODO this line might be the problem
            System.out.println("UNFRIEND: "+unfriendLabel.getText());
            profileData.removeFriend(unfriendLabel.getText());
            int index=profileData.getName().indexOf(profileData.getName());
            profileList.get(index).removeFriend(unfriendLabel.getText());
            statusLabel.setText(unfriendLabel.getText() + " removed as friend");
            //REMOVE TEXT
            unfriendLabel.setText("");
        }
    }

    public void handleQuote(ActionEvent actionEvent) {
        if (!quoteLabel.getText().trim().isEmpty()) {
            statusLabel.setText("Quote changed to " + quoteLabel.getText());
            System.out.println("QUOTE: "+quoteLabel.getText());
            //store and set profileData
            profileData.setQuote(quoteLabel.getText());
            quote.setText(quoteLabel.getText());
            quoteLabel.setText("");
        }
    }
}
