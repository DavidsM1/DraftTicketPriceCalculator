package ticketapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class PassengerTest {

    @Test(expected = IllegalArgumentException.class)
    public final void testNegativeLuggageItemCountValue() {
        Passenger test = new Passenger();
        test.setLuggageItemCount(-1);
    }

}