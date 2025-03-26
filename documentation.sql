ALTER TABLE produits MODIFY COLUMN prix DECIMAL(10,2);

CREATE TRIGGER nom_trigger
BEFORE | AFTER INSERT | UPDATE | DELETE
ON nom_table
FOR EACH ROW
BEGIN
    -- something
END;

CREATE TRIGGER after_user_insert
AFTER INSERT ON utilisateurs
FOR EACH ROW
BEGIN
    INSERT INTO historique (action, user_id, date_action)
    VALUES ('Ajout utilisateur', NEW.id, NOW());
END;


CREATE TRIGGER before_price_update
BEFORE UPDATE ON produits
FOR EACH ROW
BEGIN
    IF NEW.prix < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Le prix ne peut pas être négatif';
    END IF;
END;

DROP TRIGGER IF EXISTS nom_trigger;


use Illuminate\Support\Facades\DB;

$ventes = DB::table('ventes')
    ->select(
        'ventes.*',
        DB::raw('RANK() OVER (PARTITION BY id_magasin ORDER BY total_vente DESC) as rank')
    )
    ->get();


SELECT *, 
       RANK() OVER (PARTITION BY id_magasin ORDER BY total_vente DESC) as rank
FROM ventes;


use Illuminate\Support\Facades\DB;

$ventes = DB::table('ventes')
    ->select(
        'ventes.*',
        DB::raw('RANK() OVER (PARTITION BY id_magasin ORDER BY total_vente DESC) as rank')
    )
    ->get();


SHOW TRIGGERS;

SHOW TRIGGERS FROM nom_de_ta_base;


<dependency>
    <groupId>net.coobird</groupId>
    <artifactId>thumbnailator</artifactId>
    <version>0.4.8</version>
</dependency>


import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload")
public class ImageUploadController {

    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/resize")
    public String uploadAndResizeImage(@RequestParam("file") MultipartFile file) {
        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File destinationFile = new File(UPLOAD_DIR + fileName);

            Thumbnails.of(file.getInputStream())
                      .size(300, 300)
                      .outputFormat("jpg")  // Convertir en JPG si nécessaire
                      .toFile(destinationFile);

            return "Image uploadée et redimensionnée : " + destinationFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "Erreur lors de l'upload de l'image.";
        }
    }
}


DELIMITER $$

CREATE FUNCTION calculer_tva(prix_ht DECIMAL(10,2)) 
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE taux_tva DECIMAL(5,2);
    SET taux_tva = 0.20;  -- TVA de 20%
    RETURN prix_ht * (1 + taux_tva);
END $$

DELIMITER ;


ALTER TABLE produits MODIFY nom VARCHAR(100) NOT NULL;


ALTER TABLE produits ADD CONSTRAINT unique_nom UNIQUE (nom);


ALTER TABLE produits ADD CONSTRAINT fk_categorie 
FOREIGN KEY (categorie_id) REFERENCES categories(id);


ALTER TABLE produits ALTER stock SET DEFAULT 0;


ALTER TABLE produits DROP FOREIGN KEY fk_categorie;
