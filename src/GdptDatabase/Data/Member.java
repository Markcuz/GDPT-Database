/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GdptDatabase.Data;

/**
 *
 * @author Markcuz
 */
public class Member {
        public final String firstName;
        public final String lastName;
        public final String englishName;
        public final String phapDanh;
        public final String address;
        public final String phoneNumber;
        public final String DOB;
        public final Groups.Doan nganh;
        public final Groups.VNClass vn;
        public final Groups.PPClass pp;
        public final String school;
        public final String year;
        public final Groups.Status status;  

        public Member(String fName, String lName, String eName, String phapDanh, String add, String ph, String dob, Groups.Doan doan, Groups.VNClass vn, Groups.PPClass pp, String school, String year, Groups.Status stat) {
            this.firstName = fName;
            this.lastName = lName;
            this.englishName = eName;
            this.phapDanh = phapDanh;
            this.address = add;
            this.phoneNumber = ph;
            this.DOB = dob;
            this.nganh = doan;
            this.vn = vn;
            this.pp = pp;
            this.school = school;
            this.year = year;
            this.status = stat;
        }
    }
