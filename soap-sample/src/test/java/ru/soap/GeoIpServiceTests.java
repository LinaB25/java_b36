package ru.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
       String geoIp = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("2.94.135.218");
        System.out.println(geoIp);
     //   assertEquals(geoIp.get)
    }
}
