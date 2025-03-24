package com.example.util;

import org.hibernate.cfg.JDBCMetaDataConfiguration;
import org.hibernate.cfg.reveng.DefaultReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.ReverseEngineeringStrategy;
import org.hibernate.tool.hbm2x.Exporter;
import org.hibernate.tool.hbm2x.POJOExporter;

import java.io.File;

public class GenerateEntities {

    public static void main(String[] args) {
        try {
            JDBCMetaDataConfiguration cfg = new JDBCMetaDataConfiguration();

            // Appliquer la stratégie directement (sans ReverseEngineeringSettings)
            ReverseEngineeringStrategy strategy = new DefaultReverseEngineeringStrategy();
            cfg.setReverseEngineeringStrategy(strategy);

            // Charger config + introspecter JDBC
            cfg.configure("hibernate.cfg.xml");
            cfg.readFromJDBC();

            // Dossier de sortie
            File outputDir = new File("src/main/java/com/example/entity");
            outputDir.mkdirs();

            Exporter exporter = new POJOExporter(cfg, outputDir);
            exporter.start();

            System.out.println("✅ Entités générées avec succès !");
        } catch (Exception e) {
            System.err.println("❌ Erreur lors de la génération :");
            e.printStackTrace();
        }
    }
}
