
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
             records.add(WebLogParser.parseEntry(line));
         }
     }
     
     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);   
             }   
         }
         return uniqueIPs.size();
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num) {
         
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (statusCode > num) {
                 System.out.println(le);   
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
             String date = le.getAccessTime().toString();
             //System.out.println(date);
             if (date.contains(someday)){
                 String ipAddr = le.getIpAddress();
                 if (!uniqueIPs.contains(ipAddr)){
                     uniqueIPs.add(ipAddr);   
                 } 
             }   
         }
         System.out.println(uniqueIPs.size());
         return uniqueIPs;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
             int statusCode = le.getStatusCode();
             if (statusCode >= low && statusCode <= high) {
                 String ipAddr = le.getIpAddress();
                 if (!uniqueIPs.contains(ipAddr)){
                     uniqueIPs.add(ipAddr);   
                 }   
             }
         }
         return uniqueIPs.size();
     }
}
