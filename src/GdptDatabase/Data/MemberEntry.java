/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GdptDatabase.Data;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Markcuz
 */
public class MemberEntry {
        public SimpleStringProperty firstName = new SimpleStringProperty();
        public SimpleStringProperty lastName = new SimpleStringProperty();
        public SimpleStringProperty englishName = new SimpleStringProperty();
        public SimpleStringProperty phapDanh = new SimpleStringProperty();
        public SimpleStringProperty contact = new SimpleStringProperty();
        public Member me;
        
        
        public MemberEntry(String fName, String lName, String eName, String pDanh, String cont, Member me) { 
            this.firstName.set(fName);
            this.lastName.set(lName);
            this.englishName.set(eName);
            this.phapDanh.set(pDanh);
            this.contact.set(cont);
            this.me = me;
        }
        
        public String getFirstName() {
            return firstName.get();
        }
        
        public String getLastName() {
            return lastName.get();
        }
        
        public String getEnglishName() {
            return englishName.get();
        }
        
        public String getPhapDanh() {
            return phapDanh.get();
        }
        
        public String getContact() {
            return contact.get();
        }
        
        public Member getMember() {
            return me;
        }
    }
