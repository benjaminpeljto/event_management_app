package ba.ibu.edu.web_engineering_project.api.impl.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class QRGenerator {
    private static final Logger logger = LoggerFactory.getLogger(QRGenerator.class);
    private final String pathToTempQR;

    public QRGenerator(String pathToTempQR) {
        this.pathToTempQR = pathToTempQR;
    }

    public void generateTicketQR(
            String ticketID,
            String buyerID,
            String eventName,
            String ticketType
    ) throws WriterException, IOException {

        String data = "Buyer : " + buyerID + "\nEvent : " + eventName + "\n Type : " + ticketType;
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
            Path relativePath = Paths
                    .get(pathToTempQR + File.separator + eventName + "_" + ticketType + "_" +ticketID + ".jpg");

            MatrixToImageWriter.writeToPath(bitMatrix, "jpg", relativePath);

        } catch (Exception e) {
            logger.error("Failed to generate QR code", e);
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }
}
