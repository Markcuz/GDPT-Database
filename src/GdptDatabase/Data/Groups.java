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
        OVNam("OV Nam"), OVNu("OV Nu"), TNam("Thieu Nam"), TNu("Thieu Nu"), NThanh("Nganh Thanh"), Htr("Huynh Truong");
        
        public String label;

        Doan(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public enum VNClass {
        lop1, lop2, lop3, lop4, lop5, NONE
    }

    public enum PPClass {
        senNon, moMat, canhMem, chanCung, tungBay, huongThien, soThien, trungThien, chanhThien, bacHoa, bacTruc, bacKien, bacTri, bacDinh, bacLuc, NONE
    }   

    public enum Status {
        Active, Probation, NonActive
    }
    
}
