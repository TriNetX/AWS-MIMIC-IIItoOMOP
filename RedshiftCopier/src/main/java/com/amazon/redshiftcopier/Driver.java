package com.amazon.redshiftcopier;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoodrya
 */
public class Driver {
    
    private static FileProcessor processor;
    
    public String lambda(SNSEvent event, Context context)
    {
        System.err.println("trinetx: Started RedshiftCopier JAR");
        try
        {   
            List<String> list = new ArrayList<String>();
            
            for(SNSEvent.SNSRecord record : event.getRecords())
            {
                String table = record.getSNS().getMessage().split(" ")[0];
                System.err.println("trinetx: Driver: table from SNS: " + table);
                
                processor = new FileProcessor(table);
                processor.readFiles();
           
                processor.closeConnection();
                
                list.add(table);
            }
            
            System.err.println("trinetx: " + list.toString() + " successfully loaded");
            return list.toString() + " successfully loaded";
        }
        catch(IOException | ClassNotFoundException | SQLException ex) {
          System.err.println("trinetx: Exception: " + ex.toString());
          return ex.toString(); 
        }
    }
}
