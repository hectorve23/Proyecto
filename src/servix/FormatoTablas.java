/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author albap
 */

public class FormatoTablas {
    public static class FormatoInteger extends DefaultTableCellRenderer {
        NumberFormat formatoNum = NumberFormat.getIntegerInstance();

        public void setValue(Object value){
            if (value instanceof Number){
                value = formatoNum.format(value);
            }
            super.setValue(value);
        }
    }
    
    public static class FormatoFecha extends DefaultTableCellRenderer {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        
         public void setValue(Object value){
            if (value instanceof Number){
                value = formatoFecha.format(value);
            }
            super.setValue(value);
        }
    }
}
