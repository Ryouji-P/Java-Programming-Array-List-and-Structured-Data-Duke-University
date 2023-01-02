
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are total " + uniqueIPs + " unique IPs");
    }
    
    public void testHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        System.out.println(la.uniqueIPVisitsOnDay("Mar 24"));
        //System.out.println(la.uniqueIPVisitsOnDay("Sep 30"));
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        System.out.println(la.countUniqueIPsInRange(200,299));
        //System.out.println(la.countUniqueIPsInRange(300,399));
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        System.out.println(la.countVisitsPerIP());
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(counts));
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(la.iPsMostVisits(counts));
    }
    
    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        System.out.println(la.iPsForDays());
    }
    
    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        //HashMap<String, ArrayList<String>> iPsForDaysMap = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
    }
    
    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        HashMap<String, ArrayList<String>> iPsForDaysMap = la.iPsForDays();
        System.out.println(la.iPsWithMostVisitsOnDay(iPsForDaysMap, "Mar 17"));
    }
}
