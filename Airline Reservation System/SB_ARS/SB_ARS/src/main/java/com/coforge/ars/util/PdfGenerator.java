package com.coforge.ars.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.coforge.ars.model.Booking;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class PdfGenerator {

    public static ByteArrayInputStream generateTicket(Booking booking) {

        Document document = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);

            document.open();

            document.add(new Paragraph("AIRLINE RESERVATION SYSTEM"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Flight Ticket"));
            document.add(new Paragraph("-----------------------------"));

            document.add(new Paragraph("Booking ID : "
                    + booking.getBookingId()));

            document.add(new Paragraph("Passenger : "
                    + booking.getUser().getName()));

            document.add(new Paragraph("Flight Number : "
                    + booking.getFlight().getFlightNumber()));

            document.add(new Paragraph("Source : "
                    + booking.getFlight()
                             .getSourceAirport()
                             .getAirportName()));

            document.add(new Paragraph("Destination : "
                    + booking.getFlight()
                             .getDestinationAirport()
                             .getAirportName()));

            document.add(new Paragraph("Departure : "
                    + booking.getFlight()
                             .getDepartureTime()));

            document.add(new Paragraph("Seat Class : "
                    + booking.getSeatClass()));

            document.add(new Paragraph("Passengers : "
                    + booking.getPassengers()));

            document.add(new Paragraph("Total Fare : ₹"
                    + booking.getTotalFare()));

            document.add(new Paragraph("Status : "
                    + booking.getBookingStatus()));
            
            document.add(new Paragraph("Seat Numbers : "
                    + booking.getSeatNumbers()));

            document.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return new ByteArrayInputStream(out.toByteArray());

    }

}