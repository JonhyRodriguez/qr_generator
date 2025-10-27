package org.example;

import com.google.zxing.WriterException;

import java.io.IOException;
import java.util.Scanner;

import static org.example.qr.QRGenerator.generateQRCode;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🌀 Generador de QR en ejecución. Escribe 'salir' para terminar.");

        // Get the user's Downloads folder
        String userHome = System.getProperty("user.home");
        String downloadsPath = userHome + "/Downloads/";

        while (true) {
            System.out.print("🔗 Introduce el enlace (o escribe 'salir'): ");
            String link = scanner.nextLine();

            if (link.equalsIgnoreCase("salir")) {
                System.out.println("👋 Programa finalizado.");
                break;
            }

            System.out.print("📝 Escribe el nombre deseado para el archivo (sin extensión): ");
            String fileName = scanner.nextLine().trim();

            // Valid name and build all path
            if (fileName.isEmpty()) {
                System.out.println("⚠️ Nombre de archivo no válido. Intenta de nuevo.");
                continue;
            }

            String outputPath = downloadsPath + fileName + ".png";

            try {
                generateQRCode(link, outputPath, 300, 300);
                System.out.println("✅ QR generado con éxito:");
                System.out.println("📎 Enlace: " + link);
                System.out.println("📁 Guardado en: " + outputPath);
            } catch (WriterException | IOException e) {
                System.err.println("❌ Error al generar el QR: " + e.getMessage());
            }
        }

        scanner.close();
    }
}