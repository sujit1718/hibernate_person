package hibernate_person.controller;

import java.util.Scanner;

import org.hibernate.boot.archive.scan.spi.ScanEnvironment;

import antlr.collections.List;
import hibernate_person.dao.PersonDao;
import hibernate_person.dto.Person;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Press \n1.Signup \n2.Login");
		Person person = new Person();
		PersonDao dao = new PersonDao();
        switch(scanner.nextInt()) {
        
        case 1: {
        	
        	System.out.println("Enter the Id: ");
        	person.setId(scanner.nextInt());
        	System.out.println("Enter the Name: ");
        	person.setName(scanner.next());
        	System.out.println("Enter the Phone: ");
        	person.setPhone(scanner.nextLong());
        	System.out.println("Enter the email: ");
        	person.setEmail(scanner.next());
        	System.out.println("Enter the Password: ");
        	person.setPassword(scanner.next());
        	dao.savePerson(person);
        	break;
        }         
        case 2:{
        	
        	System.out.println("Enter the email: ");
        	String email = scanner.next();
        	System.out.println("Enter the Password: ");
            String password = scanner.next();
        	
        	Person dbPerson = dao.getPerson(email);
        	if(dbPerson!=null) {
        	   if(password.equals(dbPerson.getPassword())) {
        		  System.out.println("Login SuccessFully!!");
        		  System.out.println();
        		  System.out.println("1. Find person by Id.\n2. Find person by phone.\n3. Find person by name. \n4. Delete person by id. \n5. Delete person by phone. \n6. Delete person by email. \n7. Find all persons. \n8. Update Person.");
        		  Scanner sc = new Scanner(System.in);
        	 switch (sc.nextInt()) {
				case 1:{
					System.out.println("Enter the Id: ");
		        	int id = sc.nextInt();  
		        	
		        	Person dbId = dao.findById(id);
		        	if(dbId!=null) {
		        		System.out.println(dbId);
		        	}else {
		        		System.out.println("Id Not Found!!");
		        	}
					break;
				}
                case 2:{
                	System.out.println("Enter the Phone: ");
		        	long phone = sc.nextLong();  
		        	
		        	Person dbPhone = dao.findByPhone(phone);
		        	if(dbPhone!=null) {
		        		System.out.println(dbPhone);
		        	}else {
		        		System.out.println("Phone Not Found!!");
		        	}
					break;
				}
                case 3:{
                	System.out.println("Enter the Name: ");
		        	String name = sc.next();  
		            dao.findByName(name);
					break;
				}
                case 4:{
                	System.out.println("Enter the Id: ");
		        	int id = sc.nextInt();
		        	int result = dao.deleteById(id);
		              if(result!=0) {
		        		System.out.println("Data Deleted Successfulyy!!");
		        	  }
		        	  else {
		        		  System.out.println("Id not found!!");
		        	}
					break;
				}
                case 5:{
                	System.out.println("Enter the Phone: ");
		        	Long phone = sc.nextLong();
		        	 
		        	if (dao.deleteByPhone(phone)!=0) {
						System.out.println("Deleted SuccessFully!!");
					} else {
                        System.out.println("Phone Not Found!!");
					}
					break;
				}
                case 6:{
                	System.out.println("Enter the Email: ");
		        	String email1 = sc.next();
		        	int result = dao.deleteByEmail(email1);
		        	if (result!=0) {
						System.out.println("Deleted SuccessFully!!");
					} else {
                        System.out.println("Not Deleted!!");
					}
					break;
				}
                case 7:{
                	System.out.println(dao.findAllPerson()); 
          
					break;
				}
                case 8:{
                	System.out.println("Enter the id : ");
                	int id = sc.nextInt();
					int result = dao.updatePerson(id);
					if(result!=0) {
						System.out.println("Data Updated!!");
					}
					else {
						System.out.println("Data not updated!!");
					}
					break;
				}
				default:
					System.out.println("Entered Wrong Input!!!");
					break;
				}
        	   }else {
        		  System.out.println("Invalid Password!");
        	   }
        	}else {
        		System.out.println("Email Not Found!!");
        	}
        	
        	break;
        }
      }
  }
}
