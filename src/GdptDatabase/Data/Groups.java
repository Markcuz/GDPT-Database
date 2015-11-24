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
public class Groups {
    public enum Doan {
        OVNam("OV Nam", "OVNam"), OVNu("OV Nu", "OVNu"), TNam("Thieu Nam", "TNam"), TNu("Thieu Nu", "TNu"), NThanh("Nganh Thanh", "NThanh"), Htr("Huynh Truong", "Htr");
        
        public String label;
        public String type;

        Doan(String label, String type) {
            this.label = label;
            this.type = type;
        }

        @Override
        public String toString() {
            return label;
        }
        
        public String toType() {
            return type;
        }

    }

    public enum VNClass {
       
        lop1("Lop 1", "lop1"), lop2("Lop 2", "lop2"), lop3("Lop 3", "lop3"), lop4("Lop 4", "lop4"), lop5("Lop 5", "lop5"), NONE("None", "NONE");
        
        public String label;
        public String type;

        VNClass(String label, String type) {
            this.label = label;
            this.type = type;
        }

        @Override
        public String toString() {
            return label;
        }
        
        public String toType() {
            return type;
        }
    }

    public enum PPClass {
        
        senNon("Sen Non", "senNon"), moMat("Mo Mat", "moMat"), canhMem("Canh Mem", "canhMem"), chanCung("Chan Cung", "chanCung"), tungBay("Tung Bay", "tungBay"), 
        huongThien("Huong Thien", "huongThien"), soThien("So Thien", "soThien"), trungThien("Trung Thien", "trungThien"), chanhThien("Chanh Thien", "chanhThien"), 
        bacHoa("Bac Hoa", "bacHoa"), bacTruc("Bac Truc", "bacTruc"), 
        bacKien("Bac Kien", "bacKien"), bacTri("Bac Tri", "bacTri"), bacDinh("Bac Dinh", "bacDinh"), bacLuc("Bac Luc", "bacLuc"), NONE("None", "NONE");
        
        public String label;
        public String type;

        PPClass(String label, String type) {
            this.label = label;
            this.type = type;
        }

        @Override
        public String toString() {
            return label;
        }
        
        public String toType() {
            return type;
        }
        
    }   

    public enum Status {
        
        Active("Active", "Active"), Probation("Probation", "Probation"), NonActive("Non Active", "NonActive");
        
        public String label;
        public String type;

        Status(String label, String type) {
            this.label = label;
            this.type = type;
        }

        @Override
        public String toString() {
            return label;
        }
        
        public String toType() {
            return type;
        }
    
    }
}
