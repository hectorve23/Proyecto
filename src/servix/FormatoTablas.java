/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servix;

import javax.swing.table.DefaultTableCellRenderer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author albap
 */

public class FormatoTablas {
    public static class FormatoInteger extends DefaultTableCellRenderer {
        NumberFormat formatoNum = NumberFormat.getIntegerInstance();

        public FormatoInteger(){
            setHorizontalAlignment(RIGHT);
        }
        public void setValue(Object value){
            if (value instanceof Number){
                value = formatoNum.format(value);
            }
            super.setValue(value);
        }
    }
    
   public static class FormatoFecha extends DefaultTableCellRenderer {
         SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
        public FormatoFecha(){
            setHorizontalAlignment(CENTER);
        }

        public void setValue(Object value){
            if (value instanceof java.util.Date){
                value = formatoFecha.format(value);
            } else if (value instanceof LocalDateTime) {
                // Convertir LocalDateTime a Date para formatearlo
                LocalDateTime ldt = (LocalDateTime) value;
                Date date = Date.from(ldt.atZone(java.time.ZoneId.systemDefault()).toInstant());
                value = formatoFecha.format(date);
            }
            super.setValue(value);
        }
    }
           
}
