/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Pouya Nikbakhsh
 */
public class TableColorCellRenderer extends DefaultTableCellRenderer {
    
        TableCellRenderer render;
        Border b;
        
        public TableColorCellRenderer(TableCellRenderer r, Color top, Color left,Color bottom, Color right){
            render = r;
            
            //It looks funky to have a different color on each side - but this is what you asked
            //You can comment out borders if you want too. (example try commenting out top and left borders)
            b = BorderFactory.createCompoundBorder();
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(2,0,0,0,top));
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,2,0,0,left));
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,2,0,bottom));
            b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0,0,0,2,right));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) { 
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if(column == 4 && row == 4){
               c.setBackground(Color.LIGHT_GRAY); 
            }else {
                c.setBackground(Color.pink); // or a default background color
    }
             

    return c; 
} 
    
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        
//        Component c = RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        Color color = null;
//        for (int k = 0; k < table.getRowCount(); k++) {
//            for (int j = 0; j < table.getColumnCount(); j++) {
//              if(table.getValueAt(k,j) != null){  
//                    Object result = table.getModel().getValueAt(k, j);
//                    double number = Double.parseDouble(result.toString());
//                    if(number == 1){
//                        color = Color.RED;  
//                        
//                    }
//                }
//            }
//        }    
//        return c;
//    }
}
