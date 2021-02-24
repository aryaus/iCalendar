import java.awt.Color;
import java.awt.Component;
import java.awt.print.PrinterException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * This class designs the color of a certain table and also colors the related cells. 
 * For instance the first day of the month, the current day or days you have events on.
 */
public class TableColorCellRenderer extends DefaultTableCellRenderer {
        
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int current_day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) + 1;
        // first day of month with f_row and f_column
        int f_row; 
        int f_column;
        int c_row;
        int c_column;
        int month_inRenderer;
        int year_inRenderer;
        int current_year = calendar.get(Calendar.YEAR);
        int current_month = calendar.get(Calendar.MONTH) + 1;
        Color myColor = new Color(218, 218, 241);
        Color currentDay_Color = new Color(181, 181, 227);
        
        int select_cell_column = CalendarPage.select_cell_column;;
        int select_cell_row = CalendarPage.select_cell_row;
        

        
        public TableColorCellRenderer(int month_inRenderer, int year_inRenderer){
            this.month_inRenderer = month_inRenderer;
            this.year_inRenderer = year_inRenderer;
        }
    
        
//        It finds the current and first day of the month and stores them as a result          
        public void setFirst_Current_Day(JTable table){
            for (int k = 0; k < table.getRowCount(); k++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                  if(table.getValueAt(k,j) != null){  
                        int int_atCell = Integer.parseInt(table.getValueAt(k, j).toString());
                        if(int_atCell == 1){
                            this.f_row = k;
                            this.f_column = j;     
                        }if(int_atCell == current_day){
                            this.c_row = k;
                            this.c_column = j;
                        }
                    }
                }
            }
        }
        
        
        
        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) { 
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            JComponent jc = (JComponent)c;
            Object value_present = table.getValueAt(row, column);
            Object value_in_table = CalendarPage.model.getValueAt(row, column);   

            setFirst_Current_Day(table); 

            //case1:first day of the month  
            if (this.f_row == row && this.f_column == column) {
                c.setBackground(new Color (255, 128, 225));
                jc.setBorder( new MatteBorder(2, 2, 2, 2, new Color(255, 51, 207)) );
                c.setForeground(Color.BLACK);
                    //if first day is today
                    if(this.c_row == row && this.c_column == column){
                        c.setForeground(Color.BLUE);
                    }
                
                // If first day is the current day
                if(this.select_cell_column == column && this.select_cell_row == row 
                    &&CalendarPage.selected_month == CalendarPage.change_month &&
                    CalendarPage.selected_year == CalendarPage.change_year){
                    c.setBackground(currentDay_Color);
                    jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(126, 126, 206)) );
                    c.setForeground(Color.BLACK);
                    //if first day is today
                    if(this.c_row == row && this.c_column == column){
                        c.setForeground(Color.BLUE);
                    }
                }
                
                //If the day is a weekend aka. Sunday
                if(column == 6){
                        c.setForeground(Color.RED);
                    }
            //case2: for current day of the calendar
            }else if(this.c_row == row &&
                    this.c_column == column &&
                    this.current_year == this.year_inRenderer &&
                    this.current_month == this.month_inRenderer) {
                    c.setBackground(currentDay_Color);
                    jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(126, 126, 206)) );
                    c.setBackground(myColor);
                    jc.setBorder(new MatteBorder(-1, -1, -1, -1,Color.BLACK) );
                    c.setForeground(Color.BLUE);
                    
                    if(this.select_cell_column == column && this.select_cell_row == row ){
                    c.setBackground(currentDay_Color);
                    jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(126, 126, 206)) );
                    c.setForeground(Color.BLUE);
                    }
                    
                }
            
            //If the day is a weekend aka. Sunday
            else if(column == 6 ){
                c.setBackground(myColor);
                jc.setBorder(new MatteBorder(-1, -1, -1, -1,Color.BLACK) );
                c.setForeground(Color.RED);
                if(this.select_cell_column == column && this.select_cell_row == row ){
                    c.setBackground(currentDay_Color);
                    jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(126, 126, 206)) );
                    c.setForeground(Color.RED);
                }
            }    
                
             // If a day is selected
            else {
                c.setBackground(myColor);
                jc.setBorder(new MatteBorder(-1, -1, -1, -1,Color.BLACK) );
                c.setForeground(Color.BLACK);
               
                if(this.select_cell_column == column && this.select_cell_row == row
                   &&CalendarPage.selected_month == CalendarPage.change_month &&
                    CalendarPage.selected_year == CalendarPage.change_year){
                    c.setBackground(currentDay_Color);
                    jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(126, 126, 206)) );
                    c.setForeground(Color.BLACK);
                } 
            }
                return c;
        } 

}
