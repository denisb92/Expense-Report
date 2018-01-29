/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expensereport;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author denis
 */
public class UpdateEmployee {
    
        JTextField TechUpdate = new JTextField(10);
        JTextField FoodUpdate = new JTextField(10);
        JTextField TransUpdate = new JTextField(10);
        
        JComboBox box = new JComboBox();
        String Query = "UPDATE employeesexpenses set ";
        String idselected = " ";
        String firstname = " ";
        String lastname = " ";
        String TechExp;
        String FoodExp;
        String TransExp;
        String UserId;
        String Id = " ";
        
     public void openUpdateWindow()
    {
         JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(0,5,10));
        
        frame.setSize(400,300);
        frame.setResizable(false);
        JPanel headerPanel = new JPanel();
        JLabel headerText = new JLabel();
        headerText.setText("Update Employee Info");
        headerText.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        headerPanel.add(headerText);
        headerPanel.setLayout(new FlowLayout(0,80,0));
        frame.add(headerPanel);
               
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(0,30,15));
        frame.add(panel);
       
        
        frame.setVisible(true);
        JLabel name = new JLabel();
        name.setText("Employee");
       
        
        //Sets up connection to database
         String DB_URL = "jdbc:mysql://localhost:3306/expensereports";
        String USER= "root";
        String PASS= "";
        //Puts the employees names into the drop down box through query
        String sql = "SELECT UserId, FirstName,LastName FROM expensereports";
          try{
              
               Class.forName("com.mysql.jdbc.Driver");
          Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
             Statement stmt = null;
        stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql);
              while (rs.next()) {
                  UserId = rs.getString("UserId");
                  firstname = rs.getString("FirstName");
                  lastname = rs.getString("LastName");
                  
                  box.addItem(UserId+" "+ firstname + " " + lastname);
                
              }
    
          }
              catch(Exception e){}
            panel.add(name);
              panel.add(box);
              //Sets up the form
               JPanel panel2 =new JPanel();
        panel2.setLayout(new FlowLayout(0,10,2));
        JLabel Techadd = new JLabel("Add to Tech");
        panel2.add(Techadd);
   
        panel2.add(TechUpdate);
        
          JPanel panel3 =new JPanel();
        panel3.setLayout(new FlowLayout(0,10,2));
         JLabel Foodadd = new JLabel("Add to Food");
         panel3.add(Foodadd);
        panel3.add(FoodUpdate);
        
        JPanel panel4 =new JPanel();
        panel4.setLayout(new FlowLayout(0,10,2));
         JLabel Transadd = new JLabel("Add to Transportation");
         panel4.add(Transadd);
        panel4.add(TransUpdate);
        
        JPanel panel5 = new JPanel();
        panel5.setLayout(new FlowLayout(0,60,2));
        JButton cancel = new JButton("Main Menu");
        
        cancel.addActionListener(new ActionListener() { 
  public void actionPerformed(ActionEvent e) {
      //Listener for the main menu button 
            frame.dispose();
            MainMenu main = new MainMenu();
            main.setVisible(true);
  }});
        
        
        panel5.add(cancel);
        JButton update = new JButton("Update");
        update.addActionListener(new ActionListener() { 
  public void actionPerformed(ActionEvent e) {
      //Listener for the main menu button 
        //Gets the id for the user being updated   
      Id = box.getSelectedItem().toString();
       int index = Id.indexOf(" ");
       idselected = Id.substring(0,index);
       
    
       //Runs the Query function
       updateEmployee();
  }});
        panel5.add(update);
        
        
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);
        
        
        
        
        
    }
     public void updateEmployee()
     {
         String DB_URL = "jdbc:mysql://localhost:3306/expensereports";
        String USER= "root";
        String PASS= "";
         try{
              Class.forName("com.mysql.jdbc.Driver");
          Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
          //Updates the tech, food and trans fields for the employee chosen 
          String sql = "SELECT TechExp, FoodExp, TransExp FROM employeesexpenses WHERE "
                  + "UserId= '"+idselected+"'";
            Statement stmt = null;
        stmt = conn.createStatement();
        
        ResultSet rs = stmt.executeQuery(sql);
        //Checks to see if the user added to the text field, if the entry is empty then a default value of 0 is added to the new entry
        //when the update button is clicked
        while (rs.next()) {
        if(!TechUpdate.getText().equals(""))
        {
          String tech =  " " + TechUpdate.getText();
           
            Query = Query + "TechExp = concat(TechExp, '"+tech+"') ";
           
        }
        else
        {
            Query = Query + " ,TechExp = concat(TechExp,' 0') ";
        }
         if(!FoodUpdate.getText().equals(""))
        {
          String food = " " +  FoodUpdate.getText();
          
          
            Query = Query + " ,FoodExp =concat(FoodExp,'"+food+"') ";
            
        }
         else
         {
             Query = Query + " ,FoodExp = concat(FoodExp,' 0') ";
         }
          
          if(!TransUpdate.getText().equals(""))
        {
           String trans = " " +  TransUpdate.getText();
         
            Query = Query + " ,TransExp = concat(TransExp,'"+trans+"') ";
            
            
        }
          else{
              Query = Query + " ,TransExp = concat(TransExp,' 0') ";
          }
        }
        //Gets the current time and puts it in the time stamp for the entry submitted
          DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
          String time = " ";
           time = time + dateTimeInstance.format(Calendar.getInstance().getTime());
           time = time + ".";
            Query = Query + ", TimeStamp = concat(TimeStamp, '"+time+"')";
          Query = Query +" WHERE UserId = '"+ idselected +"'";
         
          stmt.executeUpdate(Query);
          
          conn.close();
        JOptionPane.showMessageDialog(null,"Done");
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
         }
         
         //Resets the query string
         Query = "UPDATE employeesexpenses set ";
     }
    
}
