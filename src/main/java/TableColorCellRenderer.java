import java.awt.Color;
import java.awt.Component;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
        int event_row = -1;
        int event_column = -1;
        int month_inRenderer;
        int year_inRenderer;
        int current_year = calendar.get(Calendar.YEAR);
        int current_month = calendar.get(Calendar.MONTH) + 1;
        Color myColor = new Color(204, 242, 255);
        Color currentDay_Color = new Color(153, 230, 255);
        
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
        
        //        It finds the event day of the month and stores them as a result
        //        x is the day's month of event
        public void set_event_Day(JTable table, int x){
            for (int k = 0; k < table.getRowCount(); k++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                  if(table.getValueAt(k,j) != null){  
                        int int_atCell = Integer.parseInt(table.getValueAt(k, j).toString());
                        if(int_atCell == x){
                            this.event_row = k;
                            this.event_column = j;     
                        }
                    }
                }
            }
        }
        
        public void set_event_color(JTable table, int row, int column,Component c, JComponent jc){
            for(int i=0; i<CalendarPage.date_arr.size();i++){
                Calendar cali = new GregorianCalendar();
                cali.setTime(CalendarPage.date_arr.get(i));
                set_event_Day(table, cali.get(Calendar.DAY_OF_MONTH));
                int event_month = cali.get(Calendar.MONTH) + 1;
                int event_year =cali.get(Calendar.YEAR);
                    if(this.event_row == row &&
                        this.event_column == column &&
                         event_year == this.year_inRenderer &&
                        event_month == this.month_inRenderer) 
                    {
                        if(CalendarPage.pry_arr.get(i).equals("high")){
                          jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(255, 128, 128)) );
                          c.setBackground(new Color(255, 128, 128));

                        }else if(CalendarPage.pry_arr.get(i).equals("medium")){
                           jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(255, 255, 179)) );
                           c.setBackground(new Color(255, 255, 179));

                        }else if(CalendarPage.pry_arr.get(i).equals("low")){
                           jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(153, 255, 153)) );
                           c.setBackground(new Color(153, 255, 153));
                        }
                        if(this.select_cell_column == column && this.select_cell_row == row ){
                        c.setBackground(currentDay_Color);
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
                c.setBackground(new Color (255, 230, 255));
                jc.setBorder( new MatteBorder(2, 2, 2, 2, new Color(255, 128, 255)) );
                c.setForeground(Color.BLACK);
                set_event_color(table, row, column, c, jc);
                    //if first day is today
                    if(this.c_row == row && this.c_column == column){
                        c.setForeground(Color.BLUE);
                    }
                
                // If first day is the current day
                if(this.select_cell_column == column && this.select_cell_row == row 
                    &&CalendarPage.selected_month == CalendarPage.change_month &&
                    CalendarPage.selected_year == CalendarPage.change_year){
                    c.setBackground(currentDay_Color);
                    jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 153, 204)) );
                    c.setForeground(Color.BLACK);
                    set_event_color(table, row, column, c, jc);
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
                    jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 153, 204)) );
                    c.setBackground(myColor);
                    jc.setBorder(new MatteBorder(-1, -1, -1, -1,Color.BLACK) );
                    c.setForeground(Color.BLUE);
                    set_event_color(table, row, column, c, jc);
                    if(this.select_cell_column == column && this.select_cell_row == row ){
                    c.setBackground(currentDay_Color);
                    jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 153, 204)) );
                    c.setForeground(Color.BLUE);
                    set_event_color(table, row, column, c, jc);
                    }
                    
                }
            
            //If the day is a weekend aka. Sunday
            else if(column == 6 ){
                c.setBackground(myColor);
                jc.setBorder(new MatteBorder(-1, -1, -1, -1,Color.BLACK) );
                c.setForeground(Color.RED);
                set_event_color(table, row, column, c, jc);
                if(this.select_cell_column == column && this.select_cell_row == row ){
                    c.setBackground(currentDay_Color);
//                    jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 153, 204)) );
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
                    jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 153, 204)) );
                    c.setForeground(Color.BLACK);
                }
//                for(int i=0; i<CalendarPage.date_arr.size();i++){
//                Calendar cali = new GregorianCalendar();
//                cali.setTime(CalendarPage.date_arr.get(i));
//                set_event_Day(table, cali.get(Calendar.DAY_OF_MONTH));
//                int event_month = cali.get(Calendar.MONTH) + 1;
//                int event_year =cali.get(Calendar.YEAR);
//                    if(this.event_row == row &&
//                        this.event_column == column &&
//                         event_year == this.year_inRenderer &&
//                        event_month == this.month_inRenderer) 
//                    {
//                        System.out.println("goooooooooooooooooooooooooooooo");
//                        if(CalendarPage.pry_arr.get(i).equals("high")){
//                          jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(255, 128, 128)) );
//                          c.setBackground(new Color(255, 128, 128));
//
//                        }else if(CalendarPage.pry_arr.get(i).equals("medium")){
//                           jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(255, 255, 179)) );
//                           c.setBackground(new Color(255, 255, 179));
//
//                        }else if(CalendarPage.pry_arr.get(i).equals("low")){
//                           jc.setBorder(new MatteBorder(2, 2, 2, 2, new Color(153, 255, 153)) );
//                           c.setBackground(new Color(153, 255, 153));
//                        }
//                        if(this.select_cell_column == column && this.select_cell_row == row ){
//                        c.setBackground(currentDay_Color);
//                        }    
//
//                    }
//                }
                set_event_color(table, row, column, c, jc);
            }
                return c;
        } 

}

