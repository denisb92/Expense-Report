/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expensereport;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author denis
 */
public class CheckEmployee {
     JRadioButton Tech = new JRadioButton("Tech");
        JRadioButton Food = new JRadioButton("Food");
        JRadioButton Trans = new JRadioButton("Trans");
        
        JComboBox box = new JComboBox();
        String Query = "SELECT FirstName, LastName ";
        String Query2 = "SELECT UserId ";
        String UserId = " ";
        String firstname = " ";
        String lastname = " ";
        String TechExp;
        String FoodExp;
        String TransExp;
        String Timestamp;
        String idselected = " ";
        String Techsplitted[];
         String Foodsplitted[];
          String Transsplitted[];
          String Timesplitted[];
          String queryResults =" ";
           int totalcost = 0;
       public void Connection( )
    {
        //Sets up a JFrame for the layout of the search window
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(0,5,10));
        
        frame.setSize(400,300);
        frame.setResizable(false);
        JPanel headerPanel = new JPanel();
        JLabel headerText = new JLabel();
        headerText.setText("Search For Employee");
        headerText.setFont(new Font("Lucida Fax", Font.BOLD, 14));
        headerPanel.add(headerText);
        headerPanel.setLayout(new FlowLayout(0,80,0));
        frame.add(headerPanel);
               
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(0,30,30));
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
                  
                  box.addItem(UserId + " " + firstname + "  " + lastname);
                
                  
                  
                  
              }
    
          }
              catch(Exception e){}
          //Places the box into the panel for the search window
                    panel.add(name);
              panel.add(box);
              
               JPanel panel2 =new JPanel();
        panel2.setLayout(new FlowLayout(0,10,10));
       
        Tech.setText("Tech Expense");
        Food.setText("Food Expense");
        Trans.setText("Trans Expense");
       
        panel2.add(Tech);
        panel2.add(Food);
        panel2.add(Trans);
        frame.add(panel2);
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(5,40,20));
        //Sets the button to return to the main menu
        JButton back = new JButton("Main Menu");
        JButton check2 = new JButton("Check");
        check2.setText("Check Records");
          panel3.add(back);
          back.addActionListener(new ActionListener() { 
  public void actionPerformed(ActionEvent e) {
      //Listener for the main menu button 
            frame.dispose();
            MainMenu main = new MainMenu();
            main.setVisible(true);
  }});
          panel3.add(check2);
          check2.addActionListener(new ActionListener() { 
  public void actionPerformed(ActionEvent e) { 
      //Listener for the search button 
      //Checks to see which radio buttons are selected before running the search
       
      
          if(Tech.isSelected())
          {
              Query2 = Query2 + ", TechExp ";
          }
          if(Food.isSelected())
          {
               Query2 = Query2 + ", FoodExp";
          }
          if(Trans.isSelected())
          {
               Query2 = Query2 + ", TransExp";
          }
      //Sets the Query string with the query needed 
      Query2 = Query2 + ", TimeStamp";
       Query2 = Query2 + " FROM employeesexpenses WHERE ";
      idselected = box.getSelectedItem().toString();
       int index = idselected.indexOf(" ");
       int index2 = idselected.indexOf("  ");
       UserId = idselected.substring(0,index);
       firstname = idselected.substring(index, index2);
       lastname = idselected.substring(index2+1);
       Query2 = Query2 + "UserId = '"+ UserId +"'";
      
       //Runs the Query function
       requestQuery();
      }
  
} );
        
        
        
        
        
       
        frame.add(panel3);
    }

    public void splitter(boolean a , boolean b, boolean c )
    {
  //This function selects the string arrays for that employees tech , food, transportation, and timestamps 
  //Splits the arrays in a way to display them as Tech Expense, Food Expense , Transportation Expense, and the time stamp for that submission
       for(int i = 0; i < Timesplitted.length; i++ )
        {
            if (a)
            {
                queryResults = queryResults + '$' + Techsplitted[i] +"\t\t";
                totalcost = totalcost +Integer.parseInt(Techsplitted[i]);
            }
            if (b)
            {
                queryResults = queryResults + '$' + Foodsplitted[i] +"\t\t";
                totalcost = totalcost +Integer.parseInt(Foodsplitted[i]);
            }
            if (c)
            {
                queryResults = queryResults + '$' + Transsplitted[i]+"\t\t" ;
                totalcost = totalcost +Integer.parseInt(Transsplitted[i]);
            }
            queryResults = queryResults + Timesplitted[i];
            queryResults = queryResults + "\n";
          
        }  
        
    }
    public void requestQuery()
    {
        //Sets up the window for the query results 
        JFrame results = new JFrame();
        results.setSize(1000, 300);
       results.setResizable(false);
       
        boolean TechSelected = false;
        boolean FoodSelected = false;
        boolean TransSelected = false;
        JPanel resultpanel = new JPanel();
        resultpanel.setLayout(new BorderLayout());
        JTextArea textResults = new JTextArea();
        textResults.setEditable(false);
        resultpanel.add(textResults);
        results.add(resultpanel);
        
        results.setVisible(true);
        //Sets connection to database
         String DB_URL = "jdbc:mysql://localhost:3306/expensereports";
        String USER= "root";
        String PASS= "";
         
        
        String header = " ";
        String QueryHeader = " ";
        String employeeInfo = "\t" + "Employee ID #" +UserId + "   "+  "First Name: " + firstname + "   " + "Last Name: " + lastname +"\n\n"; 
        try {
            //Header for the search results panel 
            QueryHeader = QueryHeader +"\t"+ "QUERY RESULTS: ";
           
            
            textResults.setFont(new Font("Arial", Font.BOLD, 12));
            
             Class.forName("com.mysql.jdbc.Driver");
          Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
          
            Statement stmt = null;
        stmt = conn.createStatement();
             
             ResultSet rs = stmt.executeQuery(Query2);
              JOptionPane.showMessageDialog(null,"Done");
             
              
              while (rs.next()) {
                  //Receives info from the query 
                 
                  
                  if(Tech.isSelected())
                  {
                      TechSelected = true; 
                       header = header + "TechExp ";
                       header = header + "\t\t";
                   //Checks to see if Tech Expense info is selected and sets 
                   // The header and parses the integer value as needed
                  QueryHeader = QueryHeader + " TECH EXPENSE |";
                  TechExp = rs.getString("TechExp");
                  Techsplitted = TechExp.split("\\s+");
                 
                  
                
                  System.out.print(queryResults);
                 
                 
                  }
                 
                  if(Food.isSelected())
                  {
                      FoodSelected = true; 
                       header = header + "FoodExp ";
                       header = header + "\t\t";
                      //Checks to see if Food Expense info is selected and sets 
                   // The header and parses the integer value as needed
                    
                     
                      QueryHeader = QueryHeader + " FOOD EXPENSE |";
                      //splits the array
                  FoodExp = rs.getString("FoodExp");
                  Foodsplitted = FoodExp.split("\\s+");
                
                  }
                   
                  if(Trans.isSelected())
                  {
                      TransSelected = true; 
                       header = header + "TransExp " + "\t\t";
                      //Checks to see if Transportation Expense info is selected and sets 
                   // The header and parses the integer value as needed
                     
                      QueryHeader = QueryHeader + " TRANSPORTATION EXPENSE |";
                     // splits the array
                  TransExp = rs.getString("TransExp");
                  Transsplitted = TransExp.split("\\s+");
                
               
               
                  }
                  //splits the array
                    Timestamp = rs.getString("TimeStamp");
                    
                    Timesplitted = Timestamp.split("\\.");
                  header = header + "Date Submitted";
                  header = header + "\n";
                  
                  QueryHeader = QueryHeader + "\n\n";
                  
                 
                   
              }
               conn.close();
          }
        
              catch(Exception e){}
        //This function is called to format what the display for the query will look like
        
         splitter(TechSelected, FoodSelected, TransSelected);
         
               //Formatting   
        queryResults = queryResults + "\n\n\n\n\n";
         queryResults = queryResults + "\t\t\t" + "TOTAL EXPENSE: $" + totalcost;
             //Sets the textResults for the search result panel with all the header and queryResults
             textResults.setText(employeeInfo + QueryHeader + header + queryResults);
          
             //Clears the query string for the next query search
             Query = "SELECT FirstName, LastName ";
             Query2 = "SELECT UserId ";
              queryResults =" ";
             totalcost = 0;
             
             
             
        }
    
}
