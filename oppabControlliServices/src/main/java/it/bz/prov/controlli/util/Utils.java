package it.bz.prov.controlli.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.logging.Logger;

public class Utils {

	 /**
     * da una data in formato Date restituisce la stringa nel formato DD/MM/YYYY
     * @param date
     * @return String
     */
    public static String getDateString(Date date)
    {
        if(date == null) return "";
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String dataStr = format.format(date);
        if(dataStr == null) return "";
        return dataStr;
    }

    /**
     * Ritorna il logger applicativo
     * @return
     */
    public static Logger getLogger(){
        return Logger.getLogger("OPPAB_CONTROLLI");
    }
}
