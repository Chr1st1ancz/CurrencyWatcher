package main.java.cz.spsmb;

import main.java.cz.spsmb.service.CSOBDataConvertor;
import main.java.cz.spsmb.service.SimpleDataFetcher;

public class Application {

    public static void main(String[] args) {

        new CSOBDataConvertor().convert(new SimpleDataFetcher().getContent("https://www.csob.cz/portal/lide/kurzovni-listek-old/-/date/kurzy.txt"));

    }

}
