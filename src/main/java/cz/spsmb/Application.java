package cz.spsmb;

import cz.spsmb.service.data.CSOBDataConvertor;
import cz.spsmb.service.data.SimpleDataFetcher;
import cz.spsmb.tests.MailTest;

public class Application {

    public static void main(String[] args) throws InterruptedException {

        loop(1, true);
    }

    public static void loop(int time, boolean validate) throws InterruptedException {
        do {
            // MailTest.Start();
            new CSOBDataConvertor().convert(new SimpleDataFetcher().getContent("https://www.csob.cz/portal/lide/kurzovni-listek-old/-/date/kurzy.txt"));
            Thread.sleep(time);
        } while (validate);
    }

}
