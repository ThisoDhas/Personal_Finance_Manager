/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package app;

/**
 *
 * @author THISO
 */
import java.time.LocalDate;
import java.util.*;
import java.io.*;
class App{
    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
       FinanceManager manager=new FinanceManager();
       while(true)
       {
           System.out.println("1.Add Transcation");
           System.out.println("2.Edit Transation");
           System.out.println("3.Delete Transaction");
           System.out.println("4.Sort By Date");
           System.out.println("5.Sort By Amount");
           System.out.println("6.Sort By Category");
           System.out.println("7.Save");
           System.out.println("8.Load");
           System.out.println("9.Exit");
           
           int choice=sc.nextInt();
           sc.nextLine();
           switch(choice)
           {
               case 1:
                   System.out.println("Enter The Date(yyyy-mm-dd):");
                   LocalDate date=LocalDate.parse(sc.nextLine());
                   System.out.println("Enter the amount:");
                   double amount=sc.nextDouble();
                   sc.nextLine();
                   System.out.println("Enter Category:");
                   String category=sc.nextLine();
                   System.out.println("Enter the description:");
                   String description=sc.nextLine();
                   manager.addTransaction(new Transaction(date,amount,category,description));
                   break;
                case 2:
                    System.out.println("Enter the index of Transaction to Edit");
                    int index=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the Date(yyyy-mm-dd):");
                    LocalDate newdate=LocalDate.parse(sc.nextLine());
                    System.out.println("Enter The Amount");
                    double newamount=sc.nextDouble();
                    sc.nextLine();
                    System.out.println("Enter the Category");
                    String newcategory=sc.nextLine();
                    System.out.println("Enter the Description");
                    String newdescription=sc.nextLine();
                    Transaction newtransaction=new Transaction(newdate,newamount,newcategory,newdescription);
                      manager.editTransaction(index,newtransaction);
                      break;
                case 3:
                    System.out.println("Enter the index of Transaction to Delete");
                    int removeIndex=sc.nextInt();
                    manager.removeTransaction(removeIndex);
                    break;
                case 4:
                    manager.sortByDate();
                    break;
                case 5:
                    manager.sortByAmount();
                    break;
                case 6:
                    manager.sortByCategory();
                    break;
                case 7:
                    System.out.println("Enter Filename To Save The Transaction");
                    String filename=sc.nextLine();
                    manager.saveToFile(filename);  
                    break;
                case 8:
                    System.out.println("Enter the filename from which file has to be loaded");
                    String loadfile=sc.nextLine();
                    manager.loadTransaction(loadfile);
                    break;
                case 9:
                    System.exit(0);
                    break;
           }
       }
    }
}
 class Transaction{
        LocalDate date;
        double amount;
        String category;
        String description;
         Transaction(LocalDate date,double amount,String category,String description)
        {
            this.date=date;
            this.amount=amount;
            this.category=category;
            this.description=description;
        }
        public  LocalDate getDate(){
            return date;
        }
        public double getAmount(){
            return amount;
        }
        public String getcategory(){
            return category;
        }
        
        // @override
         public String toString(){
          return "[Date=" + date + ", Amount=" + amount + ", Category=" + category + ", Description=" + description + "]";
    }
    }
 class FinanceManager{
     ArrayList<Transaction> list=new ArrayList<>();
     public void addTransaction(Transaction transaction)
     {
         list.add(transaction);
     }
     public void editTransaction(int index,Transaction newtransaction)
     {
         if(index>=0 && index<list.size())
         {
             list.set(index,newtransaction);
         }
         else
           System.out.println("illegal access of transaction");
     }
     public void removeTransaction(int removeindex)
     {
         if(removeindex>=0 && removeindex<list.size())
         {
             list.remove(removeindex);
         }
         else
         {
             System.out.println("Enter a valid Index");
         }
     }
     public void sortByDate()
     {
         list.sort(Comparator.comparing(Transaction::getDate));
           printTransaction();
     }
     public void sortByAmount(){
         list.sort(Comparator.comparing(Transaction::getAmount));
         printTransaction();
     }
     public void sortByCategory(){
         list.sort(Comparator.comparing(Transaction::getcategory));
          printTransaction();
     }
     public void printTransaction()
     {
         for(Transaction transaction:list)
         {
             System.out.println(transaction);
         }
     }
     public void saveToFile(String filename)
     {
         File f1=new File(filename);
         try{
             FileWriter fw=new FileWriter(f1);
             for(Transaction transaction:list)
             {
                 fw.write(transaction.toString()+"\n");              
             }
             fw.close();
             System.out.println("Trasactions saved to file sucessfully");
         }
         catch (IOException e)
         {
             System.out.println("An error occured saving the Transaction to file"+e.getMessage());
         }        
     }
     public void loadTransaction(String loadFile)
     {
         File f1=new File(loadFile);
         try{
             FileReader fr=new FileReader(f1);
             Scanner scanner=new Scanner(fr);
             while(scanner.hasNextLine())
             {
                 String lines=scanner.nextLine();
                 String parts[]=lines.split(",");
                LocalDate date = LocalDate.parse(parts[0]);
                double amount = Double.parseDouble(parts[1]);
                String category = parts[2];
                String description = parts[3];
                list.add(new Transaction(date, amount, category, description));
             }
         }
         catch(IOException e)
         {
             e.printStackTrace();
         }
     }
 }   
    
    


