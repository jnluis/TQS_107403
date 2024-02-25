package integration;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolverService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class AddressResolverIT {

    private ISimpleHttpClient httpClient;
    private AddressResolverService resolver;

    @BeforeEach
    public void init(){
        httpClient = new TqsBasicHttpClient();
        resolver = new AddressResolverService(httpClient);
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {
        // repeat the same tests conditions from AddressResolverTest, without mocks
        double latitude = 40.63436;
        double longitude = -8.65616;

        Optional<Address> result = resolver.findAddressForLocation(latitude,longitude );

        //return
        Address expected = new Address( "Avenida da Universidade", "Aveiro","3810-489", "");

        assertEquals( expected.getRoad(), result.get().getRoad());
    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        // repeat the same tests conditions from AddressResolverTest, without mocks
        Optional<Address> result = resolver.findAddressForLocation( -361,-361);
        // verify no valid result
        assertThrows(NoSuchElementException.class, () -> result.get());
    }

}
