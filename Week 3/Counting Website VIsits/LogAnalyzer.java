
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
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             if (!counts.containsKey(ipAddr)) {
                 counts.put(ipAddr, 1);   
             } else {
                 counts.put(ipAddr, counts.get(ipAddr) + 1);   
             }
         }
         return counts; 
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
         int max = 0;
         //String maxIP = "";
         for (String ip : counts.keySet()) {
             if (counts.get(ip) > max) {
                 max = counts.get(ip);
         //        maxIP = ip;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
         ArrayList<String> mostIPs = new ArrayList<String>();
         int max = mostNumberVisitsByIP(counts);
         for (String ip : counts.keySet()) {
             if (counts.get(ip) == max){
                 mostIPs.add(ip);           
             }
         }
         return mostIPs;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> iPsForDaysMap = new HashMap<String, ArrayList<String>>();
         
         for (LogEntry le : records){
             String date = le.getAccessTime().toString();
             String ip = le.getIpAddress();
             date = date.substring(4,10);
             //ArrayList<String> uniqueIPs = new ArrayList<String>();
             //System.out.println(date);
             if (!iPsForDaysMap.containsKey(date)) {
                 ArrayList<String> iPs = new ArrayList<String>();
                 iPs.add(ip);
                 iPsForDaysMap.put(date, iPs);
             } else {
                 ArrayList<String> iPs = iPsForDaysMap.get(date);
                 //if (!iPs.contains(ip)) {
                     iPs.add(ip);
                     iPsForDaysMap.put(date, iPs);
                 //}
             }   
         }
         
         return iPsForDaysMap;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDaysMap) {
         int max = 0;
         String dayMostVisits = "";
         for (String day : iPsForDaysMap.keySet()) {
             int size = iPsForDaysMap.get(day).size();
             if (size > max) {
                 max = size;
                 dayMostVisits = day;
             }   
         }
         return dayMostVisits;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDaysMap, String day) {
         ArrayList<String> ipMost = new ArrayList<String>();
         ArrayList<String> iPsOnDay = iPsForDaysMap.get(day);
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (String ip : iPsOnDay) {
             if (!counts.containsKey(ip)) {
                 counts.put(ip, 1);   
             } else {
                 counts.put(ip, counts.get(ip) + 1);   
             }       
         }
         ipMost = iPsMostVisits(counts);
         return ipMost;
     }
}
